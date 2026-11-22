# Lookup table

```scala
object Darts:
  private val rings = List(1.0 -> 10, 5.0 -> 5, 10.0 -> 1)

  def score(x: Double, y: Double): Int =
    val distance = math.hypot(x, y)
    rings.collectFirst { case (radius, points) if distance <= radius => points }.getOrElse(0)
```

This approach keeps the rules of the game as data.
The private value `rings` is a [`List`][list] of [tuples][tuple], each of which pairs the radius of a circle with the points for landing inside it.
The `->` operator is another way to write a tuple: `1.0 -> 10` is the same as `(1.0, 10)`.
The circles are listed from the smallest to the largest.

The `score()` method calculates the distance of the dart from the center of the target with [`math.hypot()`][math].

The [`collectFirst()`][list] method then goes through the list.
It takes a function written as a `case`, which is defined only for the values that the `case` matches.
The pattern `(radius, points)` takes the tuple apart, and the [guard][guard] that follows the `if` checks that the dart is inside the circle.
`collectFirst()` stops at the first tuple that matches and gives the value of the `case`, here the points, wrapped in a `Some`.
If no tuple matches, it gives `None`.

So the result is an [`Option`][option], and `None` means that the dart missed the target.
The [`getOrElse()`][option] method gives the points inside the `Some`, or `0` for `None`.

Adding a circle or changing the points is now a change to the list, and the method stays as it is.

[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[math]: https://www.scala-lang.org/api/current/scala/math.html
[guard]: https://docs.scala-lang.org/scala3/book/control-structures.html#using-if-guards-in-case-clauses
[option]: https://www.scala-lang.org/api/current/scala/Option.html
