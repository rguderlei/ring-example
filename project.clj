(defproject ring-example "0.1.0-SNAPSHOT"
  :description "simple example for a ring based app"
  :url "http://example.com/FIXME"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :plugins [[lein-ring "0.8.8"]] ;; start server from leiningen
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.2.1"] ;; REST core
                 [ring/ring-json "0.2.0"] ;; REST json middleware
                 [ring/ring-jetty-adapter "1.2.1"] ;; use Jetty to run the services
                 [compojure "1.1.6"]  ;; Routing
                 [org.clojure/java.jdbc "0.3.0-beta2"] ;; jdbc
                 [java-jdbc/dsl "0.1.0"] ;; jdbc dsl
                 [com.jolbox/bonecp "0.8.0.RELEASE"] ;; connection pooling
                 ;;[com.h2database/h2 "1.3.174"] ;; database (driver)
                 [org.slf4j/slf4j-simple "1.7.5"]
                 [org.clojure/algo.generic "0.1.1"]
                 [org.clojure/tools.logging "0.2.6"]
                 [mysql/mysql-connector-java "5.1.27"]
                 ]
  :ring {:handler ring-example.core/app };;:init ring-example.database/init-database}  ;; define root resource
  )
