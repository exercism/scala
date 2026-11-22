# Bit shifting

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(1) << (n - 1))

  def total: BigInt = (BigInt(1) << 64) - 1
```

The `square()` method returns an [`Option`][option], because only the squares from `1` through `64` are on the board.
[`Option.when()`][option-when] gives its value wrapped in a `Some` if the condition is `true`, and `None` otherwise.

In binary, the number `1` is a single bit, and each power of `2` is that bit moved one more position to the left:
`1` is `2` to the power of `0`, `10` is `2` to the power of `1`, `100` is `2` to the power of `2`, and so on.
The [shift left operator][bigint] `<<` moves the bits of a number to the left by the given number of positions.
So `BigInt(1) << (n - 1)` is `2` to the power of `n - 1`, which is the number of grains on square `n`.

The `total` method shifts the bit by `64` positions and subtracts `1`.
Subtracting `1` from a power of `2` turns the single bit into a row of bits below it: `1000` minus `1` is `111`.
Here the result is 64 bits that are all set, one for each square of the board.

~~~~exercism/caution
The number that is shifted has to be a `BigInt`.
A `Long` has only 64 bits, and the leftmost of them holds the sign.
`1L << 63` sets that bit, so it is `Long.MinValue`, a negative number, instead of the number of grains on the last square.
~~~~

[option]: https://www.scala-lang.org/api/current/scala/Option.html
[option-when]: https://www.scala-lang.org/api/current/scala/Option$.html
[bigint]: https://www.scala-lang.org/api/current/scala/math/BigInt.html
