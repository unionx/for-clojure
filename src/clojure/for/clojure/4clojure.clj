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

;; 125
(fn []
  (let [lst ["(fn [] (let [lst "
             "] (str (first lst) lst (last lst))))"]]
    (str (first lst)
         lst
         (last lst))))

;; 101 Levenshtein Distance
;; better solution: https://github.com/khotyn/4clojure-answer/blob/master/101-levenshtein-distance.clj
(defn levenshtein-distance [s1 s2]
  (if (or (empty? s1)
          (empty? s2))
    (max (count s1) (count s2))
    (let [c1 (first s1)
          c2 (first s2)
          cost (if (= c1 c2) 0 1)]
      (min (inc (levenshtein-distance (rest s1) s2))
           (inc (levenshtein-distance s1 (rest s2)))
           (+ (levenshtein-distance (rest s1) (rest s2)) cost)))))

(defn levenshtein-distance-better [s1 s2]
  (let [len1 (inc (count s1))
        len2 (inc (count s2))
        total-steps (dec (* len1 len2))
        max-num 999999999999999]
    (if (= 0 total-steps)
      0
      (letfn [(calc-fn [result-map step-count]
                (if (= 0 step-count)
                  (calc-fn {[0 0] 0} 1)
                  (let [j (quot step-count len1)
                        i (rem step-count len1)
                        v1 (if (nil? (result-map [(dec i) j]))
                             max-num
                             (inc (result-map [(dec i) j])))
                        v2 (if (nil? (result-map [i (dec j)]))
                             max-num
                             (inc (result-map [i (dec j)])))
                        v3 (if (or (= 0 i)
                                   (= 0 j))
                             max-num
                             (if (= (nth s1 (dec i))
                                    (nth s2 (dec j)))
                               (result-map [(dec i) (dec j)])
                               (inc (result-map [(dec i) (dec j)]))))
                        min-v
                        (do
                          (min v1 v2 v3))]
                    (if (= total-steps step-count)
                      min-v
                      (recur (assoc result-map [i j] min-v) (inc step-count))))))]
        (calc-fn nil 0)))))

;; #89 Graph Tour
(defn graph-tour [graph-vector]
  nil)

;; #119 Tic-Tac-Toe
(defn tic-tac-toe [one-piece game-board]
  (letfn [(win-game? [index]
            (if (not= :e (get-in game-board index))
              false
              (let [new-board (assoc-in game-board index one-piece)
                    test-index-list [[[0 0] [1 0] [2 0]]
                                     [[0 1] [1 1] [2 1]]
                                     [[0 2] [1 2] [2 2]]
                                     [[0 0] [1 1] [2 2]]
                                     [[0 2] [1 1] [2 0]]]
                    test-lines (map (fn [line-index]
                                      (map (fn [piece-index]
                                             (get-in new-board piece-index))
                                           line-index))
                                    test-index-list)
                    total-lines (remove #{'(:e :e :e)} (concat new-board test-lines))]
                (some #(= 1 (count (set %))) total-lines))))]
    (let [match-index (for [x (range 3)
                            y (range 3)]
                        (when (win-game? [x y]) [x y]))]
      (set (filter (complement nil?) match-index)))))

;; answer: #{}
(tic-tac-toe :x [[:x :x :o]
                 [:e :e :e]
                 [:e :e :e]])

;; answer: #{[2 2] [1 1]}
(tic-tac-toe :o [[:x :x :o]
                 [:o :e :o]
                 [:x :e :e]])

;; not my answer
;; crossword puzzle 111
;; graph tour 89
;; tree parenting 130
;; Latin Square Slicing 152
;; Analyze Reversi 124
;; Squares Squared 138
;; Love Triangle 127
;; Veitch, Please! 140
;; Language of a DFA 164



(defn -main
  []
  (println (levenshtein-distance-better "" "")))
