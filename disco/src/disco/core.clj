(ns disco.core)

(ns for-the-glory-of-art.core
  (:require [quil.core :refer :all]))

(defn setup []
  (smooth)
  (frame-rate 2)
  (background 0))

(def stroke-val (atom 0)) ; on/off switch for light

(def rainbow-colors [[255 0 0]
                     [255 127 0]
                     [255 255 0]
                     [0 255 0]
                     [0 0 255]
                     [75 0 130]
                     [143 0 255]])

(def rows 7)

(defn draw []
  (swap! stroke-val
         (fn [x]
           (mod (inc x)
                rows)))
  (doseq [r (range rows)]
   (doseq [c (range
              (count rainbow-colors))]
     (let [col (nth rainbow-colors c)
           i   (* 40
                  (inc c))]
       (if (= r @stroke-val)
         (apply stroke col)
         (stroke 0 0 0))
       (stroke-weight 2)
       (fill 0 0 0)
      
       (let [x    i
             y    (* 40 (inc r))]
         (rect x y 20 30))))))

(defsketch example
  :title "Oh so many grey circles"
  :setup setup
  :draw draw
  :size [340 340]
  :renderer :opengl)

