# `foldLeft()`

```scala
enum Bearing:
  case North, East, South, West

case class Robot(bearing: Bearing, pos: (Int, Int)):

  private val DirectionCount = Bearing.values.length

  def turnRight: Robot = Robot(Bearing.fromOrdinal((bearing.ordinal + 1) % DirectionCount), pos)

  def turnLeft: Robot = Robot(Bearing.fromOrdinal((DirectionCount + bearing.ordinal - 1) % DirectionCount), pos)

  def coordinates: (Int, Int) = pos

  def advance: Robot = bearing match
    case Bearing.North => Robot(bearing, (pos._1, pos._2 + 1))
    case Bearing.South => Robot(bearing, (pos._1, pos._2 - 1))
    case Bearing.East  => Robot(bearing, (pos._1 + 1, pos._2))
    case Bearing.West  => Robot(bearing, (pos._1 - 1, pos._2))

  def simulate(orders: String): Robot =
    orders.foldLeft(this): (robbie, cmd) =>
      cmd match
        case 'L' => robbie.turnLeft
        case 'R' => robbie.turnRight
        case 'A' => robbie.advance
```

This approach starts by defining the `Bearing` [`enum`][enum] with the four directions in clockwise order.
Each `case` has an [`ordinal`][ordinal], its position in the declaration: `North` is `0`, `East` is `1`, `South` is `2` and `West` is `3`.
`Bearing.fromOrdinal()` goes the other way, from a number to a `Bearing`, and `Bearing.values` holds all of the cases.

The `Robot` case class starts by defining an [immutable][immutability] value, `DirectionCount`, for the number of directions.

The `turnRight` method adds `1` to the `ordinal` of the bearing.
So, if the robot were facing west and turned right to the north, `1` would be added to its ordinal of `3`, and the modulus operator
would give a remainder of `0` when divided by the `DirectionCount` of `4`.
`0` would be passed to `fromOrdinal()`, which would give a `Bearing` of `North`.

The `turnLeft` method subtracts `1` instead, after adding `DirectionCount` so that the number never goes below `0`.
So, if the robot were facing north and turned left to the west, `4 + 0 - 1` is `3`.
The modulus operator would give a remainder of `3` when divided by the `DirectionCount` of `4`, and
`3` would be passed to `fromOrdinal()`, which would give a `Bearing` of `West`.

If the robot were facing east and turned left to the north, `4 + 1 - 1` is `4`.
The modulus operator would give a remainder of `0` when divided by the `DirectionCount` of `4`, and
`0` would be passed to `fromOrdinal()`, which would give a `Bearing` of `North`.

The `simulate()` method uses the [`foldLeft()`][foldleft] method to iterate the characters of the orders `String`.

```scala
def simulate(orders: String): Robot =
  orders.foldLeft(this): (robbie, cmd) =>
    cmd match
      case 'L' => robbie.turnLeft
      case 'R' => robbie.turnRight
      case 'A' => robbie.advance
```

The initial value for `foldLeft()` is the `Robot` instance.
The first iteration passes the `Robot` instance and the first character of the orders `String` into a [lambda][lambda],
which follows a [colon instead of being wrapped in braces][fewer-braces].
The lambda uses a [`match`][match] for [pattern matching][pattern-matching] on the command letter.
The `match` returns a new `Robot`, which is passed to the next iteration of `foldLeft()` along with the next
character of the orders `String`.
When `foldLeft()` is done iterating through all of the letters, it returns the `Robot` instance from its last iteration.

[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[ordinal]: https://www.scala-lang.org/api/current/scala/reflect/Enum.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
