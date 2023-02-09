# Approach: `foldRight()`

```scala
object Luhn {

  def valid(input: String): Boolean = {
    input
      .foldRight((1, 0)) { (chr, tup) =>
        chr match {
          case chr if chr.isDigit => {
            val num = chr.asDigit
            tup match {
              case (pos, sum) if pos % 2 == 0 =>
                (pos + 1, sum + (if (num > 4) (num * 2) - 9 else num * 2))
              case (pos, sum) => (pos + 1, sum + num)
            }
          }
          case chr if chr.isSpaceChar => tup
          case _                      => (-100, 0)
        }
      } match {
      case (pos, sum) => pos > 2 && sum % 10 == 0
    }
  }
}
```

This approach starts by calling the [`foldRight()`][foldright] method on the `String` input.
It is initialized with `1` for the starting position and `0` for the sum, both wrapped in a [tuple][tuple].
The tuple and each character is passed into the [lambda][lambda] where the character is tested in a [`match`][match]
expression. The [pattern matching][pattern-matching] first checks if the character is a digit.
If so, an `Int` is set from the character and the tuple is then matched to whether the position is even or odd.
A new tuple is passed into the next iteration of `foldRight()`, with the sum increased according to the position,
and the position increased by `1`.
Note that the original tuple does not have its position or sum mutated, but a new tuple is created from the operations
on the position and sum, thus supporting [immutability][immutability].

If the character is not a letter, then it is tested for being a space.
If it is a space, then the tuple is passed into the next iteration as is, without changing the position or the sum.

It the character is neither a digit nor a space, then the tuple is recreated with a position that is so far negative
that it won't pass the final `match`.

After `foldRight()` is done, the final tuple is passed to a `match` where the position is tested to be greater than `2`
and the sum is checked to be evenly divisible by `10`.

[foldright]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldRight[B](z:B)(op:(A,B)=%3EB):B
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
