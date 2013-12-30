(ns ring-example.core-test
  (:use clojure.algo.generic.functor)
  (:require [clojure.test :refer :all]
            [ring-example.core :refer :all]
           ))

(deftest a-test
  (def my-seq [{:a 1 :b 2} {:a 2 :b 4}])
  (fmap (fn [x] (fmap println x)) my-seq)
  )
