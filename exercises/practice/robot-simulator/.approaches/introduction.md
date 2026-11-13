# Introduction

There are at least a couple of idiomatic ways to solve Robot Simulator.
One is to use the [`foldLeft()`][foldleft] method to implement `simulate()`.
Another is to use [recursion][recursion] to implement `simulate()`.

## General Guidance

The handling of the `Bearing` values is an important implementation detail.
One way to address it is with an [`enum`][enum], whose `ordinal` and `fromOrdinal()` make turning a matter of arithmetic.

Another consideration is how the tests enforce implementing the robot with a [Fluent API][fluent] which lends itself
to [immutability][immutability].
The methods that operate on the robot don't change its state but create a new robot with a new state.
The less state to manage means the less chance for state to accidentally be changed to something unintended.
The lower the chance for unintended consequences, the more predictability in the code, and the less chance for bugs.

## Approach: `foldLeft()`

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

For more information, check the [`foldLeft()` approach][approach-foldleft].

## Approach: Recursion

```scala
import scala.annotation.tailrec

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

  @tailrec
  final def simulate(orders: String): Robot =
    if orders.isEmpty then this
    else
      val next = orders.head match
        case 'L' => turnLeft
        case 'R' => turnRight
        case 'A' => advance
      next.simulate(orders.tail)
```

For more information, check the [recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[fluent]: https://alvinalexander.com/scala/how-to-fluent-style-programming-methods-in-scala/
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/robot-simulator/approaches/foldleft
[approach-recursion]: https://exercism.org/tracks/scala/exercises/robot-simulator/approaches/recursion
