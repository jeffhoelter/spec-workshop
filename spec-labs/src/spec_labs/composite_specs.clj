(ns composite-specs
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as gen]
            [clojure.spec.test :as stest]))


(s/def ::symbols (s/and simple-symbol? #(not (= \& %))))
(s/valid? ::symbols \b)
(s/valid? ::symbols \&)
(s/valid? ::symbols \C)

(s/exercise ::symbols)

(s/def ::privileged-ports (s/and int? #(>= % 1) #(<= % 1024)))

(s/valid? ::privileged-ports 1024)
(s/valid? ::privileged-ports 1055)
(s/valid? ::privileged-ports 23)
(s/valid? ::privileged-ports -5)

(s/exercise ::privileged-ports)

(s/def ::unprivileged-ports (s/and int? #(>= % 1025) #(<= % 65536)))

(s/valid? ::unprivileged-ports 1024)
(s/valid? ::unprivileged-ports 1025)
(s/valid? ::unprivileged-ports 10000)

(s/exercise ::unprivileged-ports)

(s/def ::all-ports (s/or :privileged ::privileged-ports :unprivileged ::unprivileged-ports))

(s/valid? ::all-ports 100)
(s/valid? ::all-ports 1024)
(s/valid? ::all-ports 10000)
(s/valid? ::all-ports -1)

(s/exercise ::all-ports)

(s/conform ::all-ports 123)
(s/conform ::all-ports 10000)
(s/conform ::all-ports 0)


(s/def ::scores (s/map-of string? int?))
(s/conform ::scores {"Sally" 1000, "Joe" 500})

(s/def ::scores1 (s/map-of :conform-keys true string? int?))
(s/conform ::scores1 {"Sally" 1000, "Joe" 500})
