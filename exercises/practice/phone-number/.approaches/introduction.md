# Introduction

There are several ways to solve Phone Number.
An approach using a [regular expression pattern]([regex-patterns]) (also known as `regex`) can be succinct, readable, and likely
what would be used in production.
Since regex pattern matching can arguably be considered a `String` method, a couple other approaches
which may be considered more purely "functional" would be to use the [`foldLeft()`][foldleft] method
or [recursion][recursion].

## General guidance

One concern is to try to maintain [immutability][immutability].

## Approach: `Regex` with `filter()`

```scala
object PhoneNumber {
  private val VALID_PHONE_NUMBER = raw"^1?([2-9]\d{2}[2-9]\d{6})$$".r

  def clean(input: String): Option[String] = {
    val cleaned = input.filter(_.isDigit)
    Option(cleaned) collect { case VALID_PHONE_NUMBER(cleaned) => cleaned }
  }
}
```

For more information, check the [`Regex` with `filter()` approach][approach-regex-with-filter].

## Approach: Recursion

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    cleanRecur(input.to(List), Nil)
  }

  @scala.annotation.tailrec
  private def cleanRecur(
      raw: List[Char],
      cleaned: List[Char]
  ): Option[String] = {
    (raw, cleaned.length) match {
      case (Nil, index) => {
        if (index == 10) return Some(cleaned.reverse.mkString) else None
      }
      case (head :: tail, index) if List(0, 3).contains(index) => {
        head.isDigit match {
          case true if (head > '1') => cleanRecur(tail, head :: cleaned)
          case true if (index == 0 && head == '1') => cleanRecur(tail, cleaned)
          case false if (index == 0 && List('+', '(', ' ').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (index == 3 && List(')', ' ', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case _ => None
        }
      }
      case (head :: tail, index) => {
        head.isDigit match {
          case true => cleanRecur(tail, head :: cleaned)
          case false if (index == 6 && List(' ', '-', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (head == ' ') => cleanRecur(tail, cleaned)
          case _                      => None
        }
      }
    }
  }
}
```

For more information, check the [Recursion approach][approach-recursion].

## Approach: `foldLeft()`

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    input
      .foldLeft((List[Char](), 0))((tup, head) =>
        tup._2 match {
          case index if (List(0, 3).contains(index)) => {
            head.isDigit match {
              case true if (head > '1') => (head :: tup._1, index + 1)
              case true if (index == 0 && head == '1') =>
                tup
              case false
                  if (index == 0 && List('+', '(', ' ').contains(head)) =>
                tup
              case false
                  if (index == 3 && List(')', ' ', '.').contains(head)) =>
                tup
              case _ => (Nil, -100)
            }
          }
          case index => {
            head.isDigit match {
              case true => (head :: tup._1, index + 1)
              case false
                  if (index == 6 && List(' ', '-', '.').contains(head)) =>
                tup
              case false if (head == ' ') => tup
              case _                      => (Nil, -100)
            }
          }
        }
      ) match {
      case (output, index) =>
        if (index == 10) Some(output.reverse.mkString) else None
    }
  }
}
```

For more information, check the [`foldLeft()` approach][approach-foldleft].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.
The regex pattern is not very complex and has much less volume of code, so it may be considered the most readable.
Although, as of this writing, the regex approach passes all of the current tests, there are other bad inputs which
it would not catch that the other approaches would.


[regex-patterns]: https://docs.scala-lang.org/tour/regular-expression-patterns.html
[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-regex-with-filter]: https://exercism.org/tracks/scala/exercises/phone-number/approaches/regex-with-filter
[approach-recursion]: https://exercism.org/tracks/scala/exercises/phone-number/approaches/recursion
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/phone-number/approaches/foldleft
