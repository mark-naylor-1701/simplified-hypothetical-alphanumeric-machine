;; author: Mark W. Naylor
;; file:  util.clj
;; date:  2019-Aug-12
;; author: Mark W. Naylor
;; file:  util.clj
;; date:  2019-Aug-10

(ns sham.util
  (:refer clojure.string :only [lower-case]))

(def ^:private MIN-NUMBER -32767)
(def ^:private MAX-NUMBER 32767)

(def ^:private signature-size 8)
(def ^:private signature-keys [:rom :binary :disk :tape])
(def ^:private sham-files ["SHAM.ROM" "SHAM.STM" "SHAM.DSK" "SHAM.TXT"])
(def ^:private signatures
  (zipmap signature-keys (map vec sham-files)))

(defn atom?
  "Is x an instance of Atom?"
  [x]
  (= (class x) clojure.lang.Atom))

(defn ref?
  "Is x an instance of Ref?"
  [x]
  (= (class x) clojure.lang.Ref))

(defn code
  "Finds a given name in a given collection, returns its code/index,
  if it exists as a byte. Throws an exception identifying the kind."
  [coll kind name]
  {:pre [(coll? coll) (string? kind) (string? name)]}
  (let [idx (.indexOf coll (lower-case name))]
    (if (> idx -1)
      idx
      (throw (java.lang.IllegalArgumentException.
              (str "No such " kind " '" name "'"))))))

(defn legal-number?
  "Is n a legal number in the SHAM system?"
  [n]
  {:pre [(int? n)]}
  (<= MIN-NUMBER n MAX-NUMBER))


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
