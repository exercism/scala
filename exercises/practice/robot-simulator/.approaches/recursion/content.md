# Recursion

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

This approach starts by importing the `tailrec` annotation.

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

The `simulate()` method uses [recursion][recursion] to iterate the characters of the orders `String`.

```scala
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

It is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].
It is `final`, because the compiler can only optimize a call on another `Robot` if no subclass can override the method.

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

The first call uses the `Robot` instance and the entire orders `String`.
If there are any letters left in the orders `String`,
a [`match`][match] is used for [pattern matching][pattern-matching] on the first letter of the `String`.
The [`head`][head] method is used to return the first letter.
The `match` gives the `next` `Robot`, on which `simulate()` is called again with
all of the characters after the first character of the orders `String`.
The [`tail`][tail] method returns all of the characters after the first character.
When `simulate()` is done iterating through all of the letters, it returns the `Robot` instance from its last call.

In this variation of the recursive approach, `simulate()` is called on the `Robot` that the `match` gives.
Another variation could use a separate method like so:

```scala
def simulate(orders: String): Robot = simulateRecur(orders, this)

@tailrec
private def simulateRecur(orders: String, robbie: Robot): Robot =
  if orders.isEmpty then robbie
  else
    orders.head match
      case 'L' => simulateRecur(orders.tail, robbie.turnLeft)
      case 'R' => simulateRecur(orders.tail, robbie.turnRight)
      case 'A' => simulateRecur(orders.tail, robbie.advance)
```

Either variation is idiomatic.
The first one may be preferred for being less verbose.

[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[ordinal]: https://www.scala-lang.org/api/current/scala/reflect/Enum.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tail]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
