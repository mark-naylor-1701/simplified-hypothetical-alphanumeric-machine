;; author: Mark W. Naylor
;; file:  memory.clj
;; date:  2019-Aug-12

(ns sham.memory
  (:use [sham.util :only [ref?]]))

(defn memory
  "Initializes a memory bank of given size to zeros."
  [size-in-bytes]
  {:pre [(number? size-in-bytes)]}
  (vec (repeat size-in-bytes (byte 0))))

(defn- byte?
  "Is x of type byte?"
  [x]
  (= Byte (class x)))


(defn- memory-bank?
  "Is x a collection of bytes?"
  [x]
  (and (coll? x)
       (every? byte? x)))


(defn peek-byte
  "Return a byte from a memory bank at the given offset, nil if access
  attempted above upper boundary."
  [obj idx]
  {:pre [(memory-bank? obj)
         (number? idx)]}
  (get obj idx))

(defn poke-byte
  "Puts a byte into a memory bank at the given offset."
  [obj idx n]
  {:pre [(memory-bank? obj)
         (number? idx)
         (number? n)
         (< -1 idx (count obj))]}
  (assoc obj idx (unchecked-byte n)))

(defn peek-word
  "Return a word from a memory bank at the given offset, nil if access
  attempted above upper boundary."
  [obj idx]
  {:pre [(memory-bank? obj)
         (number? idx)
         (>= idx 0)]}
  (let [hi (get obj idx)
        lo (get obj (inc idx))]
    (when (every? byte? [hi lo])
      (bit-or
       (bit-shift-left hi 8)
       lo))))

(defn poke-word
  "Puts a word into a memory bank at the given offset."
  [obj idx n]
  {:pre [(memory-bank? obj)
         (number? idx)
         (number? n)
         (< -1 idx (count obj))]}
  (let [idx+ (inc idx)
        lo (unchecked-byte n)
        hi (unchecked-byte (bit-shift-right (unchecked-short n) 8))]
    (assoc obj idx hi)
    (assoc obj (inc idx) lo)))


;; TODO: Possibly move to the `computer' module, as memory doesn't
;; care about its contents. The interpretation of what the bytes
;; represent is a higher abstraction. Keep it here for now because it
;; is working. (REPL testing only.)
(defn fetch-instruction
  "Fetch the instuction byte, the registers byte, and the address byte
  from memory."
  [obj idx]
  [(peek-byte obj idx)
   (peek-byte obj (inc idx))
   (peek-word obj (-> idx inc inc))])

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
