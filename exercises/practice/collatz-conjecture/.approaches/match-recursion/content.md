# `match` with recursion

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

This approach starts by importing from packages for what will be needed.

The `CollatzConjecture` class starts by defining an [Enumeration][enum-v2] in the version 2 way.
With version 3, an [`enum`](https://docs.scala-lang.org/scala3/reference/enums/enums.html) can be created like so

```scala
enum Status:
  case ILLEGAL, EVEN, ODD, ONE
```

The `getStatus()` method returns an `enum` value depending on the value of the passed-in `Int`.

The `collatzMeRecur()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

A [`match`][match] expression is used to perform [pattern matching][pattern-matching] on the result of passing in the number to
the `getStatus()` method.

If the `Status` of the number is `ILLEGAL`, then `None` is returned.
If the `Status` of the number is `ONE`, then the number of steps wrapped in `Some` is returned from the method.

If the `Status` of the number is `EVEN` or `ODD`, then the function calls itself by adding `1` to `steps` and calculating
the new value for the number according to whether it is even or odd.

The `steps()` method calls `collatzMeRecur()`, passing in `0` as the starting value for `steps` and the number passed into the `steps()`
method as the number to be recalculated until it reaches `1`.

## Shortening

All of the ceremony involving the enumeration could be gotten rid of by using [pattern guards][pattern-guard], and the code shortened to

```scala
import scala.annotation.tailrec

object CollatzConjecture {

  @tailrec
  private def collatzMeRecur(steps: Int, num: Int): Option[Int] = {
    num match {
      case nbr0 if nbr0 <= 0     => None
      case 1                     => Some(steps)
      case num2 if num2 % 2 == 0 => collatzMeRecur(steps + 1, num2 / 2)
      case num3                  => collatzMeRecur(steps + 1, (num3 * 3) + 1)
    }
  }

  def steps(start: Int) = {
    collatzMeRecur(0, start)
  }
}
```

[enum-v2]: https://www.scala-lang.org/api/2.13.x/scala/Enumeration.html
[enum-v3]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[option]: https://www.baeldung.com/scala/option-type
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-guard]: https://alvinalexander.com/scala/how-to-use-if-then-expressions-guards-in-case-statements-scala/
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
