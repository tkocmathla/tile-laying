(ns tile-laying.board
  (:require
    [clojure.pprint :as pp]
    [tile-laying.tile :as tile]
    [tile-laying.carcassonne.core :as carcassonne]
    [tile-laying.carcassonne.tiles :refer [tiles]]))

(defmulti dirs :tile-shape)

(defmethod dirs :square [_]
  [[0 -1] [1 0] [0 1] [-1 0]])

(defmethod dirs :hex-pointy [_]
  [[1 -1] [1 0] [1 1] [0 1] [-1 0] [0 -1]])

(defmethod dirs :hex-flat [_]
  [[0 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0]])


(defmulti opposite-edge-index (fn [game _] (:tile-shape game)))

(defmethod opposite-edge-index :square [_ index]
  (mod (+ index 2) 4))

(defmethod opposite-edge-index :hex-pointy [_ index]
  (mod (+ index 3) 6))

(defmethod opposite-edge-index :hex-flat [_ index]
  (mod (+ index 3) 6))

;; ----------------------------------------------------------------------------

(defn by-coord
  "Returns the tile at coord"
  [game coord]
  (get-in game [:board coord]))

(defn neighbors
  "Returns a vector of the neighbor tiles of the tile at coord"
  [game coord]
  (mapv (partial by-coord game) (for [d (dirs game)] (map + coord d))))

(defn neighbor
  "Returns the neighbor tile of the tile at coord in direction dir"
  [game coord dir]
  (by-coord game (map + coord dir)))

(defn edge
  "Returns the edge at edge-index of the tile at coord"
  [game coord edge-index]
  (let [{:keys [edges]} (by-coord game coord)]
    (get edges edge-index)))

(defn neighbor-edge
  [game coord edge-index]
  (let [dir (get (dirs game) edge-index)
        {:keys [edges]} (neighbor game coord dir)]
    (get edges (opposite-edge-index game edge-index))))

(defn- place-tile*
  [game tile coord]
  (update game :board assoc coord tile))

(defn valid-edge?
  "Returns true if the edge at edge-index of the tile at coord matches its
   neighbor edge, or if no such neighbor exists"
  [game coord edge-index]
  (let [edge (edge game coord edge-index)
        nb-edge (neighbor-edge game coord edge-index)]
    (or (nil? nb-edge) (= edge nb-edge))))

(defn valid-place?
  "Returns true if placing tile at coord is valid"
  [game tile coord]
  (let [new-game (place-tile* game tile coord)]
    (every?
      (partial valid-edge? new-game coord)
      (range (:tile-edges game)))))

(defn place-tile
  "Places tile in game at coord if placement is valid"
  [game tile coord]
  (cond-> game
    (valid-place? game tile coord)
    (place-tile* tile coord)))

;; ----------------------------------------------------------------------------

; example carcassonne board setup
;
; -2,-1 -1,-1  0,-1  1,-1
;  #4    #3    #1    #2
;
;  ...   .|.   ...   .|.
;  .+-   -.-   ---   -.-
;  .|.   ...   ooo   .|.
;
;  .|.         ooo
;  .+-         ---
;  ...         ...
;
;  #5          #0
; -2,0         0,0

#_
(-> carcassonne/initial-state
    (place-tile (:y tiles) [0 0])
    (place-tile (tile/rotate (:y tiles) 2) [0 -1])
    (place-tile (:x tiles) [1 -1])
    (place-tile (tile/rotate (:w tiles) 2) [-1 -1])
    (place-tile (tile/rotate (:v tiles) 3) [-2 -1])
    (place-tile (tile/rotate (:v tiles) 2) [-2 0])
    ; bad placement
    (place-tile (:y tiles) [-1 0])
    pp/pprint)
