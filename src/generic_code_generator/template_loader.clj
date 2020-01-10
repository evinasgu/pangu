(ns generic-code-generator.template-loader
  (:require [clojure.tools.logging :as log]
            [struct.core :as st]))

(use 'selmer.parser)

(defn load-template
  "This function takes a file located in template-url and make the substitution for the data presented in template-content argument"
  [template-content template-url]
  (cond
    (empty? template-content) (throw
                               (ex-info "template-content cannot be empty or nil"
                                        {:causes #{:empty-template-content}
                                         :actual-value {:value template-content}}))
    (empty? template-url) (throw
                           (ex-info "template-url cannot be empty or nil"
                                    {:causes #{:empty-template-url}
                                     :actual-value {:value template-url}}))
    (not (map? template-content)) (throw
                                    (ex-info "template-content has to be a map"
                                             {:causes #{:incompatible-type-template-content}
                                              :actual-value {:value template-content}}))
    :else (render-file template-url template-content)))             
