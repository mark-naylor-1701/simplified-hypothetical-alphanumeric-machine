;; author: Mark W. Naylor
;; file:  computer.clj
;; date:  2019-Aug-13


(ns sham.computer
  (:refer-clojure :exclude [pop compare rand peek])

  (:require sham.base-register)
  (:refer sham.base-register :only
          [sham-registers rand peek poke register register-code] )

  (:require sham.memory)
  (:refer sham.memory :only [memory peek-byte poke-byte peek-word poke-word])

  (:require sham.opcodes)
  (:refer sham.opcodes :only [code-zone])
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;  Mappings to implementation functions.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def compare-reg sham.base-register/compare)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def ^:private memory-size 32 ; (* 32 1024)
  )

(def prior-ip (atom 0))

(def registers (sham-registers))

(defn- end-run
  "Temporary status message."
  []
  (let [msg "End run."]
    (println msg)
    msg))

(defn- not-implemented
  ""
  [name]
  (throw (IllegalArgumentException. (str "Function '" name "' has not been implemented.et"))))


(defn nop [])

(defn random
  [regs]
  ;(not-implemented "random")
  (poke regs (register-code "ax") (rand)))

(defn return
  [& args]
  (not-implemented "return"))

(defn
  terminate
  []
  (println (format "program terminated at relative location 0x%02x" @prior-ip))
  ;; TODO: Uncomment when sham.computer is complete. Enabling it now causes the REPL to die.
  ;;(System/exit 0)
  )

(defn trace_on
  [& args]
  (not-implemented "trace_on"))

(defn trace_off
  [& args]
  (not-implemented "trace_off"))

(defn enable
  [& args]
  (not-implemented "enable"))

(defn disable
  [& args]
  (not-implemented "disable"))

(defn negate
  [& args]
  (not-implemented "negate"))

(defn increment
  [& args]
  (not-implemented "increment"))

(defn decrement
  [& args]
  (not-implemented "decrement"))

(defn clear
  [regs n]
  (poke regs n (register 0) ))

(defn push
  [& args]
  (not-implemented "push"))

(defn pop
  [& args]
  (not-implemented "pop"))

(defn move
  [& args]
  (not-implemented "move"))

(defn add
  [& args]
  (not-implemented "add"))

(defn subtract
  [& args]
  (not-implemented "subtract"))

(defn multiply
  [& args]
  (not-implemented "multiply"))

(defn divide
  [& args]
  (not-implemented "divide"))

(defn compare
  [regs r1 r2]
  (poke regs
        (register-code "fr")
        (compare-reg r1 r2)))

(defn in
  [& args]
  (not-implemented "in"))

(defn out
  [& args]
  (not-implemented "out"))

(defn branch
  [& args]
  (not-implemented "branch"))

(defn call
  [& args]
  (not-implemented "call"))

(defn fetch_byte
  [& args]
  (not-implemented "fetch_byte"))

(defn fetch_word
  [& args]
  (not-implemented "fetch_word"))

(defn store_byte
  [& args]
  (not-implemented "store_byte"))

(defn store_word
  [& args]
  (not-implemented "store_word"))

(defn stream
  [& args]
  (not-implemented "stream"))

(defn testandset
  [& args]
  (not-implemented "testandset"))

(defn interrupt
  [& args]
  (not-implemented "interrupt"))

(defn -in
  [& args]
  (not-implemented "-in"))

(defn -out
  [& args]
  (not-implemented "-out"))

(defn -branch
  [& args]
  (not-implemented "-branch"))

(defn -call
  [& args]
  (not-implemented "-call"))

(defn -fetch_byte
  [& args]
  (not-implemented "-fetch_byte"))

(defn -fetch_word
  [& args]
  (not-implemented "-fetch_word"))

(defn -store_byte
  [& args]
  (not-implemented "-store_byte"))

(defn -store_word
  [& args]
  (not-implemented "-store_word"))

(defn -stream
  [& args]
  (not-implemented "-stream"))

(defn -testandset
  [& args]
  (not-implemented "-testandset"))

(defn -interrupt
  [& args]
  (not-implemented "-interrupt"))

;; "Sub" fetches.

(defn fetch-none
  "One byte op, no parameters."
  [& args]
  {})

(defn fetch-two
  "Two byte op, next byte the contains r1 and r2 operands, in the high
  and low nibbles respectively."
  [ram ip]
  (let [val (peek-byte ram (inc ip))
        r1 (bit-shift-right val 4)
        r2 (bit-and val 2r1111)]
    (conj (fetch-none) [:r1 r1] [:r2 r2])))

(defn fetch-three
  "Two byte op, next byte the contains r1 and r2 operands, in the high
  and low nibbles respectively. The next word after the register byte
  contains the address operand."
  [ram ip]
  (conj (fetch-two ram ip) [:address (peek-word ram (+ 2 ip))]))

;; Creating a jump table with functions having the same signature
;; simplifies the logic in 'fetch', eliminating a cond satement.
(def fetch-table {1 fetch-none
                  2 fetch-two
                  4 fetch-three})

(defn fetch
  "Get current ip, next ip, operands, if any, update prior-ip and registers."
  [registers ram]
  (let [f #(peek registers %)
        code (-> "ip" register-code)
        ip (-> code f :value)
        op (peek-byte ram (int ip))
        zone (code-zone ip)
        next-ip (byte (+ ip zone))]
    (reset! prior-ip ip)
    (poke registers code (register next-ip))
    {:ip ip :opcode op :operands ((fetch-table zone) ram ip)}
    )
  )

(defn startup
  "Entry point for the SHAM computer."
  []
  (let [ram (memory memory-size)]
    (println ram)
    )
  (println registers)
  (end-run))


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
