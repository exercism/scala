# Introduction

There are at least a couple of idiomatic ways to solve Protein Translation.
One approach is to use [`grouped()`][grouped] with [`map()`][map] and [`takeWhile()`][takewhile].
Another approach is to use `if` and `else` with [`match`][match] and [recursion][recursion].

## Approach: `grouped()` with `map()` and `takeWhile()`

```scala
object ProteinTranslation {

  def proteins(input: String): Seq[String] = {
    input.grouped(3).map(codonsToProteins).takeWhile(_ != "STOP").toSeq
  }

  private def codonsToProteins(codon: String): String = {

    codon match {
      case "AUG"                         => "Methionine"
      case "UUU" | "UUC"                 => "Phenylalanine"
      case "UUA" | "UUG"                 => "Leucine"
      case "UCU" | "UCC" | "UCA" | "UCG" => "Serine"
      case "UAU" | "UAC"                 => "Tyrosine"
      case "UGU" | "UGC"                 => "Cysteine"
      case "UGG"                         => "Tryptophan"
      case "UAA" | "UAG" | "UGA"         => "STOP"
    }
  }
}
```

For more information, check the
[`grouped()` with `map()` and `takeWhile()` approach][approach-grouped-map-takewhile].

## Approach: `if` and `else` with `match` and recursion

```scala
import scala.annotation.tailrec

object ProteinTranslation {

  def proteins(input: String): Seq[String] = {
    codonsToProteins(input, Seq())
  }

  @tailrec
  private def codonsToProteins(
      input: String,
      proteins: Seq[String]
  ): Seq[String] = {
    if (input.length < 3)
      proteins
    else
      codonToProtein(input.take(3)) match {
        case "STOP" => proteins
        case protein =>
          codonsToProteins(input.drop(3), proteins :+ protein)
      }
  }

  private def codonToProtein(codon: String): String = {
    codon match {
      case "AUG"                         => "Methionine"
      case "UUU" | "UUC"                 => "Phenylalanine"
      case "UUA" | "UUG"                 => "Leucine"
      case "UCU" | "UCC" | "UCA" | "UCG" => "Serine"
      case "UAU" | "UAC"                 => "Tyrosine"
      case "UGU" | "UGC"                 => "Cysteine"
      case "UGG"                         => "Tryptophan"
      case "UAA" | "UAG" | "UGA"         => "STOP"
    }
  }
}
```

For more information, check the [`if` and `else` with `match` with recursion approach][approach-if-else-match-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[grouped]: https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/StringOps.html#grouped(size:Int):Iterator[Repr]
[map]: https://www.scala-lang.org/api/2.12.3/scala/collection/Iterator.html#map[B](f:A=%3EB):Iterator[B]
[takewhile]: https://www.scala-lang.org/api/2.12.3/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-grouped-map-takewhile]: https://exercism.org/tracks/scala/exercises/protein-translation/approaches/filter-map-stream-takewhile
[approach-if-else-match-recursion]: https://exercism.org/tracks/scala/exercises/protein-translation/approaches/if-else-match-recursion
