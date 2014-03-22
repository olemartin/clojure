(ns workshop.core)

(def size 100)

(def new-grid (fn [] (repeatedly size #(rand-int 2))))

(def offset (fn [x y] ( +(* y (/ size 10)) x )))

(def outofbounds (fn [x y]
  (or
    (< x 0)
    (< y 0)
    (>= y (/ size 10 ))
    (>= x (/ size 10 ))
  )
))

(def grid-entry (fn [grid x y]
  (if (outofbounds x y )
    0
    (nth grid (offset x y))
  )
))

(def coord (fn [i]
  [ (mod i 10) (int (/ i 10 ))]
))


(def alive? (fn [grid x y](== 1 (grid-entry grid x y))))

(def neighbours(fn [x y]
  (vector
    (vector (dec x) y)
    (vector (inc x) y)
    (vector x (inc y))
    (vector x (dec y))
    (vector (inc x) (dec y))
    (vector (dec x) (dec y))
    (vector (inc x) (inc y))
    (vector (dec x) (inc y))
  )
))

(def alives (fn [grid x y]
  (count (filterv (fn[x](alive? grid (get x 0) (get x 1))) (neighbours x y)))))

(def should-be-alive (fn [grid x y]
  (if
    (or
      (rule1 (alives grid x y) (grid-entry grid x y))
      (rule2 (alives grid x y) (grid-entry grid x y))
      (rule3 (alives grid x y) (grid-entry grid x y))
      (rule4 (alives grid x y) (grid-entry grid x y))
    )
    1
    0
    )
))

;Any live cell with fewer than two live neighbours dies, as if caused by under-population.
(def rule1 (fn [alive-neighbours current] (and (== current 1) (or(== alive-neighbours 1)(== alive-neighbours 2)))))

;Any live cell with two or three live neighbours lives on to the next generation.
(def rule2 (fn [alive-neighbours current] (and (== current 1) (or(== alive-neighbours 2)(== alive-neighbours 3)))))

;Any live cell with more than three live neighbours dies, as if by overcrowding.
(def rule3 (fn [alive-neighbours current] (and (== current 1) (<= alive-neighbours 3))))

;Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
(def rule4 (fn [alive-neighbours current] (and (== current 0)(== alive-neighbours 3))))


(def new-value (fn [i]
  ( should-be-alive
    grid
    (get (coord i) 0)
    (get (coord i) 1)
  )
))

(def generate (fn [grid]
  ( map
    (fn [i]
      ( should-be-alive
        grid
        (get (coord i) 0)
        (get (coord i) 1)
      )
    )
    (range 100))
))



(def printe (fn [grid]
  (def grid (generate grid))
  (doseq [i (partition 10 grid)] (println i))
))

(def run (fn [grid]
    (printe grid)
    (Thread/sleep 8)
    (run grid)
))
