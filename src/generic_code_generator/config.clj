(ns generic-code-generator.config
  (:require [clojure.edn :as edn]
            [monger.core :as mg]))

(def database-info
  {:db-hostname (System/getenv "DATABASE_HOSTNAME")
   :db-port (System/getenv "DATABASE_PORT")
   :db-name (System/getenv "DATABASE_NAME")
   :db-username (System/getenv "DATABASE_USERNAME")
   :db-password (System/getenv "DATABASE_PASSWORD")})

(defn read-edn-file
  "Load configuration from the file name as an argument"
  [filename]
  (edn/read-string (slurp filename)))

(defn load-configuration
  []
  (let [common-config {:common-configuration (read-edn-file "resources/common-config.edn")}
        environment (System/getenv "ENVIRONMENT")]
    (cond
      (= environment "DEV")
      {:common-configuration common-config
       :configuration (read-edn-file "resources/dev/config.edn")}
      (= environment "PROD")
      {:common-configutation common-config
       :configuration (read-edn-file "resources/prod/config.edn")}
      :else (throw
             (ex-info "Please create the environment values before"
                      {:causes #{:missing-environment-variable}})))))

(defn build-mongo-connection-string
  [configuration-info]
  (format (configuration-info :mongo-connection-string)
          (database-info :db-username)
          (database-info :db-password)
          (database-info :db-hostname)
          (database-info :db-port)
          (database-info :db-name)))

(defn create-mongo-connection
  [configuration-info]
  (let [uri (build-mongo-connection-string configuration-info)]
    (mg/connect-via-uri uri)))
