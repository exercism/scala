# `findAllIn()`

```scala
object Acronym {
  def abbreviate(phrase: String): String = {
    raw"[\p{L}']+".r
      .findAllIn(phrase)
      .map(_.head.toUpper)
      .mkString
  }
}
```

This approach starts by defining a [`Regex`][regex] pattern using a [raw][raw] interpolator.
The raw interpolator allows using the backslash escape character in a `Regex` patttern without having to escape the backslash
with another backslash.
The pattern looks for one or more Unicode letter or dash characters.

The `Regex` calls its [`findAllIn()`][findallin] method, which finds all non-overlapping matches of the `Regex` pattern
in the input phrase.
The resulting [`MatchIterator`][matchiterator] of words is chained to the [`map()`][map] method, which calls [`toUpper()`][toupper] on the [head][head] of each word
and passes the uppercased first character of each word to the [`mkString()`][mkstring] method, which returns
all of those characters from the `abbreviate()` method as a `String`.

[regex]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html
[raw]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[findallin]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html#findAllIn(source:CharSequence):scala.util.matching.Regex.MatchIterator
[matchiterator]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex$$MatchIterator.html
[map]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex$$MatchIterator.html#map[B](f:A=%3EB):Iterator[B]
[toupper]: https://www.scala-lang.org/api/2.12.7/scala/Char.html#toUpper:Char
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html
[mkstring]: https://www.scala-lang.org/api/2.12.7/scala/collection/Iterator.html#mkString:String
