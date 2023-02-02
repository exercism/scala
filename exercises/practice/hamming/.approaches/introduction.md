# Introduction

There are several idiomatic ways to solve Hamming.
The first approach usually thought of is to use an `if` statement to return [`None`][none] if the strand lengths aren't equal.
Then the function returns the result of [`zip`][zip]ping the strands and [`count`][count]ing them, enclosed by [`Some`][some]

Another approach can use a [`match`][match] on whether the strand langths are equal.
The `false` arm returns `None` and the `true` arms returns the result of zipping the strands and counting them, enclosed by `Some`.

Another approach could also use `match`.
The `false` arm returns `None` and the `true` arm calls a [recursive][recursion] function to iterate toward the result.

## Approach: `if` with `zip()` and `count()`

```scala
object Hamming {

  def distance(strand1: String, strand2: String): Option[Int] = {
    if (strand1.length != strand2.length)
      return None
    Some(strand1 zip strand2 count (strand => strand._1 != strand._2))
  }
}
```

For more information, check the [`if` with `zip()` and `count()` approach][approach-if-zip-count].

## Approach: `match` with `zip()` and `count()`

```scala
object Hamming {

  def distance(strand1: String, strand2: String): Option[Int] = {
    strand1.length == strand2.length match {
      case true =>
        Some(strand1 zip strand2 count (strand => strand._1 != strand._2))
      case false => None
    }
  }
}
```

For more information, check the [`match` with `zip()` and `count()` approach][approach-match-zip-count].

## Approach: `match` with recursion

```scala
import scala.annotation.tailrec

object Hamming {

  def distance(strand1: String, strand2: String): Option[Int] = {
    strand1.length == strand2.length match {
      case true =>
        Some(distanceCount(0, strand1, strand2))
      case false => None
    }
  }

  @tailrec def distanceCount(acc: Int, s1: String, s2: String): Int = {
    s1.length match {
      case 0 => acc
      case _ =>
        distanceCount(
          (if (s1.charAt(0) != s2.charAt(0)) acc + 1 else acc),
          s1.substring(1),
          s2.substring(1)
        )
    }
  }

}
```

For more information, check the [`match` with recursion approach][approach-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[none]: https://www.scala-lang.org/api/2.13.3/scala/None$.html
[some]: https://www.scala-lang.org/api/2.13.3/scala/Some.html
[zip]: https://www.scala-lang.org/api/2.13.3/scala/collection/Iterable.html#zip[B](that:scala.collection.IterableOnce[B]):CC[(A@scala.annotation.unchecked.uncheckedVariance,B)]
[count]: https://www.scala-lang.org/api/2.13.3/scala/collection/mutable/Iterable.html#count(p:A=%3EBoolean):Int
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-if-zip-count]: https://exercism.org/tracks/scala/exercises/hamming/approaches/if-zip-count
[approach-match-zip-count]: https://exercism.org/tracks/scala/exercises/hamming/approaches/match-zip-count
[approach-match-recursion]: https://exercism.org/tracks/scala/exercises/hamming/approaches/match-recursion
