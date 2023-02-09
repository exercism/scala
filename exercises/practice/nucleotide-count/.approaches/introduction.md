# Introduction

There are at least three idiomatic approaches to solving Nucleotide Count.
You can check for invalid nucleotides first.
Or you can validate inside [`foldLeft()`][foldLeft].
Another way is to validate during [recursion][recursion].

## General guidance

The main concern is to return a [`Left`][left] value for an invalid nucleotide or a [`Right`][right]value
for a [Map][map] of the valid nucleotides.
Another concern is to try to maintain [immutability][immutability].

## Approach: Validate, then use `map()` with `count()`

```scala
case class DNA(strand: String) {

  private def findInvalid(strand: String): Option[Either[String, Nothing]] =
    strand
      .find(!"ACTG".contains(_))
      .map(chr => Left(f"invalid nucleotide '$chr'"))

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    findInvalid(strand)
      .getOrElse(Right("ACTG".map(chr => (chr, strand.count(_ == chr))).toMap))
}
```

For more information, check the [Validate, then use `map()` with `count()` approach][approach-validate-map-count].

## Approach: `foldLeft()` and `match`

```scala
case class DNA(strand: String) {

  def nucleotideCounts: Either[String, Map[Char, Int]] = {
    val output = Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0)
    strand.foldLeft(("", output)) { (tup, chr) =>
      tup match {
        case (errStr, output) if output.contains(chr) =>
          (errStr, output + (chr -> (output(chr) + 1)))
        case (errStr, output) => (s"invalid nucleotide '$chr'", output)
      }
    } match {
      case (errStr, output) if errStr.isEmpty() => Right(output)
      case (errStr, _)                          => Left(errStr)
    }
  }
}
```

For more information, check the [`foldLeft()` and `match` approach][approach-foldleft-match].

## Approach: Recursion

```scala
case class DNA(strand: String) {

  def nucleotideCounts: Either[String, Map[Char, Int]] = {
    countRecur(strand, Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0))
  }
  
  @scala.annotation.tailrec
  private def countRecur(
      strand: String,
      output: Map[Char, Int]
  ): Either[String, Map[Char, Int]] = {
    if (strand.isEmpty) return Right(output)
    strand.head match {
      case chr if output.contains(chr) =>
        countRecur(strand.tail, output + (chr -> (output(chr) + 1)))
      case chr => Left(s"invalid nucleotide '$chr'")
    }
  }
}
```

For more information, check the [Recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[left]: https://www.scala-lang.org/api/2.13.10/scala/util/Left.html
[right]: https://www.scala-lang.org/api/2.13.10/scala/util/Right.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-validate-map-count]: https://exercism.org/tracks/scala/exercises/nucleotide-count/approaches/validate-map-count
[approach-foldleft-match]: https://exercism.org/tracks/scala/exercises/nucleotide-count/approaches/foldleft-match
[approach-recursion]: https://exercism.org/tracks/scala/exercises/nucleotide-count/approaches/recursion
