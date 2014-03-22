(ns workshop.core)

(def x 5)

(def foo (fn [] (println "Hello world")))

(def foo1 (fn [] (println x)))

(def foo2 (fn [abc] (println abc)))

(def foo3 (fn [abc] (println (inc abc))))
