(ns collections
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]))

(s/def ::distinct-strings (s/coll-of string? :distinct true))

(s/valid? ::distinct-strings [1 2])
(s/valid? ::distinct-strings ["1" "2"])
(s/valid? ::distinct-strings ["1" "1"])

(s/exercise ::distinct-strings)


(s/def ::vector-five-bools (s/coll-of boolean? :max-count 5 :kind vector?))

(s/valid? ::vector-five-bools [true])
(s/valid? ::vector-five-bools #{true})
(s/valid? ::vector-five-bools [1])
(s/valid? ::vector-five-bools [true true false true false])
(s/valid? ::vector-five-bools [true true false true false true])

(s/exercise ::vector-five-bools)

(s/def ::set-ints (s/coll-of int? :kind set?))

(s/valid? ::set-ints #{1 2 3})
(s/valid? ::set-ints #{1 2 3 :a})
(s/valid? ::set-ints #{})
(s/valid? ::set-ints [1 2 3])

;; TODO Map of strings to ints


(s/def ::node
  (s/or :branch (s/coll-of ::node :min-count 1 :max-count 2)
        :leaf int?))

(s/valid? ::node [[1 2] [3 [4 5]]])
(s/conform ::node [[1 2] [3 [4 5]]])

(s/exercise ::node)



;; Write a spec for a recipe ingredient consisting of the ingredient name, a quantity, and unit (a keyword). The following ingredients should conform:

(s/def ::units (s/keys :req [::ounce ::tablespoon]))




(s/def ::ingredient )

(def water #:ingredient{:name "water" :quantity 10 :unit :ounce})
(def butter #:ingredient{:name "butter" :quantity 1/2 :unit :tablespoon})
