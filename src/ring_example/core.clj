(ns ring-example.core
  (:use compojure.core ring-example.hello ring-example.item)
  (:require [ring.middleware.json :as middleware] [compojure.route :as route] [compojure.handler :as handler])
)

(defroutes items-routes
  (GET "/" [] ring-example.item/all)
  (POST "/" {body :body} (ring-example.item/create body))
  (context "/:item-id" [item-id] (defroutes item-routes
       (GET "/" [] (ring-example.item/get-item item-id))
       (PUT "/" {body :body} (ring-example.item/update item-id body))
       (DELETE "/" [] (ring-example.item/delete item-id))))
)



(defroutes app-routes
  (GET "/" [] ring-example.hello/hello)
  (context "/items" [] items-routes)
  (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))