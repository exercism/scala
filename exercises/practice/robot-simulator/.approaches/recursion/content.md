# Recursion

```scala
import scala.annotation.tailrec;

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

  @tailrec
  final def simulate(orders: String): Robot =
    if (orders.isEmpty) this
    else
      (orders.head match {
        case 'L' => turnLeft
        case 'R' => turnRight
        case 'A' => advance
      }).simulate(orders.tail)
}
```

This approach starts by importing from packages for what is needed.

It then defines the `Bearing` [Enumeration][enumeration] in the Scala 2 way.

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
`id` and the `fromOrdinal()` method functioning like `apply()`, like so:

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

The `simulate()` method uses [recursion][recursion] to iterate the characters of the orders `String`.

```scala
@tailrec
final def simulate(orders: String): Robot =
  if (orders.isEmpty) this
  else
    (orders.head match {
      case 'L' => turnLeft
      case 'R' => turnRight
      case 'A' => advance
    }).simulate(orders.tail)
```

It is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

The first iteration uses the `Robot` instance and the entire orders `String`.
If there are any letters left in the orders `String`,
a [`match`][match] is used for [pattern matching][pattern-matching] on the first letter of the `String`.
The [`head()`][head] method is used to return the first letter.
The `match` returns a new `Robot` which is passed to the next call of `simulate()` along with
all of characters after the first character of the orders `String`.
The [`tail()`][tail] method returns all of the characters after the first character.
When `simulate()` is done iterating through all of the letters, it returns the `Robot` instance from its last iteration.

In this variation of the recursive approach, the recursive call to `simulate()` is chained to the result of the `match` expression.
Another variation could use a separate method like so:

```scala
def simulate(orders: String): Robot = simulateRecur(orders, this)

@tailrec
private def simulateRecur(orders: String, robbie: Robot): Robot =
  if (orders.isEmpty) robbie
  else
    orders.head match {
      case 'L' => simulateRecur(orders.tail, robbie.turnLeft)
      case 'R' => simulateRecur(orders.tail, robbie.turnRight)
      case 'A' => simulateRecur(orders.tail, robbie.advance)
    }
```

Either variation is idiomatic.
The chaining approach may be preferred for being less verbose.

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
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#head:A
[tail]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#tail:Repr
