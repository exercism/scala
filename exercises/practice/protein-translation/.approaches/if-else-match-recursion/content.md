# `if` and `else` with `match` and recursion

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
          codonsToProteins(input.substring(3), proteins :+ protein)
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

This approach starts by importing from packages for what is needed.

The `proteins()` method starts by having calling the `codonsToProteins()` method,
passing in the input `String` and en empty [Sequence][sequence].

The `codonsToProteins()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

If the length of the input `String` is less than `3`, the method returns the `Sequence` of proteins.
Otherwise, a [`match`][match] expression is used to perform [pattern matching][pattern-matching] on the result of passing the codon
to the `codonToProtein()` method.

The `codonToProtein()` method uses a `match` to look up the protein for the codon and returns the protein from the method.

If the protein is for a `STOP` codon, the `match` returns the `Sequence` of proteins from the method.
Otherwise, the method calls itself, passing in all but the first `3` `Char`s of the input `String`,
and passing in the existing `Sequence` of proteins with the protein added to the end of it with the [`:+`][append-operator] operator.

[sequence]: https://www.geeksforgeeks.org/scala-sequence/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[append-operator]: https://alvinalexander.com/scala/how-to-append-prepend-items-vector-seq-in-scala/
