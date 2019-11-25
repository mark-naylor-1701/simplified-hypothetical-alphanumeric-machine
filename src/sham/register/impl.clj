;; author: Mark W. Naylor
;; file:  impl.clj
;; date:  2019-Jun-16
(ns sham.register.impl
  (:refer-clojure :exclude [peek])
  (:use [clojure.string :only [lower-case]])
  (:use [sham.util :only [ref?]]))


(defn register
  "Constructor for Register."
  [n]
  {:pre [(number? n)]}
  (with-meta {:value (unchecked-short n)} {:type ::Register}))

(defn register?
  "Is r a Register?"
  [r]
  (= ::Register (type r)))

(defn registers
  "Constructor for a bank of registers."
  [spec]
  {:pre [(or (number? spec)
             (and (coll? spec)
                  (every? string? spec)))]}
  (let [n (if (coll? spec)
            (count spec)
            spec)]
    (vec (repeat n (register 0)))))


(defn- register-bank?
  ""
  [x]
  (and (coll? x)
       (every? register? x)))

(defn peek
  "Get a selected register"
  [obj idx]
  {:pre [(register-bank? obj)
         (number? idx)]}
  (obj idx))

(defn poke
  "Set a selected register"
  [obj idx reg]
  {:pre [(register-bank? obj)
         (number? idx)
         (register? reg)]}
  (assoc obj idx reg))

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
