(ns association.facts-test
  (:require [clojure.test :refer [deftest is]]
            [association.facts :as facts]))

(deftest nrf-has-spec-basis
  (let [sb (facts/spec-basis "nrf")]
    (is (= 2 (count sb)))
    (is (every? #(= "4719" (:association-rule/isic %)) sb))
    (is (every? #(= "USA" (:association-rule/country %)) sb))))

(deftest unknown-association-has-no-spec-basis
  (is (nil? (facts/spec-basis "bsa")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["nrf" "bsa"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["bsa"] (:missing-associations c)))))

(deftest by-topic-filters
  (is (= ["nrf.five-to-thrive-loss-prevention"]
         (mapv :association-rule/id (facts/by-topic "nrf" :loss-prevention))))
  (is (empty? (facts/by-topic "nrf" :labor)))
  (is (empty? (facts/by-topic "bsa" :governance))))
