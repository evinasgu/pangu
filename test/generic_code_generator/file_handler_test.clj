(ns generic-code-generator.file-handler-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [generic-code-generator.file-handler :refer :all]))

(def home-folder-location (System/getProperty "user.home"))

(def test-file-content "<html><body>Hello World!</body></html>")

(def test-template-name "pangu-test-file.html")

(def test-file-location (str
                         home-folder-location
                         "/"
                         test-template-name))

(defn create-test-file [] (spit test-file-location test-file-content))

(defn delete-test-file [] (clojure.java.io/delete-file test-file-location true))

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

(deftest create-file-test
  (testing "create-file function returns false when the file exists in the file system"
    (create-test-file)
    (is (= (create-file {:url home-folder-location
                         :templateName test-template-name
                         :content test-file-content})
           nil)))
  (testing "create file function returns true when the file is created succesfully in the filesystem"
    (delete-test-file)
    (is (= (create-file {:url home-folder-location
                         :templateName test-template-name
                         :content test-file-content})
           nil))))
