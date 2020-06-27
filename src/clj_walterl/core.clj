(ns clj-walterl.core)

(defn- do-cond-some-as
  [sym [test-expr then-expr & remaining]]
  (cond
    (and test-expr then-expr)
    `(if-let [~sym ~test-expr]
       ~then-expr
       ~(when (seq remaining)
          (do-cond-some-as sym remaining)))

    (some? test-expr) ; Default value
    test-expr))

(defmacro cond-some-as
  "Bind truthy test result to a symbol for use in matching exprs.

  Usage:

      (cond-some-as sym
        test expr
        [test expr
         ...]
        [default])

  Example:

      (cond-some-as $
        (re-find #\"Mr\\. (.*)\" full-name) (second $)
        (not-empty full-name)              $
        \"John Doe\")

  The above is equivalent to:

      (if-let [$ (re-find #\"Mr\\. (.*)\" full-name)]
        (second $)
        (if-let [$ (not-empty full-name)]
          $
          \"John Doe\"))"
  {:style/indent 2}
  [sym & body]
  (do-cond-some-as sym body))
