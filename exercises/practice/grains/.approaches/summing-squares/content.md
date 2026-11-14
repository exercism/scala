# Summing the squares

```scala
object Grains:
  def square(n: Int): Option[BigInt] =
    Option.when(1 <= n && n <= 64)(BigInt(2).pow(n - 1))

  def total: BigInt = (1 to 64).flatMap(square).sum
```

The `square()` method is the same as in the [`BigInt` with `pow()` approach][approach-bigint-pow].
The difference is in `total`, which adds up the grains on every square of the board.

`1 to 64` creates the [`Range`][range] of the square numbers.
Passing each of them to `square()` gives an [`Option`][option]: a `Some` with the number of grains.
Using `map()` would therefore give a collection of `Option`s, which cannot be summed.

The [`flatMap()`][range] method solves that.
It expects a function that returns a collection for each element, and it joins those collections into one.
An `Option` can be used as a collection of one element for a `Some`, or of no elements for a `None`.
So `flatMap(square)` gives the numbers of grains themselves, and a square that is not on the board would simply add nothing.

The [`sum`][range] method then adds the numbers.
They are `BigInt`s, so the sum is a `BigInt` as well and does not overflow.

This follows the instructions step by step, but it calculates 64 powers and adds them up to get a number that is known in advance:
the grains on all the squares add up to `2` to the power of `64`, minus `1`.
The [`BigInt` with `pow()` approach][approach-bigint-pow] calculates that number directly.

[approach-bigint-pow]: https://exercism.org/tracks/scala/exercises/grains/approaches/bigint-pow
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[option]: https://www.scala-lang.org/api/current/scala/Option.html
