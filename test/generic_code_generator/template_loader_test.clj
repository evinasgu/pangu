(ns generic-code-generator.template-loader-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.template-loader :refer :all]))

(def valid-template-file-url "../resources/valid_template.html")

(def valid-template-content {:header_tag "This is a header example tag"
                       :paragraph_tag "This is a paragraph example tag"})

(def valid-test-input-template "template TODO")

(deftest load-template-test
  (testing "load-template throws an error when template-content is empty"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-content cannot be empty or nil"
                          (load-template {} "/this_doesnt_exist")))
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-content cannot be empty or nil"
                          (load-template nil "/this_doent_exist")))
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-url cannot be empty or nil"
                          (load-template {:key "value"} "")))
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"template-url cannot be empty or nil"
                          (load-template {:ket "value"} nil)))))
