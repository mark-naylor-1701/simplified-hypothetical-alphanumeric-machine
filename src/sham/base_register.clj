;; author: Mark W. Naylor
;; file:  base_register.clj
;; date:  2019-Jun-07
(ns sham.base-register
  (:refer-clojure :exclude [name compare rand])

  (:refer clojure.string :only [lower-case])

  (:require sham.register.impl)
  (:refer sham.register.impl :only [register registers])

  (:require sham.util)
  (:refer sham.util :only [code])
  )

(def ^:private Register
  "Alias the \"type name\", hiding implementation details."
  :sham.register.impl/Register)

(def ^:private register-names ["nx" "ax" "bx" "cx" "dx" "ex" "fx" "dr" "ip" "sr" "sp" "fr"])

(def ^:private max-rand (Math/pow 2 16))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;          Forward declations
(declare ^:private check-fn rand)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def register-code (partial code register-names "register"))

(def sham-registers (registers register-names))

(defmulti plus "Add either a number or register to register."  check-fn)

(defmethod plus [Register Number] [r1 r2]
  (register (+' (:value r1) r2)))

(defmethod plus [Register Register] [r1 r2]
  (register (apply +' (map :value [r1 r2]))))

(defmulti minus "Subtract either a number or register to register."  check-fn)

(defmethod minus [Register Number] [r1 r2]
  (register (-' (:value r1) r2)))

(defmethod minus [Register Register] [r1 r2]
  (register (apply -' (map :value [r1 r2]))))

(defmulti times "Muliply either a number or register to register."  check-fn)

(defmethod times [Register Number] [r1 r2]
  (register (*' (:value r1) r2)))

(defmethod times [Register Register] [r1 r2]
  (register (apply *' (map :value [r1 r2]))))

(defmulti divided-by "Divide either a number or register to register."  check-fn)

(defmethod divided-by [Register Number] [r1 r2]
  (register (/ (:value r1) r2)))

(defmethod divided-by [Register Register] [r1 r2]
  (register (apply / (map :value [r1 r2]))))

(defmulti neg "Negate (* -1) either a register or number?" type)

(defmethod neg Register [r]
  (register (* -1 (:value r))))

(defmulti add-1 "Increment a register" type)

(defmethod add-1  Register [r]
  (plus r 1))

(defmulti sub-1 "Increment a register" type)

(defmethod sub-1 Register [r]
  (minus r 1))

(defmulti compare "Compares two registers or a register and a number" check-fn)

(defmethod compare [Register Number] [r1 r2]
  (register (clojure.core/compare (:value r1) r2)))

(defmethod compare [Register Register] [r1 r2]
  (register (apply clojure.core/compare (map :value [r1 r2]))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;   private functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- check-fn
  "Dispatch function for register operation multimethods."
  [p1 p2]
  [(type p1) (if (number? p2)
               Number
               (type p2))])

(defn- rand
  "Create a register holding a random integer."
  []
  (register (rand-int max-rand)))

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
