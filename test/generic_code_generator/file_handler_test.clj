(ns generic-code-generator.file-handler-test
  (:require [clojure.test :refer :all]
            [generic-code-generator.file-handler :refer :all]))

(def nested_list
  (list 1 2 (list 3 4 5 (list 6 7) 8 9)))

(def nested_vector
  [1 2 [3 4 5 [6 7] 8 9]])

(deftest pangu-flatten-test
  (testing "pangu-flatten is working properly with nested lists"
    (is (= (pangu-flatten nested_list) '(1 2 3 4 5 6 7 8 9))))
  (testing "pangu-flatten is working properly with nested vectors"
    (is (= (pangu-flatten nested_vector) '(1 2 3 4 5 6 7 8 9))))
  (testing "pangu-flatten is working properly with empty lists"
    (is (= (pangu-flatten (list)) (list))))
  (testing "pangu-flatten is working properly with empty vectors"
    (is (= (pangu-flatten (vector)) (vector)))))

(deftest create-folder-hierarchi-tes
  (testing "create-folder-hierarchi does not create a folder with an empty hierarchi representation"
    (is (= (create-folder-hierarchi {}) (empty map)))))
