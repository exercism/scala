# Introduction

There are various idiomatic approaches to solve Leap.
You can use a chain of boolean expressions to test the conditions.
Or you can use a [ternary expression][ternary].
Another approach you can use is a [match][match] on a [tuple][tuple].

## General guidance

The key to solving Leap is to know if the year is evenly divisible by `4`, `100` and `400`.
For determining that, you will use the [modulus operator][modulus-operator].

## Approach: Chain of Boolean expressions

```scala
object Leap {
  def leapYear(year: Int): Boolean =
    year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}
```

For more information, check the [Boolean chain approach][approach-boolean-chain].

## Approach: Ternary expression of Boolean conditions

```scala
object Leap {
  def leapYear(year: Int): Boolean =
    if (year % 100 == 0) year % 400 == 0
    else year % 4 == 0
}
```

For more information, check the [Ternary expression approach][approach-ternary-expression].

## Approach: `match` on a `tuple`

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

For more information, check the [`match` on a `tuple` approach][approach-match-on-a-tuple].

## Other approaches

Besides the aforementioned, idiomatic approaches, you could also approach the exercise as follows:

## LocalDate.plusDays() approach:

Add a day to February 28th for the year and see if the new day is the 29th. For more information, see the [`LocalDate.plusDays` approach][approach-localdate-plusdays].

## Built-in method approach:

Use the built-in method for the year. For more information, see the [`LocalDate.isLeapYear` approach][approach-localdate-isleapyear].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between approaches could be made by perceived readability.
- The chain of boolean expressions is most efficient, as it proceeds from the most likely to least likely conditions.
  It has a maximum of three checks.
- The ternary operator has a maximum of only two checks, but it starts from a less likely condition.
- The `switch` on a `tuple` may be considered more "functional", but it is more verbose and may be considered less readable.
- Using LocalDate addition or using the built-in `isLeapYear` method may be considered "cheats" for the exercise,
  but `isLeapYear` would be the idiomatic way to check if a year is a leap year in Scala.

[modulus-operator]: https://www.geeksforgeeks.org/operators-in-scala/
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[approach-boolean-chain]: https://exercism.org/tracks/scala/exercises/leap/approaches/boolean-chain
[approach-ternary-expression]: https://exercism.org/tracks/scala/exercises/leap/approaches/ternary-expression
[approach-match-on-a-tuple]: https://exercism.org/tracks/scala/exercises/leap/approaches/match-on-a-tuple
[approach-localdate-plusdays]: https://exercism.org/tracks/scala/exercises/leap/approaches/localdate-plusdays
[approach-localdate-isleapyear]: https://exercism.org/tracks/scala/exercises/leap/approaches/built-in-method
