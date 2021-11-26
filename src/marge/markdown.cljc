(ns marge.markdown
  (:require [marge.core :as core :refer [encode]]))

(def special-chars
  {:linebreak        "\n"
   :column-end       " |"
   :divider          "-"
   :whitespace       " "
   :rule             "---"
   :column-start     " | " ;; We trim the leading space when rendering a ro
   :depth-marker     "#"
   :italic-delimiter "*"})

(defmethod encode [:md :br] [_ _ value] (core/br        special-chars value))
(defmethod encode [:md :hr] [_ _ value] (core/hr        special-chars value))
(defmethod encode [:md :p]  [_ _ value] (core/paragraph special-chars value))
(defmethod encode [:md :h1] [_ _ value] (core/header    special-chars value 1))
(defmethod encode [:md :h2] [_ _ value] (core/header    special-chars value 2))
(defmethod encode [:md :h3] [_ _ value] (core/header    special-chars value 3))
(defmethod encode [:md :h4] [_ _ value] (core/header    special-chars value 4))
(defmethod encode [:md :h5] [_ _ value] (core/header    special-chars value 5))
(defmethod encode [:md :h6] [_ _ value] (core/header    special-chars value 6))

(defmethod encode [:md :blockquote]    [_ _ value] (str "> " value))
(defmethod encode [:md :strikethrough] [_ _ value] (core/delimited "~~" value))
(defmethod encode [:md :italic]        [_ _ value] (core/delimited "*"  value))
(defmethod encode [:md :bold]          [_ _ value] (core/delimited "**" value))
(defmethod encode [:md :normal]        [_ _ value] value)
