# `foldLeft()`

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

This approach starts by defining the `Bearing` [`enum`][enum] with the four directions in clockwise order.
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

The `simulate()` method uses the [`foldLeft()`][foldleft] method to iterate the characters of the instructions `String`.

```scala
def simulate(instructions: String): Robot = instructions.foldLeft(this): (robot, instruction) =>
  instruction match
    case 'L' => robot.turnLeft
    case 'R' => robot.turnRight
    case 'A' => robot.advance
```

The initial value for `foldLeft()` is the `Robot` instance.
The first iteration passes the `Robot` instance and the first character of the instructions `String` into a [lambda][lambda],
which follows a [colon instead of being wrapped in parentheses or braces][fewer-braces].
The lambda uses a `match` for [pattern matching][pattern-matching] on the instruction letter.
The `match` returns a new `Robot`, which is passed to the next iteration of `foldLeft()` along with the next
character of the instructions `String`.
When `foldLeft()` is done iterating through all of the letters, it returns the `Robot` instance from its last iteration.

[enum]: https://docs.scala-lang.org/scala3/reference/enums/enums.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[case-class]: https://docs.scala-lang.org/tour/case-classes.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
