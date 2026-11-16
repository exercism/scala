# `match` with guards

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    val distance = math.sqrt(x * x + y * y)
    distance match
      case _ if distance <= 1.0  => 10
      case _ if distance <= 5.0  => 5
      case _ if distance <= 10.0 => 1
      case _                     => 0
```

This approach starts by calculating the distance of the dart from the center of the target.
The center is at `(0, 0)`, so by the Pythagorean theorem the distance is the square root of `x * x + y * y`.
The [`math.sqrt()`][math] method calculates the square root.

The distance is then passed to a [`match`][match] expression.
The pattern `_` matches any value, so the patterns do not decide anything here.
The decision is made by the [guards][guard]: the conditions that follow the `if` in each `case`.
A `case` is chosen only if its guard is `true`.

The cases are tried from top to bottom, and the first one whose guard is `true` gives the value of the whole expression.
So the circles are listed from the smallest to the largest: a dart inside the inner circle is also inside the other two.
Each guard uses `<=`, because a dart that lands exactly on a circle counts as being inside it.

The last case has no guard.
It is chosen when the distance is larger than `10`, which means that the dart missed the target.

Since the `match` expression is the last expression of the method, its value is returned without needing the `return` keyword.

[math]: https://www.scala-lang.org/api/current/scala/math.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[guard]: https://docs.scala-lang.org/scala3/book/control-structures.html#using-if-guards-in-case-clauses
