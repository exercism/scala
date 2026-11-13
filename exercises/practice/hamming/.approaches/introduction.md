# Introduction

There are several idiomatic ways to solve Hamming.
The first approach usually thought of is to use an `if` expression that gives [`None`][none] if the strand lengths aren't equal.
Otherwise it gives the result of [`zip`][zip]ping the strands and [`count`][count]ing the differences, enclosed by [`Some`][some].

Another approach can use a [`match`][match] on whether the strand lengths are equal.
The `false` case gives `None` and the `true` case gives the result of zipping the strands and counting the differences, enclosed by `Some`.

Another approach could also use `match`.
The `false` case gives `None` and the `true` case calls a [recursive][recursion] function to iterate toward the result.

## Approach: `if` with `zip()` and `count()`

```scala
object Hamming:
  def distance(strand1: String, strand2: String): Option[Int] =
    if strand1.length != strand2.length then None
    else Some(strand1.zip(strand2).count((a, b) => a != b))
```

For more information, check the [`if` with `zip()` and `count()` approach][approach-if-zip-count].

## Approach: `match` with `zip()` and `count()`

```scala
object Hamming:
  def distance(strand1: String, strand2: String): Option[Int] =
    strand1.length == strand2.length match
      case true  => Some(strand1.zip(strand2).count((a, b) => a != b))
      case false => None
```

For more information, check the [`match` with `zip()` and `count()` approach][approach-match-zip-count].

## Approach: `match` with recursion

```scala
import scala.annotation.tailrec

object Hamming:

  def distance(strand1: String, strand2: String): Option[Int] =
    strand1.length == strand2.length match
      case true  => Some(distanceCount(0, strand1, strand2))
      case false => None

  @tailrec
  private def distanceCount(acc: Int, s1: String, s2: String): Int =
    s1.length match
      case 0 => acc
      case _ => distanceCount(if s1.head != s2.head then acc + 1 else acc, s1.tail, s2.tail)
```

For more information, check the [`match` with recursion approach][approach-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[none]: https://www.scala-lang.org/api/current/scala/None$.html
[some]: https://www.scala-lang.org/api/current/scala/Some.html
[zip]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[count]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-if-zip-count]: https://exercism.org/tracks/scala/exercises/hamming/approaches/if-zip-count
[approach-match-zip-count]: https://exercism.org/tracks/scala/exercises/hamming/approaches/match-zip-count
[approach-match-recursion]: https://exercism.org/tracks/scala/exercises/hamming/approaches/match-recursion
