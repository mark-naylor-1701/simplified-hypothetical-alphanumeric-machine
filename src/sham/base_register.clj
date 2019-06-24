;; author: Mark W. Naylor
;; file:  base_register.clj
;; date:  2019-Jun-07
(ns sham.base-register
  (:refer-clojure :exclude [name])
  (:require [sham.register.impl :as impl])
  (:import [sham.register.impl Register])
  )

;; Exposes the register binary operations from impl.
(def add impl/add)
(def sub impl/sub)
(def mul impl/mul)
(def div impl/div)

(def ^:private register-names
  (list "nx" "ax" "bx" "cx" "dx" "ex" "fx" "dr" "ip" "sr" "sp" "fr"))

(def ^:private registers-by-name
  (zipmap register-names (range (count register-names))))

(def ^:private registers-by-code
  (zipmap (range (count register-names)) register-names))

(defn register-name
  "Constructor for RegisterName."
  [^String value]
  {:pre [(string? value)]}
  (impl/->RegisterName value))

(defn register-code
  "Constructor for RegisterCode"
  [^Number value]
  {:pre [(number? value)]}
  (impl/->RegisterCode value))

(defn register
  "Constructor for Register."
  [n]
  {:pre [(number? n)]}
  (impl/->Register (unchecked-short n)))

(defn register?
  "Is r a Register?"
  [r]
  (= Register (type r)))

(def registers (vec (repeat (count register-names) (register 0))))


(defn name
  "Regiser name equivalent to its code."
  [^Short code]
  {:pre [(number? code)]}
  (registers-by-code (short code)))

(defn code
  "Register code equivalent to its name."
  [^String name]
  {:pre [(string? name)]}
  (registers-by-name name))

;;------------------------------------------------------------------------------
;; BSD 3-Clause License

;; Copyright (c) 2019, Mark W. Naylor
;; All rights reserved.

;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are met:

;; 1. Redistributions of source code must retain the above copyright notice, this
;;    list of conditions and the following disclaimer.

;; 2. Redistributions in binary form must reproduce the above copyright notice,
;;    this list of conditions and the following disclaimer in the documentation
;;    and/or other materials provided with the distribution.

;; 3. Neither the name of the copyright holder nor the names of its
;;    contributors may be used to endorse or promote products derived from
;;    this software without specific prior written permission.

;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
;; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
;; IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;; DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
;; FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
;; DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
;; SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
;; CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
;; OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;; OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
