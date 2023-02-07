# `match` expression

```scala
object Bob {

  def response(statement: String): String = {
    val input = statement.trim()
    if (input.isEmpty) return "Fine. Be that way!"
    
    val isShout =
      input.exists(_.isLetter) && input.toUpperCase() == input
    val isQuestion = input.endsWith("?")

    (isQuestion, isShout) match {
      case (true, true) => "Calm down, I know what I'm doing!"
      case (_, true)    => "Whoa, chill out!"
      case (true, _)    => "Sure."
      case _            => "Whatever."
    }
  }
}
```

In this approach you use a [`match`][match] to test if there is a question or a shout.
The `match` returns the right response for a question, shout, shouted question, or for not being a shout or question.
Being a question and/or a shout is [pattern-matched][pattern-matching] on a [`tuple`][tuple] made of
the `isQuestion` and `isShout` values.

The `String` [`trim()`][trim] method is applied to the input to eliminate any whitespace at either end of the input.
If the string has no characters left, it returns the response for saying nothing.

```exercism/caution
Note that a `null` `String` would be different from a `String` of all whitespace.
A `null` `String` would throw a `NullPointerException` if `trim()` were applied to it.
```

The first half of setting `isShout` uses the [`exists`][exists] and [`isLetter`][isletter] methods to look for
at least one English alphabetic character.

This is because the second half of the condition tests that the uppercased input is the same as the input.
If the input were only `"123"` it would equal itself uppercased, but without letters it would not be a shout.

A question is determined by use of the [`endsWith`][endswith] method to see if the input ends with a question mark.

If the input statement is neither a question nor a shout, the [underscore][underscore] wildcard is used to match
to anything else.
It is like `default` in a `switch` statement in other languages.

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[underscore]: https://www.baeldung.com/scala/underscore
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[trim]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#trim():String
[endswith]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html
[exists]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#exists(p:A=%3EBoolean):Boolean
[isletter]: https://www.scala-lang.org/api/2.12.8/scala/Char.html#isLetter:Boolean
