(ns generic-code-generator.file-handler-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.file-handler :refer :all]))

(def nested_list
  (list 1 2 (list 3 4 5 (list 6 7) 8 9)))

(def nested_vector
  [1 2 [3 4 5 [6 7] 8 9]])

(def home-folder-location (System/getProperty "user.home"))

(deftest pangu-flatten-test
  (testing "pangu-flatten is working properly with nested lists"
    (is (= (pangu-flatten nested_list) '(1 2 3 4 5 6 7 8 9))))
  (testing "pangu-flatten is working properly with nested vectors"
    (is (= (pangu-flatten nested_vector) '(1 2 3 4 5 6 7 8 9))))
  (testing "pangu-flatten is working properly with empty lists"
    (is (= (pangu-flatten (list)) (list))))
  (testing "pangu-flatten is working properly with empty vectors"
    (is (= (pangu-flatten (vector)) (vector)))))

(deftest malformed-location?-test
  (testing "malformed-location? returns true when location is empty"
    (is (= (malformed-location? "") true)))
  (testing "malformed-location? return false when a location is well formed"
    (is (= (malformed-location? (str home-folder-location "/" "welformed")) false))))

(deftest create-one-folder-test
  (testing "create-one-folder throws an exception when location is nil"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Location is not valid"
                          (create-one-folder (empty str)))))
  (testing "create-one-folder throws an exception when location is empty("") string"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Location is not valid"
                          (create-one-folder ""))))
  (testing "create-one-folder throws an exception when location is malformed"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Location is not valid"
                          (create-one-folder "malformed"))))
  (testing "create-one-folder is false when the folder to be created exists in the file system"
    (is (= (create-one-folder home-folder-location) false))))

(deftest create-folder-hierarchi-tes
  (testing "create-folder-hierarchi throws ex-data when location is empty"
    (is (= (create-folder-hierarchi {}) (empty map)))))
