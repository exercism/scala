# `if` expressions

```scala
object Bob {

  def response(statement: String): String = {
    val input = statement.trim()
    if (input.isEmpty) return "Fine. Be that way!"
    
    val isShout =
      input.exists(_.isLetter) && input.toUpperCase() == input
    val isQuestion = input.endsWith("?")

    if (isShout)
      if (isQuestion) "Calm down, I know what I'm doing!"
      else "Whoa, chill out!"
    else if (isQuestion) "Sure."
    else "Whatever."
  }
}
```

In this approach you have a series of `if` statements to evaluate the conditions.
As soon as the right condition is found, the correct response is returned.

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

At the time of this writing, Scala 3 is not currently supported on the Scala track.
In Scala 3, the `if` expressions can be written without parentheses, using the [`if/then/else`][if-then-else] construct, like so

```scala
if isShout then
  if isQuestion then "Calm down, I know what I'm doing!"
  else "Whoa, chill out!"
else if isQuestion then "Sure."
else "Whatever."
```

[trim]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#trim():String
[endswith]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html
[exists]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#exists(p:A=%3EBoolean):Boolean
[isletter]: https://www.scala-lang.org/api/2.12.8/scala/Char.html#isLetter:Boolean
[if-then-else]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
