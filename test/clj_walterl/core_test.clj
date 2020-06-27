(ns clj-walterl.core-test
  (:require [clojure.test :refer :all]
            [clj-walterl.core :refer [cond-some-as]]))

(deftest cond-some-as-tests
  (doseq [[full-name expected] [["Dr. Jekyll" "Dr. Jekyll"]
                                ["Mr. Hyde"   "Hyde"]
                                [""           "John Doe"]]]
    (is (= expected
           (cond-some-as $
             (re-find #"Mr\. (.*)" full-name) (second $)
             (not-empty full-name)            $
             "John Doe")))))
