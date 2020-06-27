# clj-walterl

A Clojure library for various bits and bobs that I've written and ideas I've
played with. This isn't necessarily meant to be used in other projects, but
should be considered more of a sandbox.

## `cond-some-as`

A macro that binds a truthy test result to a specified symbol for use in the
matching expr.

Usage:

```clojure
    (cond-some-as sym
      test expr
      [test expr
       ...]
      [default])
```

Example:

```clojure
(cond-some-as $
  (re-find #"Mr\\. (.*)" full-name) (second $)
  (not-empty full-name)             $
  "John Doe")
```

The above is equivalent to:

```clojure
(if-let [$ (re-find #"Mr\\. (.*)" full-name)]
  (second $)
  (if-let [$ (not-empty full-name)]
    $
    "John Doe"))
```

## License

Copyright © 2020 Walter Leibbrandt <https://github.com/walterl>

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
