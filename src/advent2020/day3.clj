(ns advent2020.day3
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(def input (vec (map #(str/split % #"") (input/input-lines "day3"))))
(def row-len (count (first input)))

(defn slope-coords [[dx dy]]
  (iterate (fn [[a b]] [(+ dy a) (mod (+ dx b) row-len)]) [0 0]))

(defn count-trees [slope]
  (let [slope-objects (map #(get-in input %) (slope-coords slope))
        field-length (count input)]
    (->> slope-objects
         (take field-length)
         (filter #(= % "#"))
         count)))

(def day3-1 (count-trees [3 1]))
(def day3-2 (apply * (map count-trees [[1 1] [3 1] [5 1] [7 1] [1 2]])))
