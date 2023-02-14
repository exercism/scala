# `match` on a `tuple`

```scala
object Leap {
  def leapYear(year: Int): Boolean =
    (year % 4, year % 100, year % 400) match {
      case (0, _, 0) => true
      case (0, 0, _) => false
      case (0, _, _) => true
      case (_, _, _) => false
    }
}
```

A [tuple][tuple] is made from the conditions for the year being evenly divisible by `4`, `100` and `400`.

The `tuple` is tested in a [match][match].
It checks the values of the expressions in the `tuple`, and uses the [underscore][underscore] (`_`) to disregard a value.
The last arm of the `match` returns `false` when none of the previous arms match.

| year | year % 4 | year % 100 | year % 400 == 0 | is leap year |
| ---- | -------- | ---------- | --------------- | ------------ |
| 2020 |        0 |         20 |              20 |         true |
| 2019 |        3 |         19 |              19 |        false |
| 2020 |        0 |          0 |               0 |         true |
| 1900 |        0 |          0 |             300 |        false |

Although some may consider it to be a more "functional" approach, the `match` on a `tuple` approach is somewhat more verbose than other approaches,
and may also be considered less readable.

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[underscore]: https://www.baeldung.com/scala/underscore
