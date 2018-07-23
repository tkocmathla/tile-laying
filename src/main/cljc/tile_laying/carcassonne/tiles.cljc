(ns tile-laying.carcassonne.tiles)

; TODO rename corners

(def tiles
  {:a {:features #{:cloister}
       :edges [:field :field :field :field]
       :corners #{[0 1] [1 2] [2 3] [3 0]}
       :ascii [[\. \. \.]
               [\. \# \.]
               [\. \. \.]]}

   :b {:features #{:cloister}
       :edges [:field :field :road :field]
       :corners #{[0 1] [3 0]}
       :ascii [[\. \. \.]
               [\. \# \.]
               [\. \| \.]]}

   :c {:features #{:pennant}
       :edges [:city :city :city :city]
       :corners #{[0 1] [1 2] [2 3] [3 0]}
       :ascii [[\o \o \o]
               [\o \o \o]
               [\o \o \o]]}

   :d {:edges [:city :city :field :city]
       :corners #{[0 1] [3 0]}
       :ascii [[\o \o \o]
               [\o \o \o]
               [\o \. \o]]}

   :e {:features #{:pennant}
       :edges [:city :city :field :city]
       :corners #{[0 1] [3 0]}
       :ascii [[\o \o \o]
               [\o \o \o]
               [\o \. \o]]}

   :f {:edges [:city :city :road :city]
       :corners #{[0 1] [3 0]}
       :ascii [[\o \o \o]
               [\o \o \o]
               [\o \| \o]]}

   :g {:features #{:pennant}
       :edges [:city :city :road :city]
       :corners #{[0 1] [3 0]}
       :ascii [[\o \o \o]
               [\o \o \o]
               [\o \| \o]]}

   :h {:edges [:city :field :field :city]
       :corners #{[1 2] [3 0]}
       :ascii [[\o \o \o]
               [\o \. \.]
               [\o \. \.]]}

   :i {:features #{:pennant}
       :edges [:city :field :field :city]
       :corners #{[1 2] [3 0]}
       :ascii [[\o \o \o]
               [\o \. \.]
               [\o \. \.]]}

   :j {:edges [:city :road :road :city]
       :corners #{[1 2] [3 0]}
       :ascii [[\o \o \o]
               [\o \+ \-]
               [\o \| \.]]}

   :k {:features #{:pennant}
       :edges [:city :road :road :city]
       :corners #{[1 2] [3 0]}
       :ascii [[\o \o \o]
               [\o \+ \-]
               [\o \| \.]]}

   :v {:edges [:field :field :road :road]
       :corners #{[0 1] [2 3]}
       :ascii [[\. \. \.]
               [\- \+ \.]
               [\. \| \.]]}

   :w {:edges [:field :road :road :road]
       :ascii [[\. \. \.]
               [\- \. \-]
               [\. \| \.]]}

   :x {:edges [:road :road :road :road]
       :ascii [[\. \| \.]
               [\- \. \-]
               [\. \| \.]]}

   :y {:edges [:city :road :field :road]
       :corners #{[1 3]}
       :ascii [[\o \o \o]
               [\- \- \-]
               [\. \. \.]]}})
