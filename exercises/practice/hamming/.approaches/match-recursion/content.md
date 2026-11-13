# `match` with recursion

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

This approach starts by importing the `tailrec` annotation.

The `distance()` method starts by using a [`match`][match] expression on whether the lengths of the strands are equal.
If the lengths are not equal, the `false` case gives [`None`][none].

Otherwise, the two strands are passed into the private `distanceCount()` method, along with `0` for the accumulator.
The `distanceCount()` method is marked with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

In `distanceCount()` a `match` expression is used to check the length of the first strand.
If the length is down to `0`, then the accumulated value is returned.
If not, then `distanceCount()` is called again.
An [`if` expression][if], which Scala uses where other languages have a ternary operator, adds `1` to the accumulating value if the first characters
of the strands are different; otherwise the accumulating value is passed to the next method call as is.
The [`head`][head] method is used to return the first `Char` in the `String`s for the comparison.
The [`tail`][tail] of each strand is passed to the next method call.
The `tail` method returns all of the `Char`s after the first `Char`.

The result from `distanceCount()` is returned to `distance()` and wrapped in a [`Some`][some].
Since the `match` expression is the body of the method, the result from either case is returned without needing the `return` keyword.

[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[if]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
[none]: https://www.scala-lang.org/api/current/scala/None$.html
[some]: https://www.scala-lang.org/api/current/scala/Some.html
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[head]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tail]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
