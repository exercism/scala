# Introduction

There are at least a couple of idiomatic ways to solve Robot Simulator.
One is to use the [`foldLeft()`][foldleft] method to implement `simulate()`.
Another is to use [recursion][recursion] to implement `simulate()`.

## General Guidance

The handling of the `Bearing` values is an important implementation detail.
One way to address it is to give the `Bearing` [`enum`][enum] its own `turnRight` and `turnLeft` methods,
each one a [`match`][match] on `this` with a `case` for every direction.
Because the `match` is on an `enum`, the compiler checks that it is exhaustive and warns if a direction is missing.
Another way is arithmetic on the [`ordinal`][ordinal] of a bearing, turning it back into a `Bearing` with `fromOrdinal()`,
which is shorter but hides the directions behind numbers.

Another consideration is how the tests enforce implementing the robot with a [Fluent API][fluent] which lends itself
to [immutability][immutability].
The methods that operate on the robot don't change its state but create a new robot with a new state.
A [case class][case-class] makes that easy with its `copy` method, which takes only the fields that change.
The less state to manage means the less chance for state to accidentally be changed to something unintended.
The lower the chance for unintended consequences, the more predictability in the code, and the less chance for bugs.

## Approach: `foldLeft()`

```scala
enum Bearing:
  case North, East, South, West

  def turnRight: Bearing = this match
    case North => East
    case East  => South
    case South => West
    case West  => North

  def turnLeft: Bearing = this match
    case North => West
    case West  => South
    case South => East
    case East  => North

case class Robot(bearing: Bearing, coordinates: (Int, Int)):
  def turnRight: Robot = copy(bearing = bearing.turnRight)
  def turnLeft: Robot  = copy(bearing = bearing.turnLeft)

  def advance: Robot =
    val (x, y) = coordinates
    bearing match
      case Bearing.North => copy(coordinates = (x, y + 1))
      case Bearing.East  => copy(coordinates = (x + 1, y))
      case Bearing.South => copy(coordinates = (x, y - 1))
      case Bearing.West  => copy(coordinates = (x - 1, y))

  def simulate(instructions: String): Robot = instructions.foldLeft(this): (robot, instruction) =>
    instruction match
      case 'L' => robot.turnLeft
      case 'R' => robot.turnRight
      case 'A' => robot.advance
```

For more information, check the [`foldLeft()` approach][approach-foldleft].

## Approach: Recursion

```scala
import scala.annotation.tailrec

enum Bearing:
  case North, East, South, West

  def turnRight: Bearing = this match
    case North => East
    case East  => South
    case South => West
    case West  => North

  def turnLeft: Bearing = this match
    case North => West
    case West  => South
    case South => East
    case East  => North

case class Robot(bearing: Bearing, coordinates: (Int, Int)):
  def turnRight: Robot = copy(bearing = bearing.turnRight)
  def turnLeft: Robot  = copy(bearing = bearing.turnLeft)

  def advance: Robot =
    val (x, y) = coordinates
    bearing match
      case Bearing.North => copy(coordinates = (x, y + 1))
      case Bearing.East  => copy(coordinates = (x + 1, y))
      case Bearing.South => copy(coordinates = (x, y - 1))
      case Bearing.West  => copy(coordinates = (x - 1, y))

  @tailrec
  final def simulate(instructions: String): Robot =
    if instructions.isEmpty then this
    else
      val next = instructions.head match
        case 'L' => turnLeft
        case 'R' => turnRight
        case 'A' => advance
      next.simulate(instructions.tail)
```

For more information, check the [recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[ordinal]: https://www.scala-lang.org/api/current/scala/reflect/Enum.html
[fluent]: https://alvinalexander.com/scala/how-to-fluent-style-programming-methods-in-scala/
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[case-class]: https://docs.scala-lang.org/tour/case-classes.html
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/robot-simulator/approaches/foldleft
[approach-recursion]: https://exercism.org/tracks/scala/exercises/robot-simulator/approaches/recursion
