(ns for.clojure.core
  (:require [jdbc.core :as jdbc]
            [taoensso.nippy :as nippy])
  (:import (com.unionx LoveChengdu)))


(def pg-db {:vendor "postgresql"
            :name "unionx"
            :host "localhost"
            :port 5432
            :user "unionx"
            :password "21121math"})

(defn test-insert []
  (let [conn (jdbc/make-connection pg-db)
        sql ["SELECT name, value FROM clj;"]
        results (jdbc/query conn sql)]
    (doseq [row results]
      (let [value (nippy/thaw (:value row))]
      (println {:name (:name row)
                :value value
                :value-type (type value)})))))

(defn -main []
  (test-insert))
