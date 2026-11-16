# One pass with `foldLeft()`

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

This approach goes through the numbers once, and collects the sum of the numbers and the sum of their squares at the same time.

The work is done by the private method `sums()`, which returns both sums as a [tuple][tuple].

`1 to n` creates a [`Range`][range] of the numbers from `1` through `n`.
The [`foldLeft()`][range] method goes through them with a value that it carries from one number to the next.
It starts with the tuple `(0, 0)`, and passes the tuple collected so far and the next number to its function.
The result of the function becomes the tuple for the next number, and the last one is the result of `foldLeft()`.

The function is written as a `case`, so that its arguments can be taken apart by a [pattern][pattern-matching]:
`((sum, squares), i)` gives names to the two sums in the tuple and to the number.
The new tuple has the number added to the first sum, and its square added to the second.
The function is passed after a colon and on an indented line, which Scala 3 allows instead of [enclosing it in braces][optional-braces].

The three public methods take what they need from the tuple.
In Scala 3 an element of a tuple can be read by its index, starting from `0`:
`sums(n)(0)` is the sum of the numbers, and `sums(n)(1)` is the sum of the squares.
`differenceOfSquares()` needs both, so it takes the tuple apart into two values with `val (sum, squares) = sums(n)`.

Going through the numbers once instead of twice is the point of this approach, and `differenceOfSquares()` is the method that gains from it.
The other two methods each use only one of the sums, so for them the [ranges approach][approach-ranges] does the same work with less code.

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[optional-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[approach-ranges]: https://exercism.org/tracks/scala/exercises/difference-of-squares/approaches/ranges
