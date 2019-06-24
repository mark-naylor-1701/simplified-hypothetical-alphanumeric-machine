(defproject sham "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  ;;:url "http://example.com/FIXME"
  :license {:name "BSD 3-Clause License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot sham.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
