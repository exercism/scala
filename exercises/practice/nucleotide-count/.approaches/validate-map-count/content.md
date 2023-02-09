# Validate, then use `map()` with `count()`

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

This approach starts by defining a private validating method that checks for invalid characters.
It does this by calling the [`find()`][find] method on the input `String`, passing each character in the `String`
to be checked by the [`contains()`][contains] method.
The underscore (`_`) wildcard is used to pass in the character, since it doesn't need to be named.

If the `ACGT` `String` contains all of the characters, it passes [`None`][none] to the [`map()`][option-map] method.
Otherwise, the first character that is not contained in `ACGT` is passed as a [`Some`][some] value to `map()`.
If `map()` receives `None`, then it returns `None` from the validating method.
If map receives a `Some`, then `map()` converts it to a `Some` [`Left`][left] value which is returned from the method.

The `nucleotideCounts()` method calls the validating method. If there is any invalid character, then
the [`getOrElse()`][getOrElse] method returns the `Left` value from `nucleotideCounts()`.
If `getOrElse()` receives `None`, then it [map][string-map]s each character in the `ACGT` `String`, passing each into
a [lambda][lambda] which uses the [`count()`][count] method to count the occurrences of the character in the 
input `String`.
The resulting `IndexedSeq` is converted to a [`Map`][map] by the [`toMap()`][tomap] method, which is returned from
`nucleotideCounts()`, wrapped as a [`Right`][right] value.

[find]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#find(p:Char=%3EBoolean):Option[Char]
[contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#contains(elem:Char):Boolean
[underscore]: https://www.baeldung.com/scala/underscore
[some]: https://www.scala-lang.org/api/2.13.10/scala/Some.html
[none]: https://www.scala-lang.org/api/2.13.10/scala/None$.html
[option-map]: https://www.scala-lang.org/api/2.13.10/scala/Option.html#map[B](f:A=%3EB):Option[B]
[string-map]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#map[B](f:Char=%3EB):IndexedSeq[B]
[nothing]: https://www.scala-lang.org/api/2.13.10/scala/Nothing.html
[getOrElse]: https://www.scala-lang.org/api/2.13.10/scala/Option.html#getOrElse[B%3E:A](default:=%3EB):B
[count]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#count(p:Char=%3EBoolean):Int
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[tomap]: https://www.scala-lang.org/api/2.13.10/scala/collection/generic/IsIterableOnce.html
[left]: https://www.scala-lang.org/api/2.13.10/scala/util/Left.html
[right]: https://www.scala-lang.org/api/2.13.10/scala/util/Right.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
