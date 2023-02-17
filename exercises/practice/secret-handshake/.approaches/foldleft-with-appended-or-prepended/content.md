# `foldLeft()` and appended or prepended

```scala
object SecretHandshake {
  val SIGNS = Vector("wink", "double blink", "close your eyes", "jump")
  val REVERSE_SIGNS = 16

  def commands(orders: Int): Seq[String] = {
  
    val stitch =
      if ((orders & REVERSE_SIGNS) == 0)
        (coll: Seq[String], that: String) => coll.:+(that)
      else (coll: Seq[String], that: String) => coll.+:(that)

    (0 until SIGNS.length)
      .foldLeft(Seq(): Seq[String])((output, index) =>
        if ((1 << index & orders) != 0) stitch(output, SIGNS(index))
        else output
      )
  }
}
```

This approach starts by defining a [Vector][vector] for holding the signs, and an `Int` binding for the reverse value.

The `command()` method starts by defining a `val` for combining a `String` with a `Seq[String]`.
The combining is done by a function that is determined by if the input number has a bit set
in the same position as binary `16` (`10000`).
If not, the `val` is set to a function that will _append_ the `String` argument to the `Seq`.
If so, the `val` is set to a function that will _prepend_ the `String` argument to the `Seq`.

A [`Range`][range] is defined from `0` up to but not including the length of the signs `Vector`.
Each number in the `Range` is passed to the [`foldLeft()`][foldleft] method for an index value.

The `foldLeft()` is initialized with an empty `Seq[String]` for the output value.
The [lambda][lambda] in `foldLeft` uses the bitwise shift left [operator][operators] to see if the input number has a binary `1` in the same position
as `1` shifted left for the index value.
If so, then the element for that index in the signs `Vector` is combined with the output `Seq` by the function defined for combining
a `String` and `Seq[String]`.
If not, the output `Seq` is passed as is to the next iteration of `foldLeft()`.

After `foldLeft()` is done, the `commands()` method returns the output `Seq`.

[vector]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Vector.html
[range]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[operators]: https://www.geeksforgeeks.org/operators-in-scala/
