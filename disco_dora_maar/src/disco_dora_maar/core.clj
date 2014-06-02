(ns disco-dora-maar.core
  (:require [quil.core :refer :all]))

(def img (atom nil))

(defn setup []
  (smooth)
  (frame-rate 2)
  (background 0)
  (swap! img (fn [x]
               (load-image "disco_dora_maar_seated_mask.png"))))

(def stroke-val (atom 0)) ; on/off switch for lights

(def rainbow-colors [[255 0 0]
                     [255 127 0]
                     [255 255 0]
                     [0 255 0]
                     [0 0 255]
                     [75 0 130]
                     [143 0 255]])

(def rect-x 5)

(def rect-y 10)

(def rows 7)

(defn draw []
  (swap! stroke-val
         (fn [x]
           (mod (inc x)
                17)))
  (doseq [r (range (/ 700 rows))]
    (doseq [c
            (range
             (* 4
                (count rainbow-colors)))]
     (let [col (nth rainbow-colors (mod c 7))
           i   (* 20
                  (inc c))]
       (if (= r @stroke-val)
         (apply stroke col)
         (stroke 0 0 0))
       (stroke-weight 2)
       (fill 0 0 0)
      
       (let [x    i
             y    (* 40 (inc r))]
         (rect x y rect-x rect-y))))
    (image @img 0 0)))

(defsketch example
  :title "Oh so many grey circles"
  :setup setup
  :draw draw
  :size [500 700]
  :renderer :opengl)
