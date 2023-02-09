# Validate and scrub, then `foldRight()`

```scala
object Luhn {

  def valid(input: String): Boolean = {
    if (input.find(chr => !chr.isDigit && !chr.isSpaceChar) != None)
      return false
    val cleanedInput = input.filter(Character.isDigit)
    if (cleanedInput.length() < 2) return false

    cleanedInput
      .foldRight((1, 0)) { (chr, tup) =>
        val num = chr.asDigit
        tup match {
          case (pos, sum) if pos % 2 == 0 =>
            (pos + 1, sum + (if (num > 4) (num * 2) - 9 else num * 2))
          case (pos, sum) => (pos + 1, sum + num)
        }
      } match {
      case (_, sum) => sum % 10 == 0
    }
  }
}
```

This approach starts be calling the [`find()`][find] method on the `String` input to check for any characters that are not
a digit or a space.
If so, then the input is invalid and the method immediately returns `false`.

If there are no invalid characters, then the input is [`filter()`][filter]ed to remove any spaces.
If the length of the input without spaces is less than `2`, then it is invalid and the method immediately returns `false`.

The validated and scrubbed input is passed to the [`foldRight()`][foldright] method.
It is initialized with `1` for the starting position and `0` for the sum, both wrapped in a [tuple][tuple].
The tuple and each character is passed into the [lambda][lambda] where an `Int` is set from the character and the tuple is then
[pattern matched][pattern-matching] to whether the position is even or odd.
A new tuple is passed into the next iteration of `foldRight()`, with the sum increased according to the position,
and the position increased by `1`.
Note that the original tuple does not have its position or sum mutated, but a new tuple is created from the operations
on the position and sum, thus supporting [immutability][immutability].

After `foldRight()` is done, the final tuple is passed to a `match` where the sum is checked to be evenly divisible by `10`.

[find]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#find(p:A=%3EBoolean):Option[A]
[filter]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#filter(p:A=%3EBoolean):Repr
[foldright]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldRight[B](z:B)(op:(A,B)=%3EB):B
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
