# `split()`

```scala
object Acronym {
  def abbreviate(phrase: String): String = raw"[\s-]+".r
    .split(phrase)
    .map(_.head.toUpper)
    .mkString
}
```

This approach starts by defining a [`Regex`][regex] pattern using a [raw][raw] interpolator.
The raw interpolator allows using the backslash escape character in a `Regex` patttern without having to escape the backslash
with another backslash.
The pattern looks for one or more whitespace or dash characters.
A variant (`"[ -]+"`) could look for just spaces or a dash.

The `Regex` calls its [`split()`][split] method, which splits the input phrase around all matches of the `Regex`.
The resulting `Array` of words is chained to the [`map()`][map] method, which calls [`toUpper()`][toupper] on the [head][head] of each word
and passes the uppercased first character of each word to the [`mkString()`][mkstring] method, which returns
all of those characters from the `abbreviate()` method as a `String`.



[regex]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html
[raw]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[split]: https://www.scala-lang.org/api/2.12.7/scala/util/matching/Regex.html#split(toSplit:CharSequence):Array[String]
[map]: https://www.scala-lang.org/api/2.12.7/scala/collection/mutable/ArrayOps.html#map[B](f:A=%3EB):Array[B]
[toupper]: https://www.scala-lang.org/api/2.12.7/scala/Char.html#toUpper:Char
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html
[mkstring]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/Iterable.html#mkString:String
