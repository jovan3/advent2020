(ns advent2020.day5
  (:require [clojure.string :as str]
            [advent2020.input :as input]))

(def input (->> "day5"
                input/input-lines
                (map #(str/split % #""))
                (map #(partition-all 7 %))))

(defn go [step [lower upper]]
  (letfn [(new-bound [l u rounding-fn] (+ l (-> (- u l) (/ 2) rounding-fn int)))]
    (if (or (= step "F") (= step "L"))
      [lower (new-bound lower upper #(Math/floor %))]
      [(new-bound lower upper #(Math/ceil %)) upper])))

(defn find-item [initial-range steps]
  (first (reduce #(go %2 %1) initial-range steps)))

(def all-seats (map (fn [[row-steps seat-steps]]
                      (let [row (find-item [0 127] row-steps)
                            seat (find-item [0 8] seat-steps)]
                        (+ seat (* row 8)))) input))

(def day5-1 (apply max all-seats))

(def day5-2
  (let [seats (sort all-seats)
        compare (conj seats (dec (first seats)))]
    (let [[[_ result]]
          (filter (fn [[diff _]] (not= diff -1))
                  (mapv (fn [v1 v2] [(- v1 v2) v1]) compare seats))]
      (inc result))))
