(ns tile-laying.tile
  (:require
    [clojure.walk :as walk]))

(defn- shift
  "Shifts elements of v to the right n times with wrapping"
  [n v]
  (let [step (fn [v] (vec (cons (peek v) (pop v))))]
    (nth (iterate step (vec v)) n)))

(defn rotate
  "Rotates a tile by n clockwise turns"
  [tile n]
  (let [faces (count (:faces tile))]
    (-> tile
        (update :faces (partial shift n))
        (update :corners (partial walk/postwalk (fn [x] (if (number? x) (mod (+ x n) faces) x)))))))
