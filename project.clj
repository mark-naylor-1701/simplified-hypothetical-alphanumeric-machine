(defproject sham "0.1.0-SNAPSHOT"
  :description "Recreating the SHAM virtual computer (and its assembly language) used to teach the Operating Systems class at Drexel University in the late 1980s."
  :url "https://github.com/mark-naylor-1701/simplified-hypothetical-alphanumeric-machine"
  :license {:name "BSD 3-Clause License"
            :url "https://opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.match "0.3.0"]
                 [me.raynes/fs "1.4.6"]]
  :main ^:skip-aot sham.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src" "src/sham" "src/sham/register" "src/sham/register_items"])
