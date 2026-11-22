# Introduction

The points depend only on how far the dart lands from the center of the target, which is at the coordinates `(0, 0)`.
By the Pythagorean theorem that distance is the square root of `x * x + y * y`.
The distance is then compared with the radii of the three circles, from the smallest to the largest: `1`, `5` and `10`.
A dart that lands exactly on a circle is still inside it, so the comparisons use `<=`.

The approaches differ in two places: how the distance is calculated, and how it is compared with the radii.

For the comparison, one approach uses a chain of `if` expressions, another the guards of a `match` expression,
and a third keeps the radii and their points in a list.
For the distance, `math.hypot()` can replace the square root written by hand,
and comparing the squared distance with the squared radii needs no square root at all.

## Approach: `if` expressions

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    val distance = math.sqrt(x * x + y * y)
    if distance <= 1 then 10
    else if distance <= 5 then 5
    else if distance <= 10 then 1
    else 0
```

For more information, check the [`if` expressions approach][approach-if-chain].

## Approach: `match` with guards

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

For more information, check the [`match` with guards approach][approach-match-guards].

## Approach: `math.hypot()`

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    math.hypot(x, y) match
      case distance if distance <= 1  => 10
      case distance if distance <= 5  => 5
      case distance if distance <= 10 => 1
      case _                          => 0
```

For more information, check the [`math.hypot()` approach][approach-hypot].

## Approach: squared distance

```scala
object Darts:
  def score(x: Double, y: Double): Int =
    val distanceSquared = x * x + y * y
    if distanceSquared <= 1 then 10
    else if distanceSquared <= 25 then 5
    else if distanceSquared <= 100 then 1
    else 0
```

For more information, check the [squared distance approach][approach-squared-distance].

## Approach: lookup table

```scala
object Darts:
  private val rings = List(1.0 -> 10, 5.0 -> 5, 10.0 -> 1)

  def score(x: Double, y: Double): Int =
    val distance = math.hypot(x, y)
    rings.collectFirst { case (radius, points) if distance <= radius => points }.getOrElse(0)
```

For more information, check the [lookup table approach][approach-lookup-table].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The chain of `if` expressions is the most direct way to write the rules, and it is easy to read for three circles.
The `match` with guards says the same thing in a different shape; which of the two reads better is a matter of taste.

`math.hypot()` names what is being calculated, so it is a good replacement for the square root in any of the approaches.
The squared distance leaves the square root out, at the price of radii that are no longer the numbers from the instructions.

The lookup table separates the data from the logic.
That is more than this target needs, but it is the approach that would change the least if circles were added or their points changed.

[approach-if-chain]: https://exercism.org/tracks/scala/exercises/darts/approaches/if-chain
[approach-match-guards]: https://exercism.org/tracks/scala/exercises/darts/approaches/match-guards
[approach-hypot]: https://exercism.org/tracks/scala/exercises/darts/approaches/hypot
[approach-squared-distance]: https://exercism.org/tracks/scala/exercises/darts/approaches/squared-distance
[approach-lookup-table]: https://exercism.org/tracks/scala/exercises/darts/approaches/lookup-table
