(ns tile-laying.tile
  (:require
    [clojure.walk :as walk]))

(defn shift
  "Shifts elements of v to the right n times with wrapping"
  [n v]
  (let [step (fn [v] (vec (cons (peek v) (pop v))))]
    (nth (iterate step (vec v)) n)))

(defn rotate-2d
  "Rotates 2d coll clockwise by 90 degrees n times"
  ([coll]
   (map reverse (apply map vector coll)))
  ([n coll]
   (nth (iterate rotate-2d coll) n)))

(defn rotate
  "Rotates a tile by n clockwise turns"
  [tile n]
  (let [num-edges (count (:edges tile))]
    (-> tile
        (update :edges (partial shift n))
        (update :corners (partial walk/postwalk (fn [x] (if (number? x) (mod (+ x n) num-edges) x))))
        (update :ascii (partial rotate-2d n)))))
