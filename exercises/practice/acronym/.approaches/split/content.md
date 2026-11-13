# `split()`

```scala
object Acronym:
  def abbreviate(phrase: String): String =
    raw"[\s_-]+".r
      .split(phrase)
      .map(_.head.toUpper)
      .mkString
```

This approach starts by defining a [`Regex`][regex] pattern using a [raw][raw] interpolator.
The raw interpolator allows using the backslash escape character in a `Regex` pattern without having to escape the backslash
with another backslash.
The pattern looks for one or more whitespace, underscore or dash characters.
The underscore is there for words with emphasis, such as `_Not_`.

The `Regex` calls its [`split()`][split] method, which splits the input phrase around all matches of the `Regex`.
The resulting `Array` of words is chained to the [`map()`][map] method, which calls [`toUpper`][toupper] on the [`head`][head] of each word
and passes the uppercased first character of each word to the [`mkString`][mkstring] method, which returns
all of those characters from the `abbreviate()` method as a `String`.

[regex]: https://www.scala-lang.org/api/current/scala/util/matching/Regex.html
[raw]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[toupper]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[head]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[split]: https://www.scala-lang.org/api/current/scala/util/matching/Regex.html
[map]: https://www.scala-lang.org/api/current/scala/collection/ArrayOps.html
