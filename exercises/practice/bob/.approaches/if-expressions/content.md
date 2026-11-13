# `if` expressions

```scala
object Bob:

  def response(statement: String): String =
    val input      = statement.trim()
    val isShout    = input.exists(_.isLetter) && input.toUpperCase() == input
    val isQuestion = input.endsWith("?")

    if input.isEmpty then "Fine. Be that way!"
    else if isShout then
      if isQuestion then "Calm down, I know what I'm doing!"
      else "Whoa, chill out!"
    else if isQuestion then "Sure."
    else "Whatever."
```

In this approach you have a series of `if` expressions to evaluate the conditions.
The first condition that holds gives the response.

The `String` [`trim()`][trim] method is applied to the input to eliminate any whitespace at either end of the input.
If the string has no characters left, the first branch gives the response for saying nothing.
Because `if` is an expression, there is no need to `return` early.

~~~~exercism/caution
Note that a `null` `String` would be different from a `String` of all whitespace.
A `null` `String` would throw a `NullPointerException` if `trim()` were applied to it.
~~~~

The first half of setting `isShout` uses the [`exists`][exists] and [`isLetter`][isletter] methods to look for
at least one letter.

This is because the second half of the condition tests that the uppercased input is the same as the input.
If the input were only `"123"` it would equal itself uppercased, but without letters it would not be a shout.

A question is determined by use of the [`endsWith`][endswith] method to see if the input ends with a question mark.

The `if` expressions use Scala 3's [`if`/`then`/`else`][if-then-else] syntax, so the conditions need no parentheses.

[trim]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[endswith]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[exists]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[isletter]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[if-then-else]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
