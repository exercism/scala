# `grouped()` with `map()` and `takeWhile()`

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

This approach starts by calling the [`grouped()`][grouped] method on the input `String`.
It will take a sequence of `Char`s up to the amount passed in.

```exercism/caution
The `grouped()` treats a `String` as a plain sequence of `Char` code units and makes no attempt to keep surrogate pairs or codepoint sequences together.
The user is responsible for making sure such cases are handled correctly.
Failing to do so may result in an invalid Unicode `String`.
The `grouped()` method works here because all of the characters are [ASCII](https://www.asciitable.com/).
```

If the remaining number of `Chars`s is less than the argument, `grouped()` will return those,
and will be an empty `Iterator` if no `Char`s are left.
The `Char`s are chained to the [`map()`][map] method which passes them to the `codonsToProteins()` method,
which accepts them as a `String`.

The `codonsToProteins()` method uses a [`match`][match] to look up the protein for the codon and returns the protein from the method.

The proteins are chained to the [`takeWhile()`][takewhile] method which keeps requesting codons until they run out or
a `STOP` codon is received.

Once all of the valid codons have been translated, the `proteins()` method returns a sequence of the proteins.

[grouped]: https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/StringOps.html#grouped(size:Int):Iterator[Repr]
[map]: https://www.scala-lang.org/api/2.12.3/scala/collection/Iterator.html#map[B](f:A=%3EB):Iterator[B]
[takewhile]: https://www.scala-lang.org/api/2.12.3/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
