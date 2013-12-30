(ns ring-example.item
  (use ring-example.database  clojure.tools.logging)
  (require [clojure.java.jdbc :refer [insert! query update! delete!]]
           [java-jdbc.sql :refer [select where]])
  (import java.util.Date))

(defn all [request]
            (query pooled-db-spec (select * :items)))

(defn create(
      [attributes]
      (def content (merge {"duedate" (new Date) "itemtext" "default text"} (select-keys attributes ["itemtext" "duedate"])))
      (insert! pooled-db-spec :items content)))

(defn get-item
  [item-id]
  (query pooled-db-spec
    (select * :items (where {:id item-id} ))))

(defn update(
              [item-id attributes]
              (update! pooled-db-spec
                  :items (select-keys attributes ["itemtext" "duedate"]) ["id = ?" item-id] ) ))


(defn delete([item-id] (delete! pooled-db-spec
                            :items (where {:id item-id} ))))

