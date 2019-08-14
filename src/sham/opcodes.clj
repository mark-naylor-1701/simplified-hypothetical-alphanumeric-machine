;; author: Mark W. Naylor
;; file:  opcodes.clj
;; date:  2019-Aug-13

(ns sham.opcodes
  (:require [clojure.set :as set])
  (:require sham.util)
  (:refer sham.util :only [code])
  )

(def one-byte ["nop" "random" "return" "terminate" "trace_on" "trace_off" "enable" "disable"])
(def one-code (set one-byte))

(def two-byte ["negate" "increment" "decrement" "clear" "push" "pop" "move"
               "add" "subtract" "multiply" "divide" "compare"])
(def two-code (set two-byte))

(def four-byte-direct ["in" "out" "branch" "call" "fetch_byte" "fetch_word" "store_byte"
                       "store_word" "stream" "testandset" "interrupt"])

(def four-byte-indirect (vec (map #(str "-" %) four-byte-direct)))

(def four-byte (concat four-byte-direct four-byte-indirect))
(def four-code (set four-byte))

(def code-names (vec (concat one-byte two-byte four-byte)))

(defn code-zone
  "Determines the byte size of the operation based upon the opcode."
  [num-code]
  {:pre [(number? num-code)]}
  (when-let [str-code (code-names (int num-code))]
    (cond
      (one-code str-code) 1
      (two-code str-code) 2
      (four-code str-code) 4)))

(def opcode-code (partial code code-names "opcode"))
