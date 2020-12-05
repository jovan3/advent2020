(ns advent2020.day4
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(defn val-bw [str lower upper] (<= lower (Integer/parseInt str) upper))

(def input (input/input-lines "day4"))

(def required ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])
(def validators {"byr" #(val-bw % 1920 2002)
                 "iyr" #(val-bw % 2010 2020)
                 "eyr" #(val-bw % 2020 2030)
                 "hgt" (fn [i] (let [[_ val unit] (re-matches #"(\d+)(cm|in)" i)]
                                 (cond (= unit "in") (val-bw val 59 76)
                                       (= unit "cm") (val-bw val 150 193))))
                 "hcl" #(re-matches #"#([0-9a-f]{6})" %)
                 "ecl" #(re-matches #"(amb|blu|brn|gry|grn|hzl|oth)" %)
                 "pid" #(re-matches #"[0-9]{9}" %)
                 "cid" (fn [_] true)})

(defn valid? [pass-data]
  (every? #(some (fn [entry] (str/includes? entry %)) pass-data) required))

(def pass-data (->> input
                    (partition-by #(not= "" %))
                    (filter #(not= '("") %))
                    (map (fn [data] (mapcat #(str/split % #" ") data)))))

(def day4-1 (count (filter valid? pass-data)))

(defn validate-passport-fields [pass-data]
  (->> pass-data
       (map #(str/split % #":"))
       (map (fn [[field val]] ((validators field) val)))
       (every? identity)))

(def day4-2 (count (filter true? (map validate-passport-fields (filter valid? pass-data)))))
