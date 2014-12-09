(defproject for-clojure "0.1.0"
  :description "clojure snippets"
  :url "https://github.com/unionx/for-clojure/tree/master/for-clojure"
  :license {:name "GPLv3"
            :url "http://www.gnu.org/licenses/gpl.html"}
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [postgresql "9.3-1102.jdbc41"]
                 [clojure.jdbc "0.3.2"]
                 [com.taoensso/nippy "2.7.1"]]
  :main for.clojure.core)
