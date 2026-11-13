# Validate and scrub, then `foldRight()`

```scala
object Luhn:

  def valid(input: String): Boolean =
    if input.exists(chr => !chr.isDigit && !chr.isSpaceChar) then false
    else
      val cleanedInput = input.filter(_.isDigit)

      val (_, total) = cleanedInput.foldRight((1, 0)): (chr, tup) =>
        val num = chr.asDigit
        tup match
          case (pos, sum) if pos % 2 == 0 =>
            (pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2))
          case (pos, sum) => (pos + 1, sum + num)
      cleanedInput.length > 1 && total % 10 == 0
```

This approach starts by calling the [`exists()`][exists] method on the `String` input to check for any characters that are not
a digit or a space.
If there is one, then the input is invalid and the `if` expression gives `false`.

Otherwise, the input is [`filter()`][filter]ed to keep only the digits, which scrubs out the spaces.

The scrubbed input is passed to the [`foldRight()`][foldright] method.
It is initialized with `1` for the starting position and `0` for the sum, both wrapped in a [tuple][tuple].
The tuple and each character is passed into the [lambda][lambda].
The lambda is the last argument of `foldRight()`, so it can follow a [colon instead of being wrapped in braces][fewer-braces].
In the lambda an `Int` is set from the character and the tuple is then
[pattern matched][pattern-matching] to whether the position is even or odd.
A new tuple is passed into the next iteration of `foldRight()`, with the sum increased according to the position,
and the position increased by `1`.
Note that the original tuple does not have its position or sum mutated, but a new tuple is created from the operations
on the position and sum, thus supporting [immutability][immutability].

After `foldRight()` is done, the final tuple is destructured into `total`, and its position is ignored with `_`.
The input is valid if it has at least two digits and the `total` is evenly divisible by `10`.

[exists]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[filter]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[foldright]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
