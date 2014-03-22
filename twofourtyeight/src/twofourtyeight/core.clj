(ns twofourtyeight.core)

(def w 4)
(def s 16)

(defn number "Generate start number" []
  (if( > (rand) 0.5 ) 4 2)
)

(defn zeros [] (vec(repeat w 0)))
(defn start "Generate grid" []
  (shuffle (flatten(conj (repeatedly 3 number) (repeat (- s 3) 0))))
)

(defn clearzeros "Removes zeros" [arr] (remove #(= 0 %) arr))

(defn left "Moves grid left" [grid]

  ;(mapcat (fn [e] (take w (flatten (conj (vec (clearzeros e)) (zeros) )))) (partition w grid))

  (mapcat
    (fn [e]
      (take w
        (flatten
          (conj
            (vec (clearzeros e))
            (zeros)
          )
        )
      )
    )
    (partition w grid)
  )


;  (map-indexed
;    (fn [i e]
;      (if (== (nth col (+ i 1) 0) e)
;        (* e 2)
;        e
;      )
;    )
;    col
;  )

)

(defn right "Moves grid right" [grid]
  (mapcat
    (fn [e]
      (take-last w
        (flatten
          (conj
            (zeros)
            (vec (clearzeros e))
          )
        )
      )
    )
    (partition w grid)
  )
)

(defn column [i] (mod i 4))

(defn row [i] (int (/ i 4)))

(defn columnup "Moves grid up" [grid col]
  (take w
    (flatten
      (conj
        (vec
          (clearzeros
            (take-nth w
              (take-last
                (- s col)
                grid
              )
            )
          )
        )
        (zeros)
      )
    )
  )
)

(defn up [grid]
  (map-indexed
    (fn [i e]
      (nth
        (columnup grid (column i))
        (row i)
      )
    )
    grid
  )
)

(defn columndown "Moves grid up" [grid col]
  (take-last w (flatten (conj (zeros) (vec (clearzeros (take-nth w (take-last (- s col) grid)))))))
)

(defn down [grid]
   (map-indexed (fn [i e] (nth (columndown grid (column i)) (row i))) grid)
)
