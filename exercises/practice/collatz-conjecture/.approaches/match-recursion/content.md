# `match` with recursion

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

This approach starts by importing from packages for what will be needed.

The `CollatzConjecture` object starts by defining an [`enum`][enum] for the four states a number can be in.
`import Status.*` lets the code use the cases as `Illegal`, `Even`, `Odd` and `One` instead of `Status.Illegal` and so on.

The `getStatus()` method returns an `enum` value depending on the value of the passed-in `Int`.

The `collatzMeRecur()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

A [`match`][match] expression is used to perform [pattern matching][pattern-matching] on the result of passing in the number to
the `getStatus()` method.

If the `Status` of the number is `Illegal`, then `None` is returned.
If the `Status` of the number is `One`, then the number of steps wrapped in `Some` is returned from the method.

If the `Status` of the number is `Even` or `Odd`, then the function calls itself by adding `1` to `steps` and calculating
the new value for the number according to whether it is even or odd.

The `steps()` method calls `collatzMeRecur()`, passing in `0` as the starting value for `steps` and the number passed into the `steps()`
method as the number to be recalculated until it reaches `1`.

## Shortening

All of the ceremony involving the `enum` could be gotten rid of by using [pattern guards][pattern-guard], and the code shortened to

```scala
import scala.annotation.tailrec

object CollatzConjecture:

  @tailrec
  private def collatzMeRecur(steps: Int, num: Int): Option[Int] =
    num match
      case n if n <= 0     => None
      case 1               => Some(steps)
      case n if n % 2 == 0 => collatzMeRecur(steps + 1, n / 2)
      case n               => collatzMeRecur(steps + 1, n * 3 + 1)

  def steps(start: Int): Option[Int] = collatzMeRecur(0, start)
```

[enum]: https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-guard]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
