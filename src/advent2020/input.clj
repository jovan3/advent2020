(ns advent2020.input
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn resource-to-vec [resource-filename]
  (slurp (io/file (io/resource resource-filename))))

(defn str-lines->ints [input]
  (->>
   (str/split-lines input)
   (map #(Integer/parseInt %))))

(defn input-lines [resource-filename]
  (str/split-lines (resource-to-vec resource-filename)))

(defn input-lines->ints [resource-filename]
  (-> resource-filename
      resource-to-vec
      str-lines->ints))
