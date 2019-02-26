(ns generic-code-generator.template-loader
  :require [clojure.tools.logging :as log])

(use 'selmer.parser)

(defn load-template
  [template-content template-url]
  (log/info (str "Loading the template: " template-url))
  (render-file template-url template-content))
  
  
