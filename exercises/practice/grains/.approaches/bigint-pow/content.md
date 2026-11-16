# `BigInt` with `pow()`

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(2).pow(n - 1))

  def total: BigInt = BigInt(2).pow(64) - 1
```

The `square()` method returns an [`Option`][option], because only the squares from `1` through `64` are on the board.
[`Option.when()`][option-when] takes a condition and a value.
If the condition is `true`, it gives the value wrapped in a `Some`; otherwise it gives `None`.
The value is evaluated only if the condition is `true`, so no power is calculated for a square that is not on the board.

The number of grains doubles from one square to the next, starting with `1` grain on the first square.
So square `n` holds `2` to the power of `n - 1` grains.
`BigInt(2)` creates a [`BigInt`][bigint] with the value `2`, and its [`pow()`][bigint] method raises it to the given power.
The result is a `BigInt` too, which matters for the last square: `2` to the power of `63` is one more than a `Long` can hold.

The `total` method does not need to visit the squares.
The grains on the first `n` squares add up to `2` to the power of `n`, minus `1`:
one square holds `1` grain, two squares hold `1 + 2 = 3`, three squares hold `1 + 2 + 4 = 7`, and so on.
For all 64 squares that is `2` to the power of `64`, minus `1`.

[option]: https://www.scala-lang.org/api/current/scala/Option.html
[option-when]: https://www.scala-lang.org/api/current/scala/Option$.html
[bigint]: https://www.scala-lang.org/api/current/scala/math/BigInt.html
