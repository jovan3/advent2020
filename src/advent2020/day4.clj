(ns advent2020.day4
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(def input (input/input-lines "day4"))
(def required ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn valid? [pass-data]
  (every? #(some (fn [entry] (str/includes? entry %)) pass-data) required))

(def pass-data (->> input
                    (partition-by #(not= "" %))
                    (filter #(not= '("") %))
                    (map (fn [data] (mapcat #(str/split % #" ") data)))))

(def day4-1 (count (filter valid? pass-data)))

