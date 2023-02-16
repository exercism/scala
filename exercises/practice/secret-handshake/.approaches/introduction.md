# Introduction

There are many ways to solve Secret Handshake.
One approach is to use the [`foldLeft()`][foldleft] method on a [`Range`][range] and [`reverse()`][reverse] if needed.
Another approach is to either append or prepend inside `foldLeft()`.

## General guidance

As always, a concern is to try to maintain [immutability][immutability].

## Approach: `foldLeft()` and `reverse()`

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

For more information, check the [`foldLeft()` and `reverse()` approach][approach-foldleft-and-reverse].

## Approach: `foldLeft()` and appended or prepended

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

For more information, check the [`foldLeft()` with appended or prepended approach][approach-foldleft-with-appended-or-prepended].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between the approaches can be made by personal preference.

[range]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[reverse]: https://www.scala-lang.org/api/2.12.x/scala/collection/immutable/Seq.html#reverse:Repr
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-foldleft-and-reverse]: https://exercism.org/tracks/scala/exercises/secret-handshake/approaches/foldleft-and-reverse
[approach-foldleft-with-appended-or-prepended]: https://exercism.org/tracks/scala/exercises/secret-handshake/approaches/foldleft-with-appended-or-prepended
