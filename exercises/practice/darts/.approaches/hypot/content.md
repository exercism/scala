# `math.hypot()`

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    math.hypot(x, y) match
      case distance if distance <= 1  => 10
      case distance if distance <= 5  => 5
      case distance if distance <= 10 => 1
      case _                          => 0
```

The [`math.hypot()`][math] method returns the square root of `x * x + y * y`.
That is the length of the hypotenuse of a right triangle whose other sides are `x` and `y`, which is where the name comes from.
It is also the distance of the point `(x, y)` from `(0, 0)`, the center of the target.

Using it instead of `math.sqrt(x * x + y * y)` has two advantages.
The name says what is calculated, so there is no formula to read.
And `hypot()` gives the right result even when `x * x` would be too large or too small for a `Double`.
The coordinates of a dart are nowhere near those limits, so for this exercise the first advantage is the one that counts.

The result is passed directly to a [`match`][match] expression, without being kept in a value first.
The pattern `distance` matches any value and gives it that name, so the [guard][guard] that follows the `if` can use it.
The first `case` whose guard is `true` gives the value of the whole expression.

The circles are listed from the smallest to the largest, because a dart inside the inner circle is also inside the other two.
The last case is chosen when the distance is larger than `10`, which means that the dart missed the target.

[math]: https://www.scala-lang.org/api/current/scala/math.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[guard]: https://docs.scala-lang.org/scala3/book/control-structures.html#using-if-guards-in-case-clauses
