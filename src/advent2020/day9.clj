(ns advent2020.day9
  (:require [advent2020.input :as input]))

(def preamble-length 25)
(def day-input (->> "day9"
                    input/input-lines
                    (map #(BigInteger. %))))

(defn valid-values [input preamble-length]
  (for [x (take preamble-length input)
        y (take preamble-length input)
        :when (not= x y)]
    (+ x y)))

(def day9-1
  (loop [index 0]
    (if (>= index (- (count day-input)) preamble-length) nil
        (let [valid (valid-values (drop index day-input) preamble-length)
              next (nth day-input (+ preamble-length index))]
          (if (some #(= % next) valid) (recur (inc index)) next)))))

(def all-subsequences
  (apply concat
         (for [size (range 2 (count day-input))]
           (partition size 1 day-input))))

(def day9-2
  (let [ss (first
            (drop-while
             (fn [subsequence] (not (= day9-1 (apply + subsequence)))) all-subsequences))]
    (str (+ (apply max ss) (apply min ss)))))
  

         
