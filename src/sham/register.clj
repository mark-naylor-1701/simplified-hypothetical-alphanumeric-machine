;; author: Mark W. Naylor
;; file:  register.clj
;; date:  2019-Jun-07
(ns sham.register)

(def ^:private register-names
  (list "nx" "ax" "bx" "cx" "dx" "ex" "fx" "dr" "ip" "sr" "sp" "fr"))

(def ^:private registers-by-names
  (zipmap register-names (range (count register-names))))

(def ^:private registers-by-code
  (zipmap (range (count register-names)) register-names))

(defrecord RegisterName [^String value])
(defn register-name
  "Constructor for RegisterName."
  [^String value]
  {:pre [(string? value)]}
  (when (string? value)
    (->RegisterName value)))

(defrecord RegisterCode [^Number value])
(defn register-code
  "Constructor for RegisterCode"
  [^Number value]
  {:pre [(number? value)]}
  (when (number? value)
      (->RegisterCode value)))

(defrecord Register [^Short value])
(defn register
  "Constructor for Register."
  [n]
  {:pre [(number? n)]}
  (->Register (unchecked-short n)))

(def registers (vec (repeat (count register-names) (register 0))))


(defn name
  "Regiser name equivalent to its code."
  [^Short code]
  (registers-by-code code))

(defn code
  "Register code equivalent to its name."
  [^String name]
  (registers-by-names name))


(defprotocol RegisterOps
  (add [^Register r1 ^Register r2])
  (sub [^Register r1 ^Register r2])
  (mul [^Register r1 ^Register r2])
  (div [^Register r1 ^Resister r2])
  (Register? [r]))


(extend-type Register
  RegisterOps

  (add [r1 r2] (register (+' (:value r1) (:value r2))))
  (sub [r1 r2] (register (-' (:value r1) (:value r2))))
  (mul [r1 r2] (register (*' (:value r1) (:value r2))))
  (div [r1 r2] (register (/ (:value r1) (:value r2))))
  (Register? [r] (= Register (type r))))
