(ns generic-code-generator.file-handler
  (:require [clojure.tools.logging :as log]))

(def valid-path-regex #"^(/[^/ ]*)+/?$")

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
    (.mkdirs (java.io.File. location))))

(defn create-folder-hierarchi
  "This function build a structured folder hierarchi in the disk"
  [hierarchi-representation]
  (if (empty? hierarchi-representation)
    (empty map)
    (map create-one-folder hierarchi-representation)))

(defn create-file
  "This function create template files in their respective folders"
  [file-representation]
  (let [filepath (str (get file-representation :url) "/" (get file-representation :templateName))
        content (get file-representation :content)]
    (spit filepath content)))

