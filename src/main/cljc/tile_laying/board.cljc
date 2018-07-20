(ns tile-laying.board
  (:require
    [clojure.pprint :as pp]
    [tile-laying.tile :as tile]
    [tile-laying.carcassonne.core :as carcassonne]
    [tile-laying.carcassonne.tiles :refer [tiles]]))

(defmulti dirs :tile-shape)

(defmethod dirs :square [_]
  [[0 -1] [1 0] [0 1] [-1 -1]])

(defmethod dirs :hex-pointy [_]
  [[1 -1] [1 0] [1 1] [0 1] [-1 0] [0 -1]])

(defmethod dirs :hex-flat [_]
  [[0 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0]])


(defmulti opposite-index (fn [game _] (:tile-shape game)))

(defmethod opposite-index :square [_ index]
  (mod (+ index 2) 4))

;; ----------------------------------------------------------------------------

(defn by-coord
  "Returns the tile at coord"
  [game coord]
  (get-in game [:board coord]))

(defn neighbors [game coord]
  (mapv (partial by-coord game) (for [d (dirs game)] (map + coord d))))

(defn neighbor [game coord dir]
  (by-coord game (map + coord dir)))

(defn valid-edge?
  [game coord [index dir]]
  (let [face (get (:faces (by-coord game coord)) index)
        {:keys [faces]} (neighbor game coord dir)]
    (or (nil? faces)
        (= face (get faces (opposite-index game index))))))

(defn valid-place?
  "Returns true if placing tile at coord is valid"
  [game tile coord]
  (let [new-game (update game :board assoc coord tile)
        {:keys [faces]} (by-coord new-game coord)]
    (every?
      (partial valid-edge? new-game coord)
      (map vector (range (:tile-faces game)) (dirs new-game)))))

(defn place-tile
  "Places tile in game at coord if placement is valid"
  [game tile [x y :as coord]]
  (cond-> game
    (valid-place? game tile coord)
    (update :board assoc coord tile)))

;; ----------------------------------------------------------------------------

; example carcassonne board setup
;
; -2,1 -1,1  0,1  1,1
;  #4   #3   #1   #2
;
;  ...  .|.  ...  .|.
;  .+-  -.-  ---  -.-
;  .|.  ...  ooo  .|.
;
;  .|.       ooo
;  .+-       ---
;  ...       ...
;
;  #5        #0
; -2,0       0,0

(-> carcassonne/initial-state
    (place-tile (:y tiles) [0 0])
    (place-tile (tile/rotate (:y tiles) 2) [0 1])
    (place-tile (:x tiles) [1 1])
    (place-tile (tile/rotate (:w tiles) 2) [-1 1])
    (place-tile (tile/rotate (:v tiles) 3) [-2 1])
    (place-tile (tile/rotate (:v tiles) 2) [-2 0])
    ; bad placement
    (place-tile (:y tiles) [-1 0])
    pp/pprint)
