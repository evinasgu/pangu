(ns generic-code-generator.file-handler-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.file-handler :refer :all]))

(def home-folder-location (System/getProperty "user.home"))

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

(deftest create-folder-hierarchi-test
  (testing "create-folder-hierarchi throws ex-data when location is empty"
    (is (= (create-folder-hierarchi {}) (empty map)))))


