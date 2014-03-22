(ns bowling.core-test
  (:require [clojure.test :refer :all]
            [bowling.core :refer :all]))

(deftest test-rolling
  (testing "Rolling."
    (is (= (roll [[1 2] [3]] 3) [[1 2] [3 3]])))

  (testing "Rolling with last strike"
    (is (= (roll [[1 2] [10]] 3) [[1 2] [10] [3]])))

  (testing "Rolling a new window"
    (is (= (roll [[1 2]] 3) [[1 2] [3]])))

  (testing "Rolling first ball"
    (is (= (roll [] 3) [[3]]))))


(deftest test-reduceScores
  (testing "Reducing two throws."
    (is (= (reduceScores [1 2]) 3 )))

  (testing "Reducing one throw"
    (is (= (reduceScores [6]) 6 ))))

(deftest test-roundRolls
  (testing "Getting frame at -1"
    (is (= (roundRolls [[1 2]] -1) [0 0] )))

  (testing "Reducing one throw"
    (is (= (roundRolls [[1 2] [3 4]] 0) [1 2] ))))

(deftest test-scoreRound
  (testing "Scoring first frame"
    (is (= (scoreRound [1 2] [0 0] [0 0]) 3 )))

  (testing "Scoring frame after strike"
    (is (= (scoreRound [1 2] [10] [0 0]) 13 )))

  (testing "Scoring frame after spare"
    (is (= (scoreRound [1 2] [5 5] [0 0]) 13 )))

  (testing "Scoring frame after spare two back"
    (is (= (scoreRound [1 2] [5 5] [5 5]) 13 )))

  (testing "Scoring frame after strike two back"
    (is (= (scoreRound [1 2] [5 5] [10]) 13 )))

  (testing "Scoring frame after two strikes"
    (is (= (scoreRound [1 2] [10] [10]) 23 )))

  (testing "Scoring frame after two strikes"
    (is (= (scoreRound [10] [10] [4 4]) 20 )))

  (testing "Scoring strike after two non-strikes"
    (is (= (scoreRound [10] [3 4] [4 4]) 10 )))

  (testing "Scoring three strikes"
    (is (= (scoreRound [10] [10] [10]) 30 )))

  (testing "Scoring two spares"
    (is (= (scoreRound [3 7] [5 5] [1 3]) 20 )))
)

(deftest test-scoregame
  (testing "Score perfect game"
    (is (= (scoreGame [[10] [10] [10] [10] [10]]) 120 )))

  (testing "Score not so perfect game"
    (is (= (scoreGame [[1 3] [4 4] [2 3]]) 17)))

  (testing "Score gutter game"
    (is (= (scoreGame [[0 0] [0 0] [0 0]]) 0)))

  (testing "Score random game"
    (is (= (scoreGame [[1 8] [8 2] [10] [10] [4 4]]) 87)))

)
