(ns generic-code-generator.template-loader-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.template-loader :refer :all]))

(def expected-template-loaded "bla")

(def valid-test-input-template "template TODO")

(deftest load-template-test)
  ;(testing "load-template function load succesfully a valid template"
  ;  (is (= (load-template valid-test-input-template) expected-template-loaded;))))
