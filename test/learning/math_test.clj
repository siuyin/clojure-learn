(ns learning.math-test
  (:require [clojure.test :refer [deftest is testing]]
            [learning.math :refer [subtract]]))

(deftest subtract-test
  (testing "Basic subtraction with positive integers"
    (is (= 5 (subtract 10 5)))
    (is (= 0 (subtract 10 10))))

  (testing "Subtraction resulting in negative numbers"
    (is (= -5 (subtract 5 10))))

  (testing "Subtraction with floating point numbers"
    ;; Note: In real scenarios, use an epsilon check for float precision
    (is (= 0.5 (subtract 1.0 0.5))))

  (testing "Subtracting zero"
    (is (= 10 (subtract 10 0)))
    (is (= 0 (subtract 0 0)))))
