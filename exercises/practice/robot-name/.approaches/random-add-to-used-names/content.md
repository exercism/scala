# Randomly add to used names

```scala
import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.Random

class Robot:
  var name: String = Robot.addName

  def reset(): Unit = name = Robot.addName

object Robot:
  private val randy      = Random
  private val savedNames = mutable.Set.empty[String]

  private enum CharType:
    case Letter, Number

  import CharType.*

  private def getNextRandom(base: Int, high: Int): Int = base + randy.nextInt(high)

  private def genChar(charType: CharType): Char = charType match
    case Letter => getNextRandom(65, 26).toChar
    case Number => getNextRandom(48, 10).toChar

  @tailrec
  private def nameMe(chars: List[Char]): String = chars.length match
    case len if len < 2 => nameMe(genChar(Letter) :: chars)
    case len if len < 5 => nameMe(genChar(Number) :: chars)
    case _              => chars.reverse.mkString

  @tailrec
  private def addName: String = nameMe(Nil) match
    case name if savedNames.contains(name) => addName
    case name                              =>
      savedNames += name
      name
```

There are many possible variations of randomly adding to used names.

This approach uses a [mutable `Set`][mutable-set] for storing the names.
The `Set` itself is mutable, so the `val` holding it never needs to be reassigned.

A private [`enum`][enum] is defined for determining if a letter or digit is being generated.

The `getNextRandom()` method is passed in the lower bound for an [ASCII][ascii] value as the first argument.
The value will be `65` for `A` or `48` for `0`.
The second argument is a value for generating a random number from `0` up to but not including the second argument.
The randomly generated number from the [`nextInt()`][nextint] method is added to the lower bound and returned from the method
as a character, from either `A` through `Z` or `0` through `9`.

The `genChar()` method passes the appropriate bounds to `getNextRandom()` for either a letter or a number.

The `nameMe()` method passes in the appropriate `enum` value to `genChar()` based on the length of the name being built.
The name starts as an empty `List` and is built with the cons operator (`::`) until it has two letters and three digits.
The `nameMe()` method calls itself, passing in the list of name characters until it is complete, at which point it [`reverse`][reverse]s
the characters and uses [`mkString`][mkstring] to return them as a `String`.

It is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

The `addName` method is also tail recursive.
It will keep calling itself until a name is generated which hasn't been generated before, at which point the newly generated name is
added to the `Set` of used names and is returned from the method.

Other variations of this approach _could_ generate the numeric part of the name as one number instead of three separate digits.
This particular variation of generating three separate digits, even with the collision against used names, can finish all the tests,
including generating all 676,000 unique names, in a few seconds.

Interestingly, when the code is refactored to generate the name with one number instead of three digits, it takes longer
to finish all the tests, including generating all 676,000 unique names, which perhaps better demonstrates the disadvantage of collision:

```scala
private def genChar(): Char = (65 + randy.nextInt(26)).toChar

private def genNumber(): String = f"${randy.nextInt(1000)}%03d"

@tailrec
private def nameMe(chars: List[Char]): String = chars.length match
  case len if len < 2 => nameMe(genChar() :: chars)
  case _              => chars.mkString("", "", genNumber())
```

[mutable-set]: https://www.scala-lang.org/api/current/scala/collection/mutable/Set.html
[enum]: https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
[ascii]: https://www.asciitable.com/
[nextint]: https://www.scala-lang.org/api/current/scala/util/Random.html
[reverse]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
