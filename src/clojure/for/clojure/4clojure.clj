(ns for.clojure.4clojure)

(comment
  "Just don't know how to solve these prolems.")

(defn number-maze-106 [start end]
  (loop [current [start] steps 1]
    (if (some #{end} current)
      steps
      (recur (flatten
              (map (fn [x]
                       (if x
                         [(* 2 x) (+ 2 x) (if (even? x) (/ x 2))]))
                   current))
             (inc steps)))))


(defn -main
  []
  (println (number-maze-106 3 12)))
