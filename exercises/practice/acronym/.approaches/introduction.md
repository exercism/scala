# Introduction

There are at least two general ways to solve Acronym.
One approach is to use the [`split()`][split] method to get the words.
Another approach is to use the [`findAllIn()`][findallin] method to get the words.

## General guidance

A variant on the `split()` approach is to replace dashes with spaces and then split on the spaces, but that is a step
which isn't necessary.

# Approach: `split()`

```scala
object Acronym {
  def abbreviate(phrase: String): String = raw"[\s-]+".r
    .split(phrase)
    .map(_.head.toUpper)
    .mkString
}
```

For more information, check the [`split()` approach][approach-split].

# Approach: `findAllIn()`

```scala
object Acronym {
  def abbreviate(phrase: String): String = {
    raw"[\p{L}']+".r
      .findAllIn(phrase)
      .map(_.head.toUpper)
      .mkString
  }
}
```

For more information, check the [`findAllIn()` approach][approach-findallin].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between approaches can be made by perceived readability.

[split]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html#split(toSplit:CharSequence):Array[String]
[findallin]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html#findAllIn(source:CharSequence):scala.util.matching.Regex.MatchIterator
[approach-split]: https://exercism.org/tracks/scala/exercises/acronym/approaches/split
[approach-findallin]: https://exercism.org/tracks/scala/exercises/acronym/approaches/findallin
