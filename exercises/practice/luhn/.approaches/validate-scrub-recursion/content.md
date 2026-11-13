# Validate and scrub, then recurse

```scala
import scala.annotation.tailrec

object Luhn:

  def valid(input: String): Boolean =
    if input.exists(chr => !chr.isDigit && !chr.isSpaceChar) then false
    else
      val cleanedInput = input.filter(_.isDigit)
      cleanedInput.length > 1 && validRecur(1, 0, cleanedInput)

  @tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean =
    if input.isEmpty then sum % 10 == 0
    else
      val num = input.last.asDigit
      pos match
        case pos if pos % 2 == 0 =>
          validRecur(pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2), input.init)
        case _ => validRecur(pos + 1, sum + num, input.init)
```

This approach starts by calling the [`exists()`][exists] method on the `String` input to check for any characters that are not
a digit or a space.
If there is one, then the input is invalid and the `if` expression gives `false`.

Otherwise, the input is [`filter()`][filter]ed to keep only the digits, which scrubs out the spaces.

If the input without spaces has fewer than two digits, then it is invalid.
Otherwise, the method returns the result of calling the recursive method, passing in `1` for the starting position,
`0` for the sum, and the scrubbed input.

The `validRecur()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

If the input `String` [`isEmpty`][isempty], then the method returns whether the sum is evenly divisible by `10`.

Otherwise, an `Int` is set from the [last][last] character, and the position is then [pattern matched][pattern-matching]
for whether it is even or odd.
The `validRecur()` method calls itself with the sum increased according to the position,
the position increased by `1`, and all but the last character returned by [`init`][init].
Note that the original position, sum and input are not mutated, but new arguments are created from the operations
on them, thus supporting [immutability][immutability].

[exists]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[filter]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[last]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[init]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[isempty]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
