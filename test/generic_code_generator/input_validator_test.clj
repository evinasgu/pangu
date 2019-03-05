(ns generic-code-generator.input-validator-test
  (:require [clojure.test :refer :all]
            [generic-code-generator.input-validator :refer :all]
            [struct.core :as st]))

(def +test-scheme+
  {:name [st/required st/string]
   :year [st/required st/number]})

(def test-valid-input
  {:name "Enyert Vinas"
   :year 1936})

(def test-non-valid-input
  {:name "Enyert Vinas"
   :year "1936"})

(def expected-exception-vector-string
  #"[{:year \"must be a number\"} {:name \"Enyert Vinas\"}]")

(def load-template-valid-input
  {:template-content "Hello World! {{ name }}"
   :template-url "/home/machine_name/hello.txt"})

(def load-template-non-valid-input-by-type
  {:template-content "Hello World! {{ name }}"
   :template-url 1})

(def load-template-non-valid-input-by-required-nil
  {:template-content "Hello World! {{ name }}"
   :template-url nil})

(def load-template-non-valid-input-by-required-empty
  {:template-content ""
   :template-url "/home/machine_name/hello.txt"})

(def expected-exception-vector-string-validate-load-template-by-type
  #"[{:template-url \"must be a string\"} {:template\-content \"Hello World! {{ name }}\"}]")

(def expected-exception-vector-string-validate-load-template-by-nil
  #"[{:template-url \"this field is mandatory\"} {:template\-content \"Hello World! {{ name }}\"}]")

(def expected-exception-vector-string-validate-load-template-by-nil
  #"[{:template\-content \"this field is mandatory\"} {:template\-url \"\/home\/machine_name\/hello.txt\"}]")

(deftest validate-scheme-test
  (testing "validate-scheme function verifies succesfully a valid input"
    (is (= test-valid-input (validate-scheme
                             +test-scheme+
                             test-valid-input))))
  (testing "validate-scheme function throws an exception when input is invalid"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo expected-exception-vector-string
                          (validate-scheme
                           +test-scheme+
                           test-non-valid-input)))))

(deftest validate-load-template-input-test
  (testing "validate-load-template-input function verifies succesfully a valid input"
    (is (= load-template-valid-input (validate-load-template-input
                                      load-template-valid-input))))
  (testing "validate-load-template-input function throws an error when receives an invalid input by type"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo expected-exception-vector-string-validate-load-template-by-type
                          (validate-load-template-input
                           load-template-non-valid-input-by-type))))
  (testing "validate-load-template-input throws an error when receives a nil parameter"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo expected-exception-vector-string-validate-load-template-by-nil
                          (validate-load-template-input
                           load-template-non-valid-input-by-required-nil))))
  (testing "validate-load-template-input function throws an error when receives an empty parameter"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo expected-exception-vector-string-validate-load-template-by-nil
                          (validate-load-template-input
                           load-template-non-valid-input-by-required-empty)))))
                           
                           
  
