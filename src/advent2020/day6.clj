(ns advent2020.day6
  (:require [clojure.string :as str]
            [advent2020.input :as input]
            [clojure.set :as cset]))

(def processed-input (->>
                      (input/input-lines "day6")
                      (partition-by #(not= "" %))
                      (filter #(not= '("") %))))

(defn unique-group-letters [group] (into #{} cat group))

(def day6-1 (->> processed-input
                 (map unique-group-letters)
                 (map count)
                 (apply +)))

(defn unique-person-answers [group] (map #(set (str/split % #"")) group)) 

(def day6-2 (->> processed-input
                 (map unique-person-answers)
                 (map #(apply cset/intersection %))
                 (map count)
                 (apply +)))
