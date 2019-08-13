;; author: Mark W. Naylor
;; file:  opcodes.clj
;; date:  2019-Aug-13

(ns sham.opcodes
  (:require sham.util)
  (:refer sham.util :only [code])
  )

(def one-byte ["nop" "random" "return" "terminate" "trace_on" "trace_off" "enable" "disable"])

(def two-byte ["negate" "increment" "decrement" "clear" "push" "pop" "move"
               "add" "subtract" "multiply" "divide" "compare"])

(def four-byte-direct ["in" "out" "branch" "call" "fetch_byte" "fetch_word" "store_byte"
                       "store_word" "stream" "testandset" "interrupt"])

(def four-byte-indirect (vec (map #(str "-" %) four-byte-direct)))

(def four-byte (concat four-byte-direct four-byte-indirect))

(def code-names (concat one-byte two-byte four-byte))

(def opcode-code (partial code code-names "opcode"))
