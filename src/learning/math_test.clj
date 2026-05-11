(ns learning.math-test
  (:require [clojure.test :refer [deftest is testing run-tests]]))

;; The function we are testing (usually this would be in a separate source file)
(defn subtract
  "Subtracts y from x."
  [x y]
  (- x y))

(deftest subtract-test
  (testing "Basic subtraction with positive integers"
    (is (= 5 (subtract 10 5)))
    (is (= 0 (subtract 10 10))))

  (testing "Subtraction resulting in negative numbers"
    (is (= -5 (subtract 5 10))))

  (testing "Subtraction with floating point numbers"
    ;; Note: In real scenarios, use an epsilon check for float precision
    (is (= 0.5 (subtract 1.0 0.5)))))

(run-tests)
