(ns advent2020.day1
  (:require [advent2020.input :as input]))

(def input (input/input-lines->ints "day1"))

(let [[e1 e2] (first
               (filter #(= 2020 (apply + %))
                       (for [c1 input c2 input]
                         (vector c1 c2))))]
  (* e1 e2))

(let [[e1 e2 e3] (first
                    (filter #(= 2020 (apply + %))
                            (for [c1 input c2 input c3 input]
                              (vector c1 c2 c3))))]
  (* e1 e2 e3)
)
