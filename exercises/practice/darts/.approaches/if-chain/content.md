# `if` expressions

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    val distance = math.sqrt(x * x + y * y)
    if distance <= 1 then 10
    else if distance <= 5 then 5
    else if distance <= 10 then 1
    else 0
```

This approach starts by calculating the distance of the dart from the center of the target.
The center is at `(0, 0)`, so by the Pythagorean theorem the distance is the square root of `x * x + y * y`.
The [`math.sqrt()`][math] method calculates the square root.

The distance is then compared with the radii of the circles in a chain of [`if` expressions][if], starting with the smallest circle.
The order matters: a dart inside the inner circle is also inside the other two, so the first comparison that is `true` has to be the one that decides.
Each comparison uses `<=`, because a dart that lands exactly on a circle counts as being inside it.

If the distance is larger than `10`, then the dart missed the target, and the last `else` gives `0`.

In Scala `if` is an expression: it has a value, which here is the value of the branch that was taken.
Since the chain is the last expression of the method, its value is returned without needing the `return` keyword.

[math]: https://www.scala-lang.org/api/current/scala/math.html
[if]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
