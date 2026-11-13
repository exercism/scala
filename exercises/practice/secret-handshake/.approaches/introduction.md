# Introduction

There are many ways to solve Secret Handshake.
One approach is to use the [`foldLeft()`][foldleft] method on a [`Range`][range] and [`reverse`][reverse] if needed.
Another approach is to either append or prepend inside `foldLeft()`.

## General guidance

As always, a concern is to try to maintain [immutability][immutability].

## Approach: `foldLeft()` and `reverse()`

```scala
object SecretHandshake:

  private val Signs        = Vector("wink", "double blink", "close your eyes", "jump")
  private val ReverseSigns = 16

  def commands(orders: Int): Seq[String] =
    val signs = Signs.indices.foldLeft(Seq.empty[String]): (output, index) =>
      if (1 << index & orders) != 0 then output :+ Signs(index) else output

    if (orders & ReverseSigns) == 0 then signs else signs.reverse
```

For more information, check the [`foldLeft()` and `reverse()` approach][approach-foldleft-and-reverse].

## Approach: `foldLeft()` and appended or prepended

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

For more information, check the [`foldLeft()` with appended or prepended approach][approach-foldleft-with-appended-or-prepended].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between the approaches can be made by personal preference.

[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[foldleft]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[reverse]: https://www.scala-lang.org/api/current/scala/collection/SeqOps.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-foldleft-and-reverse]: https://exercism.org/tracks/scala/exercises/secret-handshake/approaches/foldleft-and-reverse
[approach-foldleft-with-appended-or-prepended]: https://exercism.org/tracks/scala/exercises/secret-handshake/approaches/foldleft-with-appended-or-prepended
