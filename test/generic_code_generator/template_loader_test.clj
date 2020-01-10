(ns generic-code-generator.template-loader-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.template-loader :refer :all]))

(def home-folder-location (System/getProperty "user.home"))

(def expected-template-loaded "bla")

(def valid-test-input-template "template TODO")

(deftest load-template-test
  (testing "load-template returns exception when template-content argument is empty"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-content cannot be empty or nil"
                          (load-template nil home-folder-location))))
  (testing "load-template returns exception when template-url is empty or nil"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-url cannot be empty or nil"
                          (load-template {:key "value"} nil))))
  (testing "load-template returns exception when template-content is not a map"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-content has to be a map"
                          (load-template home-folder-location "hello")))))
