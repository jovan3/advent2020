(ns advent2020.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn resource-to-vec [resource_filename]
  (slurp (io/file (io/resource resource_filename))))

(defn process-input [input]
  (->>
   (str/split-lines input)
   (map #(Integer/parseInt %))))

(def input (process-input (resource-to-vec "day1")))

(let [[e1 e2] (first
               (filter #(= 2020 (apply + %))
                       (for [c1 input c2 input]
                         (vector c1 c2))))]
  (* e1 e2))

(let [[e1 e2 e3] (first
                    (filter #(= 2020 (apply + %))
                            (for [c1 input c2 input c3 input]
                              (vector c1 c2 c3))))]
  (* e1 e2 e3))
