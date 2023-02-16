# `foldLeft()` and appended or prepended

```scala
object SecretHandshake {
  val SIGNS = Vector("wink", "double blink", "close your eyes", "jump")
  val REVERSE_SIGNS = 16

  def commands(orders: Int): Seq[String] = {
  
    def stitch(coll: Seq[String], that: String): Seq[String] =
      if ((orders & REVERSE_SIGNS) == 0) coll.:+(that) else coll.+:(that)

    (0 until SIGNS.length)
      .foldLeft(Seq(): Seq[String])((output, index) =>
        if ((1 << index & orders) != 0) stitch(output, SIGNS(index))
        else output
      )
  }
}
```

This approach starts by defining a [Vector][vector] for holding the signs, and an `Int` binding for the reverse value.

The `command()` method starts by defining a nested method that takes a `Seq[String]` and a `String`.
Since the nested method has access to bindings defined in its enclosing scope, it can check if the input number has a bit set
in the same position as binary `16` (`10000`).
If not, it will append the `String` argument to the `Seq`.
If so, it will prepend the `String` argument to the `Seq`.

A [`Range`][range] is defined from `0` up to but not including the length of the signs `Vector`.
Each number in the `Range` is passed to the [`foldLeft()`][foldleft] method for an index value.

The `foldLeft()` is initialized with an empty `Seq[String]` for the output value.
The [lambda][lambda] in `foldLeft` uses the bitwise shift left [operator][operators] to see if the input number has a binary `1` in the same position
as `1` shifted left for the index value.
If so, then the element for that index in the signs `Vector` is combined with the output `Seq` by the nested method.
If not, the output `Seq` is passed as is to the next iteration of `foldLeft()`.

After `foldLeft()` is done, the `commands()` method returns the output `Seq`.

## Scala version 3.x

```exercism/caution
At the time of writing, this approach only works when running locally with Scala version 3.x.
```

```scala
object SecretHandshake {
  val SIGNS = Vector("wink", "double blink", "close your eyes", "jump")
  val REVERSE_SIGNS = 16

  def commands(orders: Int): Seq[String] = {
    object MySeqOps {
      extension (coll: Seq[String]) {
        def stitch(that: String): Seq[String] =
          if ((orders & REVERSE_SIGNS) == 0) coll.:+(that) else coll.+:(that)
      }
    }
    import MySeqOps.stitch

    (0 until SIGNS.length)
      .foldLeft(Seq(): Seq[String])((output, index) =>
        if ((1 << index & orders) != 0) output stitch SIGNS(index)
        else output
      )
  }
}
```

The `command()` method starts by defining an [extension method][extension-methods] for a `Seq[String]`.
Since the extension method has access to bindings defined in its enclosing scope, it can check if the input number has a bit set
in the same position as binary `16` (`10000`).
If not, it will append the `String` argument to the `Seq`.
If so, it will prepend the `String` argument to the `Seq`.
Even though the extension method is defined here, it must be imported to be used.

It works much like the approach described above.
If the input number has a binary `1` in the same position as `1` shifted left for the index value,
then the element for that index in the signs `Vector` is combined with the output `Seq` by the extension method.

[extension-methods]: https://docs.scala-lang.org/scala3/reference/contextual/extension-methods.html
[vector]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Vector.html
[range]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[operators]: https://www.geeksforgeeks.org/operators-in-scala/
