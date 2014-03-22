(ns twofourtyeight.core-test
  (:require [clojure.test :refer :all]
            [twofourtyeight.core :refer :all]))

(deftest init
  (testing "Generate a 4x4 grid"
    (is (= (count(start)) 16)))

  (testing "Generate a 4x4 grid that contains 3 numbers"
    (is (= (count(filter pos?(start))) 3)))
)

(deftest moving

  (testing "Move left"
    (is (= (left [0 0 4 0 2 0 0 0 0 4 0 0 0 0 2 0])  [4 0 0 0 2 0 0 0 4 0 0 0 2 0 0 0] ))
  )

  (testing "Move right"
    (is (= (right [0 0 4 0 2 0 0 0 0 4 0 0 0 0 2 0]) [0 0 0 4 0 0 0 2 0 0 0 4 0 0 0 2] ))
  )

  (testing "Move up"
    (is (= (up [0 0 4 0   2 0 0 0   0 4 0 0   0 0 0 2]) [2 4 4 2  0 0 0 0  0 0 0 0  0 0 0 0] ))
  )

  (testing "Move down"
    (is (= (down [0 0 4 0   2 0 0 0   0 4 0 0   0 0 0 2]) [0 0 0 0  0 0 0 0  0 0 0 0  2 4 4 2] ))
  )
)

(deftest calculating

  (testing "Adding numbers when moving left"
    (is (= (left [0 0 4 4 2 2 0 0 0 4 4 0 0 0 2 2])  [8 0 0 0 4 0 0 0 8 0 0 0 4 0 0 0] ))
  )
)
