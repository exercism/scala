# Introduction

There are at least a couple of idiomatic ways to solve Collatz Conjecture.
One approach is to use [`filter()`][filter] and [`map()`][map] with [`LazyList`][lazylist] and [`takeWhile()`][takewhile].
Another approach is to use [`match`][match] with [recursion][recursion].

## Approach: `filter()` and `map()` with `LazyList` and `takeWhile()`

```scala
object CollatzConjecture:

  def steps(start: Int): Option[Int] =
    Option(start)
      .filter(_ > 0)
      .map(collatzSequence(_).takeWhile(_ != 1).length)

  private def collatzSequence(n: Int): LazyList[Int] =
    LazyList.iterate(n)(n => if n % 2 == 0 then n / 2 else n * 3 + 1)
```

For more information, check the
[`filter()` and `map()` with `LazyList` and `takeWhile()` approach][approach-filter-map-stream-takewhile].

## Approach: `match` with recursion

```scala
import scala.annotation.tailrec

object CollatzConjecture:

  private enum Status:
    case Illegal, Even, Odd, One

  import Status.*

  private def getStatus(num: Int): Status =
    if num <= 0 then Illegal
    else if num == 1 then One
    else if num % 2 == 0 then Even
    else Odd

  @tailrec
  private def collatzMeRecur(steps: Int, num: Int): Option[Int] =
    getStatus(num) match
      case Illegal => None
      case One     => Some(steps)
      case Even    => collatzMeRecur(steps + 1, num / 2)
      case Odd     => collatzMeRecur(steps + 1, num * 3 + 1)

  def steps(start: Int): Option[Int] = collatzMeRecur(0, start)
```

For more information, check the [`match` with recursion approach][approach-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[option]: https://www.baeldung.com/scala/option-type
[filter]: https://www.scala-lang.org/api/current/scala/Option.html
[map]: https://www.scala-lang.org/api/current/scala/Option.html
[lazylist]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList.html
[takewhile]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-filter-map-stream-takewhile]: https://exercism.org/tracks/scala/exercises/collatz-conjecture/approaches/filter-map-stream-takewhile
[approach-match-recursion]: https://exercism.org/tracks/scala/exercises/collatz-conjecture/approaches/match-recursion
