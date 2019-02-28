(ns generic-code-generator.request-validator
  :require [clojure.tools.logging :as log])

(defn validate-fn
  [predicate-fn field-coll]
  (log/debug field-col)
  (not-any? predicate-fn field-coll))

(defn validate-empty-fields
  [field-coll]
  (validate-fn empty? field-coll))
