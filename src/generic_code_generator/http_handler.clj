(ns generic-code-generator.http-handler
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]))

(defn extract-folder-hierarchi
  "This function extracts the folder hierarchi from json request"
  [request]
  ;TODO
  )



(defroutes app
  (ANY "/" {body :body} (fn [x] "Hello")))

(def handler
  (-> app
      wrap-params))

  
