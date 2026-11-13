# `foldLeft()` and appended or prepended

```scala
object SecretHandshake:

  private val Signs        = Vector("wink", "double blink", "close your eyes", "jump")
  private val ReverseSigns = 16

  def commands(orders: Int): Seq[String] =
    val stitch: (Seq[String], String) => Seq[String] =
      if (orders & ReverseSigns) == 0 then (output, sign) => output :+ sign
      else (output, sign) => sign +: output

    Signs.indices.foldLeft(Seq.empty[String]): (output, index) =>
      if (1 << index & orders) != 0 then stitch(output, Signs(index)) else output
```

This approach starts by defining a private [Vector][vector] for holding the signs, and an `Int` for the reverse value.

The `commands()` method starts by defining a `stitch` function for combining a `String` with a `Seq[String]`.
Which function it is depends on whether the input number has a bit set in the same position as binary `16` (`10000`).
If not, `stitch` is a function that _appends_ the `String` to the `Seq` with `:+`.
If so, `stitch` is a function that _prepends_ the `String` to the `Seq` with `+:`.

The [`indices`][indices] of the signs `Vector` give a [`Range`][range] from `0` up to but not including its length.
Each number in the `Range` is passed to the [`foldLeft()`][foldleft] method as an index.

The `foldLeft()` is initialized with an empty `Seq[String]` for the output value.
Its [lambda][lambda] follows a [colon instead of being wrapped in braces][fewer-braces].
The lambda uses the bitwise shift left [operator][operators] to see if the input number has a binary `1` in the same position
as `1` shifted left by the index.
If so, then the element for that index in the signs `Vector` is combined with the output `Seq` by `stitch`.
If not, the output `Seq` is passed as is to the next iteration of `foldLeft()`.

After `foldLeft()` is done, the `commands()` method returns the output `Seq`.

[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[indices]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[operators]: https://www.geeksforgeeks.org/operators-in-scala/
