(ns generic-code-generator.file-handler
  (:require [clojure.tools.logging :as log]))

(def valid-path-regex #"^(/[^/ ]*)+/?$")

(defn pangu-flatten
  "pangu-flatten make a recursive flat operation over an iterable core structure"
  [x]
  (filter (complement sequential?)
          (rest (tree-seq sequential? seq x))))

(defn build-folder-list
  "Given a folder hierarchi, this function build a list of folders
  to be created in the file system"
  [folder-hierarchi]
  ;TODO
  )

(defn malformed-location?
  "Given a location, this function verifies if the location is malformed"
  [location]
  (not (empty (re-matches valid-path-regex location))))

(defn create-one-folder
  "Given a location, this function creates a folder in that location.
  In case of nil, empty location or non valid location this throws an error"
  [location]
  (if (or (empty? location) (malformed-location? location))
    (throw
     (ex-info "Location is not valid"
              {:causes #{:empty-location :malformed-location}
               :actual-value {:value location}}))
    (.mkdir (java.io.File. location))))

(defn create-folder-hierarchi
  "This function build a structured folder hierarchi in the disk"
  [hierarchi-representation]
  (if (empty? hierarchi-representation)
    (empty map)
    nil)) ;TODO operation with no empty data
