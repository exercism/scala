# Formulas

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

Both sums have a formula that gives the result directly.

The sum of the numbers from `1` through `n` is `n * (n + 1) / 2`.
One way to see it is to add the numbers in pairs from both ends: `1 + n`, `2 + (n - 1)`, and so on.
Every pair adds up to `n + 1`, and for an even `n` there are `n / 2` pairs.
The formula holds for an odd `n` as well.
The `squareOfSum()` method keeps this sum in a value and multiplies it by itself.

The sum of the squares of the numbers from `1` through `n` is `n * (n + 1) * (2 * n + 1) / 6`.
For `n = 5` that is `5 * 6 * 11 / 6 = 55`, which is `1 + 4 + 9 + 16 + 25`.

In both formulas the multiplications come before the division, and the order matters.
Integer division drops the remainder: `5 / 2` is `2`.
Written as `n / 2 * (n + 1)`, the first formula would give `2 * 6 = 12` for `n = 5`, and not `15`.
Multiplying first avoids that, because the product is always divisible:
one of the two numbers `n` and `n + 1` is even, so `n * (n + 1)` can be divided by `2` with no remainder,
and `n * (n + 1) * (2 * n + 1)` can always be divided by `6`.

For `n = 1024`, the sum of squares is `358438400`, but the product before division is six times as large and exceeds `Int.MaxValue`.
Starting with `n.toLong` keeps the product in a `Long` until the division is complete.
The final `toInt` conversion preserves the result whenever the sum of squares fits in an `Int`.

The `differenceOfSquares()` method calls the other two methods and subtracts one result from the other.

The number of arithmetic operations is independent of `n`.
