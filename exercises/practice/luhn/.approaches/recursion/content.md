# Recursion

``` scala
object Luhn {

  def valid(input: String): Boolean = {
    validRecur(1, 0, input)
  }

  @scala.annotation.tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean = {
    if (input.isEmpty) return pos > 2 && sum % 10 == 0
    input.last match {
      case chr if chr.isDigit => {
        val num = chr.asDigit
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
      case chr if chr.isSpaceChar => validRecur(pos, sum, input.init)
      case _                      => false
    }
  }
}
```

This approach starts by returning the result of calling the recursive method, passing in `1` for the starting position,
`0` for the sum, and the `String` input.

The `validRecur()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

If the input `String` [`isEmpty`][isempty], then the method returns if the position is greater than `2` and the sum
is evenly divisible by `10`.

Otherwise, the [last][last] character is tested in a [`match`][match] expression.
The [pattern matching][pattern-matching] first checks if the character is a digit.
If so, an `Int` is set from the character and the position is then checked for whether it is even or odd.
The `validRecur()` method calls itself with the sum increased according to the position,
the position increased by `1`, and all but the last character returned by [`init()`][init].
Note that the original position, sum and input are not mutated, but new arguments are created from the operations
on them, thus supporting [immutability][immutability].

If the character is not a letter, then it is tested for being a space.
If it is a space, then the position and sum are passed into the next call of`validRecur()` as is, along with all but the last character.

If the character is neither a digit nor a space, then the character is illegal and the method immediately returns `false`.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[last]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#last:A
[init]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#init:Repr
[isempty]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#isEmpty:Boolean
