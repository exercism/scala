# `foldLeft()` and `reverse()`

```scala
object SecretHandshake:

  private val Signs        = Vector("wink", "double blink", "close your eyes", "jump")
  private val ReverseSigns = 16

  def commands(orders: Int): Seq[String] =
    val signs = Signs.indices.foldLeft(Seq.empty[String]): (output, index) =>
      if (1 << index & orders) != 0 then output :+ Signs(index) else output

    if (orders & ReverseSigns) == 0 then signs else signs.reverse
```

This approach starts by defining a private [Vector][vector] for holding the signs, and an `Int` for the reverse value.

The [`indices`][indices] of the signs `Vector` give a [`Range`][range] from `0` up to but not including its length.
Each number in the `Range` is passed to the [`foldLeft()`][foldleft] method as an index.

The `foldLeft()` is initialized with an empty `Seq[String]` for the output value.
Its [lambda][lambda] follows a [colon instead of being wrapped in braces][fewer-braces].
The lambda uses the bitwise shift left [operator][operators] to see if the input number has a binary `1` in the same position
as `1` shifted left by the index.
If so, then the element for that index in the signs `Vector` is appended to the output `Seq`.
If not, the output `Seq` is passed as is to the next iteration of `foldLeft()`.

After `foldLeft()` is done, `signs` holds the output `Seq`.

The method uses an [`if` expression][if], which Scala uses where other languages have a ternary operator,
to return either the `Seq` as is or the `Seq` in [`reverse`][reverse],
depending on whether the input number has a bit set in the same position as binary `16` (`10000`).

## Example

If the input number is `26`, its value in binary is `11010`. The `1` bits are set at five, four and two from the right,
which are the indexes `4`, `3` and `1`.
- The element in the signs `Vector` at index `1` is "double blink", so that would get appended to the output `Seq`.
- The element in the signs `Vector` at index `3` is "jump", so that would get appended to the output `Seq`, after "double blink".
- Index `4` is beyond the length of the `Vector`, but `1` shifted left 4 places is `10000`, which is the binary value for `16`,
so "double blink" and "jump" would be reversed and returned from `commands()` as `Seq("jump", "double blink")`.

[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[indices]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[operators]: https://www.geeksforgeeks.org/operators-in-scala/
[if]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
[reverse]: https://www.scala-lang.org/api/current/scala/collection/SeqOps.html
