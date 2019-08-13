;; author: Mark W. Naylor
;; file:  register_items.clj
;; date:  2019-Jun-25

(ns sham.register-items
  (:require [sham.register-items.impl :as impl] ))

(def RegisterCode impl/RegisterCode)
(def RegisterName impl/RegisterName)

;; Constructors
(def register-code "RegisterName constructor." impl/register-code)
(def register-name "RegisterCode constructor." impl/register-name)
