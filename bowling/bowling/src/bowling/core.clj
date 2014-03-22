(ns bowling.core)

(def roll(fn [scores point]
  (def lastRoll (last scores))
  (if (and (== (count lastRoll) 1) (not= (get lastRoll 0) 10))
    (conj
      (vec (butlast scores))
      (conj lastRoll point)
    )
    (conj scores [point])
  )
))

(def reduceScores(fn [scores] (reduce + scores) ))

(def roundRolls (fn [scores index]
  (if (< index 0)
    [0 0]
    (get scores index)
  )
))

(def scoreRound (fn [round last lastlast]
  (if (and (== (count last) 1) (== (count lastlast) 1))
    (+
      (reduceScores last)
      (reduceScores lastlast)
      (reduceScores round)
    )
    (if (== (reduceScores last) 10)
      (+
        (reduceScores last)
        (reduceScores round)
      )
      (reduceScores round)
    )
  )
))

(def score (fn [scores index]
    (if (< index (count scores))
      (+
        (score scores (+ index 1))
        (scoreRound
          (get scores index)
          (roundRolls scores (- index 1))
          (roundRolls scores (- index 2))
        )
      )
      0
    )
))

(def scoreGame (fn [scores] (score scores 0)))

(def -main (fn [& args]
  (def scores [])
  (doseq [line (line-seq (java.io.BufferedReader. *in*))]
    (if (==(count scores) 10)
      (println (scoreGame scores))
      (do (def scores (roll scores (Integer/parseInt line))) (println scores))
    )
  )

))
