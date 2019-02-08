(defproject generic-code-generator "0.1.0-SNAPSHOT"
  :description "Code generator for different languages"
  :url "http://example.com/FIXME"
  :plugins [[lein-ring "0.12.2"]]
  :ring {:handler generic-code-generator.core/http-handler, :open-browser false}
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [liberator "0.15.1"]
                 [compojure "1.6.0"]
                 [ring/ring-core "1.6.3"]
                 [selmer "1.12.5"]
                 [cheshire "5.8.1"]
                 [org.clojure/tools.logging "0.4.1"]]
  :profiles
  {:uberjar
   {:aot :all}
   :production
   {:ring {:stacktraces? false, :autoreload false}}
   :dev
   {:dependencies [[ring/ring-mock "0.3.2"] [ring/ring-devel "1.7.1"]],
    :ring {:stacktraces? true}}}
  :repl-options {:init-ns generic-code-generator.core})
