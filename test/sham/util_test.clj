;; author: Mark W. Naylor
;; file:  util_test.clj
;; date:  2019-Oct-26
(ns sham.util-test
  (:require [sham.util :refer [legal-number? MIN-NUMBER MAX-NUMBER]]
            [clojure.test :refer [deftest testing is]]))

(def not-legal? (comp not legal-number?))

(deftest legal-number-test
  (testing "Legal SHAM numbers"

    (is (legal-number? 0))
    (is (legal-number? MIN-NUMBER))
    (is (legal-number? MAX-NUMBER))
    (is (legal-number? 1))
    (is (legal-number? -1))

    (is (not-legal? (inc MAX-NUMBER)))
    (is (not-legal? (dec MIN-NUMBER)))

    (is (thrown? java.lang.AssertionError (legal-number? 'apple)))	; Because it's a symbol.
    (is (thrown? java.lang.AssertionError (legal-number? "apple")))	; Because it's a string.
    ))
