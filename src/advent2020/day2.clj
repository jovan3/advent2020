(ns advent2020.day2
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(def input (input/input-lines "day2"))

(defn process-line [line]
  (let [[len-range-token letter-token pw] (str/split line #" ")
        range (map #(Integer/parseInt %) (str/split len-range-token #"-"))
        letter (first letter-token)]
    [range letter pw]))

(defn letter-occurrences-ok [line-data]
  (let [[[range-from range-to] letter pw] line-data]
    (<= range-from (or ((frequencies pw) letter) 0) range-to)))

(def day2-1 (count (filter true? (map #(letter-occurrences-ok (process-line %)) input))))

(defn letter-occurrences-ok-part-2 [line-data]
  (let [[[pos1 pos2] letter pw] line-data]
    (= #{true false}
       (set [(= letter (nth pw (dec pos1)))
             (= letter (nth pw (dec pos2)))]))))

(def day2-2 (count (filter true? (map #(letter-occurrences-ok-part-2 (process-line %)) input))))
