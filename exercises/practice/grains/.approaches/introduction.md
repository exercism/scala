# Introduction

The number of grains doubles from one square to the next, so square `n` holds `2` to the power of `n - 1` grains:
`1` on the first square, `2` on the second, `4` on the third, and so on.

The only real difficulty is the size of the numbers.
The last square holds `2` to the power of `63` grains, which is `9223372036854775808`.
That is exactly one more than [`Long.MaxValue`][long], the largest value a `Long` can hold, so the grains on square 64 do not fit in a `Long`.
The total for the whole board is almost twice as large.
That is why the methods return [`BigInt`][bigint], an integer that grows to whatever size the number needs.

There are several idiomatic ways to calculate the numbers.
One approach raises `2` to a power with the `pow()` method of `BigInt`.
Another gets the same power of `2` by shifting a bit to the left.
Both calculate the total directly, as `2` to the power of `64` minus `1`.
A third approach calculates the total by adding up the grains on all 64 squares.

## Approach: `BigInt` with `pow()`

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(2).pow(n - 1))

  def total: BigInt = BigInt(2).pow(64) - 1
```

For more information, check the [`BigInt` with `pow()` approach][approach-bigint-pow].

## Approach: bit shifting

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(1) << (n - 1))

  def total: BigInt = (BigInt(1) << 64) - 1
```

For more information, check the [bit shifting approach][approach-bit-shifting].

## Approach: summing the squares

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(2).pow(n - 1))

  def total: BigInt = (1 to 64).flatMap(square).sum
```

For more information, check the [summing the squares approach][approach-summing-squares].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

`pow()` says what the exercise says: the number of grains is a power of `2`.
Bit shifting gives the same numbers, and reads naturally to those who think of a power of `2` as a single bit.

Summing the squares follows the wording of the instructions most closely, and it shows how a collection of `Option`s is flattened.
It also calculates 64 powers and adds them up for a number that the other two approaches get in one step,
so the direct calculation is the better choice for `total`.

[long]: https://www.scala-lang.org/api/current/scala/Long$.html
[bigint]: https://www.scala-lang.org/api/current/scala/math/BigInt.html
[approach-bigint-pow]: https://exercism.org/tracks/scala/exercises/grains/approaches/bigint-pow
[approach-bit-shifting]: https://exercism.org/tracks/scala/exercises/grains/approaches/bit-shifting
[approach-summing-squares]: https://exercism.org/tracks/scala/exercises/grains/approaches/summing-squares
