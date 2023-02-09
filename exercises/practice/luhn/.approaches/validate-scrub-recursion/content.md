# Validate and scrub, then recurse

```scala
object Luhn {

  def valid(input: String): Boolean = {
    if (input.find(chr => !chr.isDigit && !chr.isSpaceChar) != None)
      return false
    val cleanedInput = input.filter(Character.isDigit)
    if (cleanedInput.length() < 2) return false

    validRecur(1, 0, cleanedInput)
  }

  @scala.annotation.tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean = {
    if (input.isEmpty) return sum % 10 == 0
    val num = input.last.asDigit
    pos match {
      case pos if pos % 2 == 0 =>
        validRecur(
          pos + 1,
          sum + (if (num > 4) (num * 2) - 9 else num * 2),
          input.init
        )
      case _ => validRecur(pos + 1, sum + num, input.init)
    }
  }
}
```

This approach starts be calling the [`find()`][find] method on the `String` input to check for any characters that are not
a digit or a space.
If so, then the input is invalid and the method immediately returns `false`.

If there are no invalid characters, then the input is [`filter()`][filter]ed to remove any spaces.
If the length of the input without spaces is less than `2`, then it is invalid and the method immediately returns `false`.

The method then returns the result of calling the recursive method, passing in `1` for the starting position,
`0` for the sum, and the scrubbed input.

The `validRecur()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

If the input `String` [`isEmpty`][isempty], then the method returns if the sum is evenly divisible by `10`.

Otherwise, an `Int` is set from the [last][last] character, and the position is then [pattern matched][pattern-matching]
for whether it is even or odd.
The `validRecur()` method calls itself with the sum increased according to the position,
the position increased by `1`, and all but the last character returned by [`init()`][init].
Note that the original position, sum and input are not mutated, but new arguments are created from the operations
on them, thus supporting [immutability][immutability].

[find]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#find(p:A=%3EBoolean):Option[A]
[filter]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#filter(p:A=%3EBoolean):Repr
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[last]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#last:A
[init]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#init:Repr
[isempty]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#isEmpty:Boolean
