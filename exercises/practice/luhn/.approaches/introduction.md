# Introduction

There are at least two general approaches to solving Luhn.
You can validate for non-digits and scrub out the spaces first.
Or you can ignore a space and test if each character is a digit as you go.
Each general approach can then be calculated in at least two idiomatic ways.
One way is to use the [`foldRight()`][foldRight] method.
Another way is to use [recursion][recursion].

## General guidance

One important aspect to solving Luhn is to allow for spaces in the input and to disallow all other non-numeric characters.
Another important aspect is to handle the value of each digit according to its position in the string.

## Approach: Validate and scrub, then `foldRight()`

```scala
object Luhn {

  def valid(input: String): Boolean = {
    if (input.find(chr => !chr.isDigit && !chr.isSpaceChar) != None)
      return false
    val cleanedInput = input.filter(Character.isDigit)
    if (cleanedInput.length() < 2) return false

    cleanedInput
      .foldRight((1, 0)) { (chr, tup) =>
        val num = chr.asDigit
        tup match {
          case (pos, sum) if pos % 2 == 0 =>
            (pos + 1, sum + (if (num > 4) (num * 2) - 9 else num * 2))
          case (pos, sum) => (pos + 1, sum + num)
        }
      } match {
      case (_, sum) => sum % 10 == 0
    }
  }
}
```

For more information, check the [Validate and scrub, then `foldright()` approach][approach-validate-scrub-foldright].

## Approach: Validate and scrub, then recurse

```scala
object Luhn {

  def valid(input: String): Boolean = {
    if (input.find(chr => !chr.isDigit && !chr.isSpaceChar) != None)
      return false
    val cleanedInput = input.filter(Character.isDigit)
    if (cleanedInput.length() < 2) return false

    validRecur(1, 0, cleanedInput)
  }

  @scala.annotation.tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean = {
    if (input.isEmpty) return sum % 10 == 0
    val num = input.last.asDigit
    pos match {
      case pos if pos % 2 == 0 =>
        validRecur(
          pos + 1,
          sum + (if (num > 4) (num * 2) - 9 else num * 2),
          input.init
        )
      case _ => validRecur(pos + 1, sum + num, input.init)
    }
  }
}
```

For more information, check the [Validate and scrub, then recurse approach][approach-validate-scrub-recursion].

## Approach: `foldRight()`

```scala
object Luhn {

  def valid(input: String): Boolean = {
    input
      .foldRight((1, 0)) { (chr, tup) =>
        chr match {
          case chr if chr.isDigit => {
            val num = chr.asDigit
            tup match {
              case (pos, sum) if pos % 2 == 0 =>
                (pos + 1, sum + (if (num > 4) (num * 2) - 9 else num * 2))
              case (pos, sum) => (pos + 1, sum + num)
            }
          }
          case chr if chr.isSpaceChar => tup
          case _                      => (-100, 0)
        }
      } match {
      case (pos, sum) => pos > 2 && sum % 10 == 0
    }
  }
}
```

For more information, check the [`foldRight()` approach][approach-foldright].

## Approach: Recursion

``` scala
object Luhn {

  def valid(input: String): Boolean = {
    validRecur(1, 0, input)
  }

  @scala.annotation.tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean = {
    if (input.isEmpty) return pos > 2 && sum % 10 == 0
    input.last match {
      case chr if chr.isDigit => {
        val num = chr.asDigit
        pos match {
          case pos if pos % 2 == 0 =>
            validRecur(
              pos + 1,
              sum + (if (num > 4) (num * 2) - 9 else num * 2),
              input.init
            )
          case _ => validRecur(pos + 1, sum + num, input.init)
        }
      }
      case chr if chr.isSpaceChar => validRecur(pos, sum, input.init)
      case _                      => false
    }
  }
}
```

For more information, check the [Recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The validate-as-you-go general approach may be more efficient if the input is usually valid.
The validate-and-scrub-first general approach may be more efficient if the input is usually invalid.

A nice feature of using recurson is that the method can immediately return `false` upon encountering an illegal character.

[foldright]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldRight[B](z:B)(op:(A,B)=%3EB):B
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-validate-scrub-foldright]: https://exercism.org/tracks/scala/exercises/luhn/approaches/validate-scrub-foldright
[approach-validate-scrub-recursion]: https://exercism.org/tracks/scala/exercises/luhn/approaches/validate-scrub-recursion
[approach-foldright]: https://exercism.org/tracks/scala/exercises/luhn/approaches/foldright
[approach-recursion]: https://exercism.org/tracks/scala/exercises/luhn/approaches/recursion
