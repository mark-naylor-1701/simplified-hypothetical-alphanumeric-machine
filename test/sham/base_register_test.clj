;; author: Mark W. Naylor
;; file:  base_register_test.clj
;; date:  2019-Aug-08
(ns sham.base-register-test
  (:require [sham.register.impl :as sri]
            [sham.base-register :as sut]
            [clojure.test :refer :all]))

(deftest register-tests

  (testing "Two register operations"
    (let [three (sri/register 3) four (sri/register 4)]
      (testing "Addition"
        (is (= (:value (sut/plus three four)) 7)))
      (testing "Subtraction"
        (is (= (:value (sut/minus three four)) -1)))
      (testing "Multiplication"
        (is (= (:value (sut/times three four)) 12)))
      (testing "Division"
        (is (= (:value (sut/divided-by three four)) 0))
        (is (= (:value (sut/divided-by four three)) 1)))))

  (testing "Register number operations"
    (let [three (sri/register 3) four 4 two 2]
      (testing "Addition"
        (is (= (:value (sut/plus three four)) 7)))
      (testing "Subtraction"
        (is (= (:value (sut/minus three four)) -1)))
      (testing "Multiplication"
        (is (= (:value (sut/times three four)) 12)))
      (testing "Division"
        (is (= (:value (sut/divided-by three four)) 0))
        (is (thrown? ArithmeticException (sut/divided-by three 0)))
        )))

  (testing "Register only operations"
    (let [three (sri/register 3)]
      (testing "Increment"
        (is (= (:value (sut/add-1 three)) 4)))
      (testing "Decrement"
        (is (= (:value (sut/sub-1 three)) 2)))
      (testing "Negation"
        (is (= (:value (sut/neg three)) -3))
        (is (= (-> three sut/neg sut/neg :value) 3)))
      ))
  )
