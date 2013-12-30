(ns ring-example.item
  (use ring-example.database  clojure.tools.logging)
  (require [clojure.java.jdbc :as jdbc]
           [java-jdbc.sql :as sql])
  (import java.util.Date))

(defn all [request]
            (jdbc/query pooled-db-spec (sql/select * :items)))

(defn create(
      [attributes]
      (def content (merge {"duedate" (new Date) "itemtext" "default text"} (select-keys attributes ["itemtext" "duedate"])))
      (jdbc/insert! pooled-db-spec :items content)))

(defn get-item
      [item-id]
      (jdbc/query pooled-db-spec
      (sql/select * :items (sql/where {:id item-id} ))))

(defn update(
              [item-id attributes]
              (jdbc/update! pooled-db-spec
                  :items (select-keys attributes ["itemtext" "duedate"]) ["id = ?" item-id] ) ))


(defn delete([item-id] (jdbc/delete! pooled-db-spec
                            :items (sql/where {:id item-id} ))))

