# `foldLeft()`

```scala
object Bearing extends Enumeration {
  val North = Value(0)
  val East = Value(1)
  val South = Value(2)
  val West = Value(3)
}

case class Robot(bearing: Bearing.Value, pos: (Int, Int)) {

  val DirectionCount = 4

  def turnRight: Robot =
    Robot(Bearing.apply((bearing.id + 1) % DirectionCount), pos)

  def turnLeft: Robot =
    Robot(
      Bearing.apply((DirectionCount + (bearing.id - 1)) % DirectionCount),
      pos
    )

  def coordinates: (Int, Int) = pos

  def advance: Robot =
    bearing match {
      case Bearing.North => Robot(bearing, (pos._1, pos._2 + 1))
      case Bearing.South => Robot(bearing, (pos._1, pos._2 - 1))
      case Bearing.East  => Robot(bearing, (pos._1 + 1, pos._2))
      case Bearing.West  => Robot(bearing, (pos._1 - 1, pos._2))
    }

  def simulate(orders: String): Robot =
    orders.foldLeft(this)((robbie, cmd) =>
      cmd match {
        case 'L' => robbie.turnLeft
        case 'R' => robbie.turnRight
        case 'A' => robbie.advance
      }
    )
}
```

This approach starts be defining the `Bearing` [Enumeration][enumeration] in the Scala 2 way.

The `Robot` `class` starts by defining an [immutable][immutability] value that represents the count of the four directions being used
in the program.

The `turnRight()` method uses the [`id`][value-id] member of the `Value` `class` to get the value of the `Enumeration`.
So, the `id` of a variable set to `Bearing.West` would be `3`.
The [`apply()`][enumeration-apply] method is used to set a `Bearing` `Enumeration` from a value.
So, if the robot were facing west and turned right to the north, `1` would be added to its value of `3`, and the modulus operator
would give a remainder of `0` when divided by the `DirectionCount` of `4`.
`0` would be passed to `apply()`, which would create a `Bearing` of `North`.

The `turnLeft()` method uses the same methods.
So, if the robot were facing north and turned left to the west, `1` would be subracted from its value of `0`, 
and `-1` would be added to `DirectionCount` to get `3`.
The modulus operator would give a remainder of `3` when divided by the `DirectionCount` of `4`, and
`3` would be passed to `apply()`, which would create a `Bearing` of `West`.

if the robot were facing east and turned left to the north, `1` would be subracted from its value of `1`, 
and `0` would be added to `DirectionCount` to get `4`.
The modulus operator would give a remainder of `0` when divided by the `DirectionCount` of `4`. and
`0` would be passed to `apply()`, which would create a `Bearing` of `North`.

At the time of the writing, Scala 3 is not yet supported on the Scala track.
The Scala 3 way of [`enum`][enum]s would be handled similarly, with the [`ordinal()`][ordinal] method functioning like
`id` and the `fromOrdinal()` method functioning like `apply()`, like so

```scala
enum Bearing {
  case North, East, South, West
}

// code snipped

def turnRight: Robot =
  Robot(Bearing.fromOrdinal((bearing.ordinal + 1) % DirectionCount), pos)

def turnLeft: Robot =
  Robot(
    Bearing.fromOrdinal(
      (DirectionCount + (bearing.ordinal - 1)) % DirectionCount
    ),
    pos
  )
```

The `simulate()` method uses the [`foldLeft()`][foldleft] method to iterate the characters of the orders `String`.

```scala
def simulate(orders: String): Robot =
  orders.foldLeft(this)((robbie, cmd) =>
    cmd match {
      case 'L' => robbie.turnLeft
      case 'R' => robbie.turnRight
      case 'A' => robbie.advance
    }
  )
```
The initial value for `foldLeft()` is set to the `Robot` instance.
The first iteration passes the `Robot` instance and the first character of the orders `String` into a [lambda][lambda]
which uses a [`match`][match] for [pattern matching][pattern-matching] on the command letter.
The `match` returns a new `Robot` which is passed to the next iteration of `foldLeft` along with the next
character of the orders `String`.
When `foldLeft()` is done iterating through all of the letters, it returns the `Robot` instance from its last iteration.

[foldleft]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#foldLeft[B](z:B)(op:(B,Char)=%3EB):B
[enumeration]: https://www.scala-lang.org/api/2.13.10/scala/Enumeration.html
[value-id]: https://www.scala-lang.org/api/2.13.10/scala/Enumeration$Value.html#id:Int
[enumeration-apply]: https://www.scala-lang.org/api/2.13.10/scala/Enumeration$Value.html#id:Int
[modulus]: https://www.geeksforgeeks.org/operators-in-scala/
[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[ordinal]: https://www.scala-lang.org/api/3.2.2/scala/reflect/Enum.html#
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[fluent]: https://alvinalexander.com/scala/how-to-fluent-style-programming-methods-in-scala/
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
