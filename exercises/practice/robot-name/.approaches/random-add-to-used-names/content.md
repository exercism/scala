# Randomly add to used names

```scala
import scala.annotation.tailrec
import scala.util.Random

class Robot() {
  import Robot.addName
  var name: String = addName
  def reset(): Unit = name = addName
}

object Robot {
  private val randy = Random
  private var savedNames = scala.collection.mutable.Set[String]()

  private object CharType extends Enumeration {
    type CharType = Value
    val Letter, Number = Value
  }
  import CharType._

  private def getNextRandom(base: Int, high: Int): Int =
    base + randy.nextInt(high)

  private def genChar(charType: CharType): Char = {
    charType match {
      case Letter => (getNextRandom(65, 26)).toChar
      case Number => (getNextRandom(48, 10)).toChar
    }
  }

  @tailrec
  private def nameMe(chars: List[Char]): String = {
    chars.length match {
      case len if len < 2 => nameMe(genChar(Letter) :: chars)
      case len if len < 5 => nameMe(genChar(Number) :: chars)
      case _              => chars.reverse.mkString("")
    }
  }

  @tailrec
  private def addName: String = {
    val temp = nameMe(List[Char]())
    temp match {
      case name if savedNames contains name => addName
      case name =>
        savedNames += name
        name
    }
  }
}
```

There are many possible variations of randomly adding to used names.

This approach uses a [mutable `Set`][mutable-set] for storing the names.

An [`Enumeration`][enumeration] is defined for determining if a letter or digit is being generated.

The `getNextRandom()` method is passed in the lower bound for an [ASCII][ascii] value as the first argument.
The value will be `65` for `A` or `40` for `0`.
The second argument is a value for generating a random number from `0` up but not including the second argument.
The randomly generated number from the [`nextInt()`][nextint] method is added to the lower bound and returned from the method
as a character, from either `A` through `Z` or `0` through `9`.

The `genChar()` method passes the appropriate bounds to `getNextRandom()` for either a letter or a number.

The `nameMe()` method passes in the appropriate `Enumeraton` value to `genChar()` based on the length of the name being built.
The name starts as an empty `List` and is built with the [cons][cons] operator (`::`) until it reaches all two letters and three digits.
The `nameMe()` method calls itself, passing in the list of name characters until it is complete, at which point it [`reverse()`][reverse]s
the characters and uses [`mkString()`][mkstring] to return them as a `String`.

It is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

The `addName()` method is also tail recursive.
It will keep calling itself until a name is generated which hasn't been generated before, at which point the newly generated name is
added to the `Set` of used names and is returned from the method.

Other variations of this approach _could_ generate the numeric part of the name as one number instead of three separate digits.
This particular variation of generating three separate digits, even with the collision against used names, can finish all the tests,
including generating all 676,000 unique names, in about five seconds.

Interestingly, when the code is refactored to generate the name with one number instead of three digits, it takes about eight seconds
to finish all the tests, including generating all 676,000 unique names, which perhaps better demonstrates the disadvantage of collision:

```scala
private def genChar(): Char = (65 + randy.nextInt(26)).toChar

private def genNumber(): String = f"${randy.nextInt(1000)}%03d"

@tailrec
private def nameMe(chars: List[Char]): String = {
  chars.length match {
    case len if len < 2 => nameMe(genChar() :: chars)
    case _              => chars.mkString("", "", genNumber())
  }
}
```

[mutable-set]: https://www.scala-lang.org/api/2.13.6/scala/collection/mutable/Set.html
[enumeration]: https://www.scala-lang.org/api/2.13.10/scala/Enumeration.html
[ascii]: https://www.asciitable.com/
[nextint]: https://www.scala-lang.org/api/2.13.6/scala/util/Random.html#nextInt(n:Int):Int
[cons]: https://www.scala-lang.org/api/2.13.5/scala/collection/immutable/Stream$$Cons.html
[reverse]: https://www.scala-lang.org/api/2.13.5/scala/collection/immutable/List.html#reverse_:::[B%3E:A](prefix:List[B]):List[B]
[mkstring]: https://www.scala-lang.org/api/2.13.5/scala/collection/immutable/List.html#mkString:String
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
