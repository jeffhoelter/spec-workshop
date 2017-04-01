(ns regex
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]
            [clojure.spec.test :as stest]))


(s/def ::alternating (s/* (s/cat :s string? :n number?)))
(s/conform ::alternating ["a" 5 "b" 0.2])

(s/def ::line (s/cat :x1 int? :y1 int? :x2 int? :y2 int?))
(s/conform ::line [0 0, 5 5])

(s/def ::range-args
  (s/? (s/cat :start (s/? number?)
              :end number?
              :step (s/? number?))))

(s/conform ::range-args [])
(s/conform ::range-args [1 2 34])
(s/conform ::range-args [2 3])
(s/conform ::range-args [3 3 4 5/2])


(s/fdef clojure.core/range
        :args ::range-args
        :ret seqable?
        :fn (fn [{:keys args ret}]
              (let [{:keys [start end step]} args]
                (= (first ret) start))))

(stest/check `clojure.core/range)
