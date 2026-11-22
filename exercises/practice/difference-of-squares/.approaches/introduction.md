# Introduction

The exercise asks for two numbers and their difference:
the square of the sum of the first `n` natural numbers, and the sum of their squares.

There are two ways to get the sums.
One is to do what the words say: go through the numbers from `1` to `n` and add them up.
The other is to use the formulas that give each sum directly, with no numbers to go through.

## Approach: ranges with `map()` and `sum`

```scala
object DifferenceOfSquares:

  def sumOfSquares(n: Int): Int = (1 to n).map(i => i * i).sum

  def squareOfSum(n: Int): Int =
    val sum = (1 to n).sum
    sum * sum

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
```

For more information, check the [ranges with `map()` and `sum` approach][approach-ranges].

## Approach: formulas

```scala
object DifferenceOfSquares:

  def sumOfSquares(n: Int): Int =
    val last = n.toLong
    (last * (last + 1) * (2 * last + 1) / 6).toInt

  def squareOfSum(n: Int): Int =
    val sum = n * (n + 1) / 2
    sum * sum

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
```

For more information, check the [formulas approach][approach-formulas].

## Approach: one pass with `foldLeft()`

```scala
object DifferenceOfSquares:

  def sumOfSquares(n: Int): Int = sums(n)(1)

  def squareOfSum(n: Int): Int =
    val sum = sums(n)(0)
    sum * sum

  def differenceOfSquares(n: Int): Int =
    val (sum, squares) = sums(n)
    sum * sum - squares

  private def sums(n: Int): (Int, Int) =
    (1 to n).foldLeft((0, 0)):
      case ((sum, squares), i) => (sum + i, squares + i * i)
```

For more information, check the [one pass with `foldLeft()` approach][approach-foldleft].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The ranges approach reads like the instructions, and nothing in it has to be looked up or proved.

The formulas calculate the difference with a fixed number of arithmetic operations.
The price is that a reader has to know the formulas, or trust them.

The `foldLeft()` approach accumulates both sums in one traversal without building a collection of squares.
It is longer and harder to read than the ranges approach, and it is shown here mainly as a contrast:
collecting two results in one traversal is a technique worth knowing, but this exercise does not need it.

~~~~exercism/note
All three approaches return an `Int`, as the exercise asks.
The square of the sum grows quickly: it no longer fits in an `Int` when `n` is larger than `303`.
~~~~

[approach-ranges]: https://exercism.org/tracks/scala/exercises/difference-of-squares/approaches/ranges
[approach-formulas]: https://exercism.org/tracks/scala/exercises/difference-of-squares/approaches/formulas
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/difference-of-squares/approaches/foldleft
