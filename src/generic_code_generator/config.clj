(ns generic-code-generator.config
  (:require [clojure.edn :as edn]))

(def database-info
  {:db-hostame (System/getenv "DATABASE_HOSTNAME")
   :db-port (System/getenv "DATABASE_PORT")
   :db-name (System/getenv "DATABASE_NAME")
   :db-username (System/getenv "DATABASE_USERNAME")
   :db-password (System/getenv "DATABASE_PASSWORD")})

(defn read-edn-file
  "Load configuration from the file name as an argument"
  [filename]
  (edn/read-string (slurp filename)))

(defn load-configuration
  (let [common-config {:common-configuration (read-edn-file "../config/common.edn")}
        environment (System/getenv "ENVIRONMENT")]
    (cond
      (= environment "DEV")
      {:common-configuration common-config
       :configuration (read-edn-file "../config/develop.edn")}
      (= environment "PROD")
      {:common-configutation common-config
       :configuration (read-edn-file "../config/production.edn")}
      :else (throw
             (ex-info "Please create the environment values before"
                      {:causes #{:missing-environment-variable}})))))

(defn build-connection-string
  [configuration-info]
  (format (configuration-info :mongo-connection-string)
          (database-info :db-hostame)
          (database-info :db-port)
          (database-info :db-name)
          (database-info :db-username)
          (database-info :db-password)))
          
  
   
  
