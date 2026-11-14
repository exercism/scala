# Ranges with `map()` and `sum`

```scala
object DifferenceOfSquares:

  def sumOfSquares(n: Int): Int = (1 to n).map(i => i * i).sum

  def squareOfSum(n: Int): Int =
    val sum = (1 to n).sum
    sum * sum

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
```

This approach does what the instructions say, one step at a time.

`1 to n` creates a [`Range`][range] of the numbers from `1` through `n`.

For the sum of the squares, the [`map()`][range] method applies a function to each number and collects the results.
The [lambda][lambda] `i => i * i` gives the square of a number, so the result is the collection of the squares.
The [`sum`][range] method adds them up.

For the square of the sum, `sum` is called on the range itself, which adds up the numbers.
The result is kept in a value, so it can be multiplied by itself without being calculated twice.

The `differenceOfSquares()` method does no calculation of its own.
It calls the other two methods and subtracts one result from the other.

[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
