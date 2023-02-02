# Introduction

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
  private def collatzMeBaby(steps: Int, num: Int): Option[Int] = {
    getStatus(num) match {
      case ILLEGAL => None
      case ONE     => Some(steps)
      case EVEN    => collatzMeBaby(steps + 1, num / 2)
      case ODD     => collatzMeBaby(steps + 1, (num * 3) + 1)
    }
  }

  def steps(start: Int) = {
    collatzMeBaby(0, start)
  }
}
```

For more information, check the [`match` with recursion approach][approach-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-match-recursion]: https://exercism.org/tracks/scala/exercises/hamming/approaches/match-recursion
