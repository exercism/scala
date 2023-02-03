# Introduction

There are at least a couple of idiomatic ways to solve Collatz Conjecture.
One approach is to use [`filter()`][filter] and [`map()`][map] with [`Stream`][stream] and [`takeWhile()`][takewhile].
Another approach is to use [`match`][match] with [recursion][recursion].

## Approach `filter()` and `map()` with `Stream()` and `takeWhile()`

```java
object CollatzConjecture {

  def steps(start: Int): Option[Int] = {
    Option(start)
      .filter(_ > 0)
      .map(collatzStream(_).takeWhile(_ != 1).length)
  }
  
  private def collatzStream(n: Int): Stream[Int] =
    Stream.iterate(n)(n => if (n % 2 == 0) n / 2 else (n * 3) + 1)
}
```

For more information, check the
[`filter()` and `map()` with `Stream()` and `takeWhile()` approach][approach-filter-map-stream-takewhile].

## Approach: `match` with recursion

```java
import scala.annotation.tailrec

object CollatzConjecture {

  private object Status extends Enumeration {
    type Status = Value
    val ILLEGAL, EVEN, ODD, ONE = Value
  }
  import Status._

  private def getStatus(num: Integer): Status = {
    if (num <= 0)
      ILLEGAL
    else if (num == 1)
      ONE
    else if (num % 2 == 0)
      EVEN
    else
      ODD
  }

  @tailrec
  private def collatzMeRecur(steps: Int, num: Int): Option[Int] = {
    getStatus(num) match {
      case ILLEGAL => None
      case ONE     => Some(steps)
      case EVEN    => collatzMeRecur(steps + 1, num / 2)
      case ODD     => collatzMeRecur(steps + 1, (num * 3) + 1)
    }
  }

  def steps(start: Int) = {
    collatzMeRecur(0, start)
  }
}
```

For more information, check the [`match` with recursion approach][approach-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[option]: https://www.baeldung.com/scala/option-type
[filter]: https://www.scala-lang.org/api/2.13.6/scala/Option.html#filter(p:A=%3EBoolean):Option[A]
[map]: https://www.scala-lang.org/api/2.13.6/scala/Option.html#map[B](f:A=%3EB):Option[B]
[stream]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/Stream$.html#iterate[A](start:A)(f:A=%3EA):scala.collection.immutable.Stream[A]
[takewhile]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/Stream.html#takeWhile(p:A=%3EBoolean):C
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-filter-map-stream-takewhile]: https://exercism.org/tracks/scala/exercises/collatz-conjecture/approaches/filter-map-stream-takewhile
[approach-match-recursion]: https://exercism.org/tracks/scala/exercises/collatz-conjecture/approaches/match-recursion
