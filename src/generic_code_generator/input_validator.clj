(ns generic-code-generator.request-validator
  :require [[clojure.tools.logging :as log]
            [struct.core :as st]])

(def +load-template-scheme+
  {:template-content [st/required st/string]
   :template-url [st/required st/string]})

(defn validate-scheme
  [scheme input]
  (log/info (str "Validating: " input))
  (if (st/valid? input scheme)
    input
    (throw
     (let [causes (get (-> input (st/validate scheme)) 0)
           error-message (str causes)]
       (ex-info error-message
                {:causes causes
                 :actual-value {:value input}})))))

(defn validate-load-template-input
  [input]
  (validate-scheme input +load-template-scheme+))

