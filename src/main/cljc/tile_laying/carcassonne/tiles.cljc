(ns tile-laying.carcassonne.tiles)

; TODO rename corners

(def tiles
  {:a {:features #{:cloister}
       :faces [:field :field :field :field]
       :corners #{[0 1] [1 2] [2 3] [3 0]}
       :ascii ["..."
               ".#."
               "..."]}

   :b {:features #{:cloister}
       :faces [:field :field :road :field]
       :corners #{[0 1] [3 0]}
       :ascii ["..."
               ".#."
               ".|."]}

   :c {:features #{:pennant}
       :faces [:city :city :city :city]
       :corners #{[0 1] [1 2] [2 3] [3 0]}
       :ascii ["ooo"
               "ooo"
               "ooo"]}

   :d {:faces [:city :city :field :city]
       :corners #{[0 1] [3 0]}
       :ascii ["ooo"
               "ooo"
               "o.o"]}

   :e {:features #{:pennant}
       :faces [:city :city :field :city]
       :corners #{[0 1] [3 0]}
       :ascii ["ooo"
               "ooo"
               "o.o"]}

   :f {:faces [:city :city :road :city]
       :corners #{[0 1] [3 0]}
       :ascii ["ooo"
               "ooo"
               "o|o"]}

   :g {:features #{:pennant}
       :faces [:city :city :road :city]
       :corners #{[0 1] [3 0]}
       :ascii ["ooo"
               "ooo"
               "o|o"]}

   :h {:faces [:city :field :field :city]
       :corners #{[1 2] [3 0]}
       :ascii ["ooo"
               "o.."
               "o.."]}

   :i {:features #{:pennant}
       :faces [:city :field :field :city]
       :corners #{[1 2] [3 0]}
       :ascii ["ooo"
               "o.."
               "o.."]}

   :j {:faces [:city :road :road :city]
       :corners #{[1 2] [3 0]}
       :ascii ["ooo"
               "o+-"
               "o|."]}

   :k {:features #{:pennant}
       :faces [:city :road :road :city]
       :corners #{[1 2] [3 0]}
       :ascii ["ooo"
               "o+-"
               "o|."]}

   :v {:faces [:field :field :road :road]
       :corners #{[0 1] [2 3]}
       :ascii ["..."
               "-+."
               ".|."]}

   :w {:faces [:field :road :road :road]
       :ascii ["..."
               "-.-"
               ".|."]}

   :x {:faces [:road :road :road :road]
       :ascii [".|."
               "-.-"
               ".|."]}

   :y {:faces [:city :road :field :road]
       :corners #{[1 3]}
       :ascii ["ooo"
               "---"
               "..."]}})
