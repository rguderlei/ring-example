(ns ring-example.database
  (require [clojure.java.jdbc :as jdbc]
         [java-jdbc.ddl :as ddl]
         [java-jdbc.sql :as sql]))

(def db-spec {:classname "com.mysql.jdbc.Driver"
              :subprotocol "mysql"
              :subname "//localhost:3306/todoexample"
              :user "todouser"
              :password "password"
              })

(import 'com.jolbox.bonecp.BoneCPDataSource)
;;(import 'org.h2.jdbc.JdbcClob)

(defn pooled-datasource [db-spec]
  (let [{:keys [classname subprotocol subname user password
                init-pool-size max-pool-size idle-time partitions]} db-spec
         cpds (doto (BoneCPDataSource.)
                    (.setDriverClass classname)
                    (.setJdbcUrl (str "jdbc:" subprotocol ":" subname))
                    (.setUsername user)
                    (.setPassword password)
                    (.setMinConnectionsPerPartition 5)
                    (.setMaxConnectionsPerPartition 100)
                    (.setPartitionCount 1)
                    (.setStatisticsEnabled true)
                    (.setIdleMaxAgeInMinutes (or idle-time 60)))]
       {:datasource cpds}))

(def pooled-db-spec (pooled-datasource db-spec))

(defn init-database
  []
  (jdbc/db-do-commands pooled-db-spec false
    (ddl/create-table
      :items
      [:id :serial "PRIMARY KEY"]
      [:itemText "VARCHAR(255)"]
      [:dueDate :timestamp]))
  )
