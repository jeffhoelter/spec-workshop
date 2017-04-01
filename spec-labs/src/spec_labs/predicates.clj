(ns predicates
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]))

(s/valid? string? "abc")

(s/valid? (s/int-in 0 10) 5)

(s/exercise (s/int-in 0 11))

(s/valid? #(clojure.string/includes? "-0123456789/X" %) "0")

(s/exercise #{\1 \2 \3 \4 \5 \6 \7 \8 \9 \0 \- \X \/})

(s/valid? #(instance? java.util.Date %) (java.util.Date.))

(s/valid? double? 12.3)
