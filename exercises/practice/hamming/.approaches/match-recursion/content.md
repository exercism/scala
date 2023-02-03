# `match` with recursion

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

This approach starts by importing from packages for what is needed.

This `distance()` method starts by using an [`match`][match] expression to compare the lengths of the strands.
If the lengths are not equal the function returns [`None`][none].

Otherwise, the two strands are passed into the `distanceCount()` method, along with `0` for the accumulator.
The `distanceCount()` is marked with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

In `distanceCount()` a `match` expression is used to check the length of the first stand.
If the length is down to `0`, then the accumulated value is returned.
If not, then `distanceCount()` is called again.
A [ternary expression][ternary] expression is used to add to the acccumulating value if the first character in the strands are different,
otherwise the accumulating value is passed to the next method call as is.
A substring of each strand is passed to the next method call.
The substring is for everything after the first character.
Fortunately for this context, if the index is `1` past the length of the `String`, it returns an empty `String` instead of raising
a `StringIndexOutOfBoundsException` exception.

A more idiomatic way to do the same thing as the `substring()` method calls is to use the [`head()`][head] and [`tail()`][tail] methods.
The `head()` method returns the first `Char` in the `String`, and the `tail()` method returns all of the `Char`s after the first `Char`, like so:

```scala
distanceCount(
  (if (s1.head != s2.head) acc + 1 else acc),
  s1.tail,
  s2.tail
)
```

The result from `distanceCount()` is returned to `distance()` and wrapped in a [`Some`][some].
Since the `match` expression is the last line in the function, the result from either `match` case is implicitly returned from the function
without needing the `return` keyword.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[none]: https://www.scala-lang.org/api/2.13.3/scala/None$.html
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[some]: https://www.scala-lang.org/api/2.13.3/scala/Some.html
[char]: https://www.scala-lang.org/api/2.12.1/scala/Char.html
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#head:A
[tail]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#tail:Repr
