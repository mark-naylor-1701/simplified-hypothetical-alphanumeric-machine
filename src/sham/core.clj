(ns sham.core
  (:gen-class)
  (:require [sham.register :refer [register add sub mul div
                                   ]])
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [ax (register 3) bx (register 7)]
    (println (str "ax: " ax))
    (println (str "bx: " bx))
    (println (add ax bx))
    (println (sub ax bx))
    (println (mul ax bx))
    (println (div ax bx))
    )

  )
