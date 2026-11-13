# Introduction

There are at least two general approaches to solving Luhn.
You can validate for non-digits and scrub out the spaces first.
Or you can ignore a space and test if each character is a digit as you go.
Each general approach can then be calculated in at least two idiomatic ways.
One way is to use the [`foldRight()`][foldright] method.
Another way is to use [recursion][recursion].

## General guidance

One important aspect to solving Luhn is to allow for spaces in the input and to disallow all other non-numeric characters.
Another important aspect is to handle the value of each digit according to its position in the string.

## Approach: Validate and scrub, then `foldRight()`

```scala
object Luhn:

  def valid(input: String): Boolean =
    if input.exists(chr => !chr.isDigit && !chr.isSpaceChar) then false
    else
      val cleanedInput = input.filter(_.isDigit)

      val (_, total) = cleanedInput.foldRight((1, 0)): (chr, tup) =>
        val num = chr.asDigit
        tup match
          case (pos, sum) if pos % 2 == 0 =>
            (pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2))
          case (pos, sum) => (pos + 1, sum + num)
      cleanedInput.length > 1 && total % 10 == 0
```

For more information, check the [Validate and scrub, then `foldright()` approach][approach-validate-scrub-foldright].

## Approach: Validate and scrub, then recurse

```scala
import scala.annotation.tailrec

object Luhn:

  def valid(input: String): Boolean =
    if input.exists(chr => !chr.isDigit && !chr.isSpaceChar) then false
    else
      val cleanedInput = input.filter(_.isDigit)
      cleanedInput.length > 1 && validRecur(1, 0, cleanedInput)

  @tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean =
    if input.isEmpty then sum % 10 == 0
    else
      val num = input.last.asDigit
      pos match
        case pos if pos % 2 == 0 =>
          validRecur(pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2), input.init)
        case _ => validRecur(pos + 1, sum + num, input.init)
```

For more information, check the [Validate and scrub, then recurse approach][approach-validate-scrub-recursion].

## Approach: `foldRight()`

```scala
object Luhn:

  def valid(input: String): Boolean =
    val (count, total) = input.foldRight((1, 0)): (chr, tup) =>
      chr match
        case chr if chr.isDigit     =>
          val num = chr.asDigit
          tup match
            case (pos, sum) if pos % 2 == 0 =>
              (pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2))
            case (pos, sum) => (pos + 1, sum + num)
        case chr if chr.isSpaceChar => tup
        case _                      => (-100, 0)
    count > 2 && total % 10 == 0
```

For more information, check the [`foldRight()` approach][approach-foldright].

## Approach: Recursion

```scala
import scala.annotation.tailrec

object Luhn:

  def valid(input: String): Boolean = validRecur(1, 0, input)

  @tailrec
  private def validRecur(pos: Int, sum: Int, input: String): Boolean =
    if input.isEmpty then pos > 2 && sum % 10 == 0
    else
      input.last match
        case chr if chr.isDigit     =>
          val num = chr.asDigit
          pos match
            case pos if pos % 2 == 0 =>
              validRecur(pos + 1, sum + (if num > 4 then num * 2 - 9 else num * 2), input.init)
            case _ => validRecur(pos + 1, sum + num, input.init)
        case chr if chr.isSpaceChar => validRecur(pos, sum, input.init)
        case _                      => false
```

For more information, check the [Recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The validate-as-you-go general approach may be more efficient if the input is usually valid.
The validate-and-scrub-first general approach may be more efficient if the input is usually invalid.

A nice feature of using recursion is that the method can stop as soon as it encounters an illegal character.

[foldright]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-validate-scrub-foldright]: https://exercism.org/tracks/scala/exercises/luhn/approaches/validate-scrub-foldright
[approach-validate-scrub-recursion]: https://exercism.org/tracks/scala/exercises/luhn/approaches/validate-scrub-recursion
[approach-foldright]: https://exercism.org/tracks/scala/exercises/luhn/approaches/foldright
[approach-recursion]: https://exercism.org/tracks/scala/exercises/luhn/approaches/recursion
