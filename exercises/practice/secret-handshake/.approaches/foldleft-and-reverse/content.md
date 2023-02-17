# `foldLeft()` and `reverse()`

```scala
object SecretHandshake {
  val SIGNS = Vector("wink", "double blink", "close your eyes", "jump")
  val REVERSE_SIGNS = 16

  def commands(orders: Int): Seq[String] = {
    val signs =
      (0 until SIGNS.length)
        .foldLeft(Seq(): Seq[String])((output, index) =>
          if ((1 << index & orders) != 0) output :+ SIGNS(index)
          else output
        )

    if ((orders & REVERSE_SIGNS) == 0) signs else signs.reverse
  }
}
```

This approach starts by defining a [Vector][vector] for holding the signs, and an `Int` binding for the reverse value.

The `command()` method starts by defining a [`Range`][range] from `0` up to but not including the length of the signs `Vector`.
Each number in the `Range` is passed to the [`foldLeft()`][foldleft] method for an index value.

The `foldLeft()` is initialized with an empty `Seq[String]` for the output value.
The [lambda][lambda] in `foldLeft` uses the bitwise shift left [operator][operators] to see if the input number has a binary `1` in the same position
as `1` shifted left for the index value.
If so, then the element for that index in the signs `Vector` is appended to the output `Seq`.
If not, the output `Seq` is passed as is to the next iteration of `foldLeft()`.

After `foldLeft()` is done, the `signs` binding is set to the output `Seq`.

The function uses a [ternary expression][ternary] to return either the `Seq` as is or the `Seq` in [`reverse()`][reverse],
depending if the input number has a bit set in the same position as binary `16` (`10000`).

## Example

If the input number is `26`, its value in binary is `11010`. The `1` bits are set at four from the right, three from the right and
one from the right.
- The element in the signs `Vector` at index `1` is "double blink", so that would get appended to the output `Seq`.
- The element in the signs `Vector` at index `3` is "jump", so that would get appended to the output `Seq`, after "double blink".
- Index `4` is beyond the length of the `Vector`, but `1` shifted left 4 places is `10000`, which is the binary value for `16`,
so "double blink" and "jump" would be reversed and returned from `commands()` as `("jump", "double blink")`.


[vector]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Vector.html
[range]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[operators]: https://www.geeksforgeeks.org/operators-in-scala/
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[reverse]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Seq.html#reverse:Repr
