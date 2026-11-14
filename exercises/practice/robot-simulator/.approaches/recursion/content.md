# Recursion

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

This approach starts by importing the `tailrec` annotation.

It then defines the `Bearing` [`enum`][enum] with the four directions in clockwise order.
Turning is the job of the bearing, so the `enum` itself has the `turnRight` and `turnLeft` methods.
Each one is a [`match`][match] on `this`, the bearing it is called on, with a `case` that gives the next direction.
Inside the `enum` the cases can be named without the `Bearing.` prefix.
Since the compiler knows every case of an `enum`, it checks that the `match` is exhaustive:
if a direction were left out, compiling would give a warning instead of the program failing at runtime.

The `Robot` is a [case class][case-class] with a `bearing` and the `coordinates` the tests ask for.
A case class gets a `copy` method, which makes a new instance with the named fields changed and the others kept,
so the `Robot` [immutably][immutability] turns by copying itself with the bearing turned the same way.

The `advance` method [destructures][tuple] the `coordinates` into `x` and `y`,
and then matches on the `bearing` to copy the robot with one of them changed by `1`.

The `simulate()` method uses [recursion][recursion] to iterate the characters of the instructions `String`.

```scala
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

It is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].
It is `final`, because the compiler can only optimize a call on another `Robot` if no subclass can override the method.

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

The first call uses the `Robot` instance and the entire instructions `String`.
If there are any letters left in the instructions `String`,
a `match` is used for [pattern matching][pattern-matching] on the first letter of the `String`.
The [`head`][head] method is used to return the first letter.
The `match` gives the `next` `Robot`, on which `simulate()` is called again with
all of the characters after the first character of the instructions `String`.
The [`tail`][tail] method returns all of the characters after the first character.
When `simulate()` is done iterating through all of the letters, it returns the `Robot` instance from its last call.

In this variation of the recursive approach, `simulate()` is called on the `Robot` that the `match` gives.
Another variation could pass the robot to a private helper method like so:

```scala
def simulate(instructions: String): Robot = loop(this, instructions)

@tailrec
private def loop(robot: Robot, instructions: String): Robot =
  if instructions.isEmpty then robot
  else
    instructions.head match
      case 'L' => loop(robot.turnLeft, instructions.tail)
      case 'R' => loop(robot.turnRight, instructions.tail)
      case 'A' => loop(robot.advance, instructions.tail)
```

A `private` method can't be overridden, so `loop()` doesn't need to be `final` for `@tailrec`.
Either variation is idiomatic.
The first one may be preferred for being less verbose.

[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[case-class]: https://docs.scala-lang.org/tour/case-classes.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tail]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
