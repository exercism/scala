# `foldLeft()` and `match`

```scala
case class DNA(strand: String):

  def nucleotideCounts: Either[String, Map[Char, Int]] =
    val output = Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0)
    strand.foldLeft(("", output)) {
      case ((errStr, output), chr) if output.contains(chr) => (errStr, output + (chr -> (output(chr) + 1)))
      case ((_, output), chr)                              => (s"invalid nucleotide '$chr'", output)
    } match
      case (errStr, output) if errStr.isEmpty => Right(output)
      case (errStr, _)                        => Left(errStr)
```

This approach starts by defining a [`Map`][map] with a count of `0` for each nucleotide.
The [`foldLeft()`][foldleft] method is then called on the input `String`.
It is initialized with an empty `String` intended for the [`Left`][left] error value and
the `Map` intended for the [`Right`][right] value,
both wrapped together in a [tuple][tuple].
The tuple and each character from the input `String` are passed into the [lambda][lambda].

The lambda is made of `case` clauses, so it uses [pattern matching][pattern-matching] to destructure the tuple
and a guard to check whether the character is valid.
If the `Map` [`contains()`][contains] the character as a key, then a new tuple is created, using
the error `String` as is and a new `Map` created by using the `+` alias for the [`updated()`][updated] method.
Recreating an immutable `Map` instead of changing a mutable `Map` is how the approach maintains [immutability][immutability].
If the `Map` does not contain the character, then a new tuple is created with a new error `String` and the `Map` as is.

After the `foldLeft()` is done, its final tuple is checked by the [`match`][match].
If the error `String` is empty, then the `Map` is returned from `nucleotideCounts`,
wrapped as a [`Right`][right] value.
Otherwise, the error `String` is returned from `nucleotideCounts`, wrapped as a [`Left`][left] value.

[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[contains]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[updated]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[left]: https://www.scala-lang.org/api/current/scala/util/Left.html
[right]: https://www.scala-lang.org/api/current/scala/util/Right.html
[map]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
