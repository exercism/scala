# Introduction

There are at least two idiomatic ways to solve Robot Name.
One approach is to randomly generate each new name and check it against a collection of used names.
If the name has already been used, then keep randomly generating a new name until it does not match a used name.
If the new name has not been used, then add it to the collection of used names.

Another approach is to generate all possible names, randomly shuffle them, and then take from them in sequence.
That approach avoids the collision of new names against used names.
The chance of collision increases as more used names are added.

## General guidance

Regardless of the approach used, one thing to consider is to generate the whole number at once instead of three separate digits.

## Approach: Randomly add to used names

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

For more information, check the [randomly add to used names approach][approach-random-add-to-used-names].

## Approach: Sequentially take from shuffled names

```scala
import scala.util.Random

class Robot {

  var name = genName()

  def reset() = name = genName()

  private def genName(): String = Robot.names.next()
}

object Robot {
  val names: Iterator[String] = {
    val seqNames =
      for {
        ltr1 <- ('A' to 'Z')
        ltr2 <- ('A' to 'Z')
        num <- (0 to 999)
      } yield {
        f"$ltr1%c$ltr2%c$num%03d"
      }
    Random.shuffle(seqNames).to(LazyList).iterator
  }
}
```

For more information, check the [sequentially take from shuffled names approach][approach-sequential-take-from-shuffled-names].

## Which approach to use?

Benchmarking is currently outside the scope of this document,
but taking from shuffled names will avoid the collision that happens with checking a new randomly generated name against used names.
One advantage to checking randomly generated names against used names would be if only a few names were ever expected to be generated.
In that case, randomly generating names would use much less processing and memory than creating a collection of all possible names.

The test for "a large number of new instances have unique names" is ignored by default.
If `ignored` is changed to `it`, then the code needs to be efficient enough to allow all 676,000 unique names to be generated
before the tests time out.

[approach-random-add-to-used-names]: https://exercism.org/tracks/scala/exercises/robot-name/approaches/random-add-to-used-names
[approach-sequential-take-from-shuffled-names]: https://exercism.org/tracks/scala/exercises/robot-name/approaches/sequential-take-from-shuffled-names
