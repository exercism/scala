# Introduction

There are at least three idiomatic ways to solve Roman Numerals.
One approach is to use [`iterate()`][iterate] with [`takeWhile()`][takewhile] and [`last`][last].
Another way is to use [recursion][recursion] with a couple of [`Vector`][vector]s.
Recursion could also be used with a [`Map`][map].

## General guidance

One concern is to try to maintain [immutability][immutability].

## Approach: `iterate()` with `takeWhile()` and `last`

```scala
object RomanNumerals {

  val ArabicNum = Vector(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
  val RomanNum = Vector(
    "M",
    "CM",
    "D",
    "CD",
    "C",
    "XC",
    "L",
    "XL",
    "X",
    "IX",
    "V",
    "IV",
    "I"
  )

  def roman(num: Int): String = {
    Iterator
      .iterate((num, 0, List[String]()))(tup =>
        tup match {
          case (num, idx, digits) if num >= ArabicNum(idx) =>
            (num - ArabicNum(idx), idx, RomanNum(idx) :: digits)
          case (num, idx, digits) =>
            (num, idx + 1, digits)
        }
      )
      .takeWhile(tup => tup._2 < 13)
      .to(Seq)
      .last match {
      case (_, _, digits) => digits.reverse.mkString
    }
  }
}
```

For more information, check the [`iterate()` with `takeWhile()` and  `last` approach][approach-iterate-takewhie-last].

## Approach: Recursion with `Vector`s

```scala
object RomanNumerals {

  val ArabicNum = Vector(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
  val RomanNum = Vector(
    "M",
    "CM",
    "D",
    "CD",
    "C",
    "XC",
    "L",
    "XL",
    "X",
    "IX",
    "V",
    "IV",
    "I"
  )

  def roman(num: Int): String = {
    romanRecur(num, 0, List[String]())
  }
  @scala.annotation.tailrec
  private def romanRecur(num: Int, idx: Int, digits: List[String]): String = {
    (num, idx, digits) match {
      case (_, 13, digits) => digits.reverse.mkString
      case (num, idx, digits) if num >= ArabicNum(idx) =>
        romanRecur(num - ArabicNum(idx), idx, RomanNum(idx) :: digits)
      case (num, idx, digits) =>
        romanRecur(num, idx + 1, digits)
    }
  }
}
```

For more information, check the [Recursion with `Vector`s approach][approach-vectors-recursion].

## Approach: Recursion with a `Map`

```scala
object RomanNumerals {
  val ArabicToRoman = Map(
    1000 -> "M",
    900 -> "CM",
    500 -> "D",
    400 -> "CD",
    100 -> "C",
    90 -> "XC",
    50 -> "L",
    40 -> "XL",
    10 -> "X",
    9 -> "IX",
    5 -> "V",
    4 -> "IV",
    1 -> "I"
  )

  def roman(num: Int): String = romanRecur(num, List[String]())

  @scala.annotation.tailrec
  def romanRecur(num: Int, digits: List[String]): String =
    (num, digits) match {
      case (0, digits) => digits.reverse.mkString
      case (num, digits) => {
        val digit = ArabicToRoman.keys.filter(_ <= num).max
        romanRecur(num - digit, ArabicToRoman(digit) :: digits)
      }
    }
}
```

For more information, check the [Recursion with a `Map` approach][approach-map-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[iterate]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator$.html#iterate[T](start:T)(f:T=%3ET):Iterator[T]
[takeWhile]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[last]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#last:A
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[vector]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Vector.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-iterate-takewhie-last]: https://exercism.org/tracks/scala/exercises/roman-numerals/approaches/iterate-takewhile-last
[approach-vectors-recursion]: https://exercism.org/tracks/scala/exercises/roman-numerals/approaches/vectors-recursion
[approach-map-recursion]: https://exercism.org/tracks/scala/exercises/roman-numerals/approaches/map-recursion
