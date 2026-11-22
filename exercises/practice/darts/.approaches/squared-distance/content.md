# Squared distance

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    val distanceSquared = x * x + y * y
    if distanceSquared <= 1 then 10
    else if distanceSquared <= 25 then 5
    else if distanceSquared <= 100 then 1
    else 0
```

The distance of the dart from the center of the target is the square root of `x * x + y * y`.
This approach does not calculate it.
It stops at `x * x + y * y`, which is the distance squared, and compares that with the squares of the radii: `1`, `25` and `100`.

In exact arithmetic, comparing squared distances gives the same result because distances and radii are non-negative.
With `Double`, rounding can make the two approaches score a dart differently when it lands extremely close to a circle.

The comparisons are made in a chain of [`if` expressions][if], starting with the smallest circle.
A dart inside the inner circle is also inside the other two, so the first comparison that is `true` has to be the one that decides.
If none of them is, then the dart missed the target, and the last `else` gives `0`.

Leaving out the square root saves a calculation.
The price is that the numbers in the code are no longer the radii from the instructions:
a reader has to recognize `25` as `5` squared and `100` as `10` squared.

[if]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
