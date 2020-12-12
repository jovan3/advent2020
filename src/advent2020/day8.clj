(ns advent2020.day8
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(def input (input/input-lines "day8"))

(defn str->int [str] (Integer/parseInt str))

(def processed-input
  (->> input
       (map #(str/split % #" "))
       (map (fn [[instr value-str]] [instr (str->int value-str)]))))

(def opposite-instruction {"nop" "jmp" "jmp" "nop"})

(defn all-nop-jmp-mutations [code]
  (let [code-indexed (map-indexed vector code)]
    (for [[index [instr value]] code-indexed
          :when (some #(= instr %) ["nop" "jmp"])]
      (assoc (into [] code) index [(opposite-instruction instr) value]))))

(defn go [code]
  (loop [acc 0
         visited []
         index 0]
    (cond
      (>= index (count code)) [acc false] ;; [acc looped?]
      (some #(= % index) visited) [acc true]
      
      :else
      (let [new-index (mod index (count code))
            [code val] (nth code new-index)]
        (cond (= code "acc")
              (recur (+ acc val) (conj visited new-index) (inc new-index))
              (= code "nop")
              (recur acc (conj visited new-index) (inc new-index))
              (= code "jmp")
              (recur acc (conj visited new-index) (+ new-index val)))))))

(def day8-1 (let [[acc _] (go processed-input)] acc))

(def day8-2
  (let [[[acc _]] (filter
                 (fn [[_ looped?]] (not looped?))
                 (map go (all-nop-jmp-mutations processed-input)))]
    acc))

