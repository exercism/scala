# `foldLeft()` and `match`

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

This approach starts by defining a [`Map`][map].
The [`foldLeft()`][foldleft] method is then called on the input `String`.
It is initalized with en empty `String` intended for the [`Left`][left] error value and
the `Map` intended for the [`Right`][right] value,
both wrapped together in a [tuple][tuple].
The tuple and each character from the input `String` are passed into the [lambda][lambda].

The [match][match] is used to destructure the tuple and checks to see if the character is valid.
If the `Map` [`contains()`][contains] the character as a key, then a new tuple is created, using the
the error `String` as is and a new `Map` created by using the `+` alias for the [`updated()`][updated] method.
Recreating an immutable `Map` instead of changing a mutable `Map` is how the approach maintains [immutability][immutability].
If the `Map` does not contain the character, then a new tuple is created with a new error `String` and the `Map` as is.

After the `foldLeft()` has done, its final tuple is checked by the [`match`][match].
If the [pattern matching][pattern-matching] determines the error `String` is empty, then the `Map` is returned from `nucleotideCounts()`,
wrapped as a [`Right`][right] value.
Otherwise, the error `String` is returned from `nucleotideCounts()`, wrapped as a [`Left`][left] value.

[foldleft]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#foldLeft[B](z:B)(op:(B,Char)=%3EB):B
[left]: https://www.scala-lang.org/api/2.13.10/scala/util/Left.html
[right]: https://www.scala-lang.org/api/2.13.10/scala/util/Right.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[updated]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
