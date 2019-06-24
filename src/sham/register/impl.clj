;; author: Mark W. Naylor
;; file:  impl.clj
;; date:  2019-Jun-16
(ns sham.register.impl
  (:use [clojure.string :only [lower-case]]))

(defrecord RegisterName [^String value])
(defn register-name
  "Constructor for RegisterName."
  [^String value]
  {:pre [(string? value)]}
  (-> value lower-case ->RegisterName))

(defrecord RegisterCode [^Number value])
(defn register-code
  "Constructor for RegisterCode"
  [^Number value]
  {:pre [(number? value)]}
  (->RegisterCode value))

(defrecord Register [^Short value])
(defn register
  "Constructor for Register."
  [n]
  {:pre [(number? n)]}
  (->Register (unchecked-short n)))

(defn register?
  "Is r a Register?"
  [r]
  (= Register (type r)))

(defprotocol RegisterOps
  (add [^Register r1 ^Register r2])
  (sub [^Register r1 ^Register r2])
  (mul [^Register r1 ^Register r2])
  (div [^Register r1 ^Resister r2]))


(extend-type Register
  RegisterOps

  (add [r1 r2] (register (+' (:value r1) (:value r2))))
  (sub [r1 r2] (register (-' (:value r1) (:value r2))))
  (mul [r1 r2] (register (*' (:value r1) (:value r2))))
  (div [r1 r2] (register (/ (:value r1) (:value r2)))))

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
