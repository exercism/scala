# Validate, then use `map()` with `count()`

```scala
case class DNA(strand: String):

  private def findInvalid(strand: String): Option[Either[String, Nothing]] =
    strand
      .find(!"ACTG".contains(_))
      .map(chr => Left(s"invalid nucleotide '$chr'"))

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    findInvalid(strand)
      .getOrElse(Right("ACTG".map(chr => chr -> strand.count(_ == chr)).toMap))
```

This approach starts by defining a private validating method that checks for invalid characters.
It does this by calling the [`find()`][find] method on the input `String`, passing each character in the `String`
to be checked by the [`contains()`][contains] method.
The [underscore][underscore] (`_`) placeholder is used to pass in the character, since it doesn't need to be named.

If the `ACTG` `String` contains all of the characters, `find()` gives [`None`][none] to the [`map()`][option-map] method.
Otherwise, the first character that is not contained in `ACTG` is given as a [`Some`][some] value to `map()`.
If `map()` receives `None`, then it returns `None` from the validating method.
If `map()` receives a `Some`, then it converts it to a `Some` of a [`Left`][left] value, which is returned from the method.
The `Left` holds no `Right` type, so it is an `Either[String, Nothing]`, and [`Nothing`][nothing] fits any `Right` type.

The `nucleotideCounts` method calls the validating method. If there is any invalid character, then
the [`getOrElse()`][getorelse] method returns the `Left` value from `nucleotideCounts`.
If `getOrElse()` receives `None`, then it [map][string-map]s each character in the `ACTG` `String`, passing each into
a [lambda][lambda] which uses the [`count()`][count] method to count the occurrences of the character in the
input `String`.
The resulting `IndexedSeq` of pairs is converted to a [`Map`][map] by the [`toMap`][tomap] method, which is returned from
`nucleotideCounts`, wrapped as a [`Right`][right] value.

[find]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[contains]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[underscore]: https://www.baeldung.com/scala/underscore
[some]: https://www.scala-lang.org/api/current/scala/Some.html
[none]: https://www.scala-lang.org/api/current/scala/None$.html
[option-map]: https://www.scala-lang.org/api/current/scala/Option.html
[string-map]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[nothing]: https://www.scala-lang.org/api/current/scala/Nothing.html
[getorelse]: https://www.scala-lang.org/api/current/scala/Option.html
[count]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[tomap]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[left]: https://www.scala-lang.org/api/current/scala/util/Left.html
[right]: https://www.scala-lang.org/api/current/scala/util/Right.html
[map]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
