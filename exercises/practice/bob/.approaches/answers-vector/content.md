# Answers Vector

```scala
object Bob:

  private val Answers = Vector(
    "Whatever.",
    "Sure.",
    "Whoa, chill out!",
    "Calm down, I know what I'm doing!",
  )

  def response(statement: String): String =
    val input      = statement.trim()
    val isShout    = if input.exists(_.isLetter) && input.toUpperCase() == input then 2 else 0
    val isQuestion = if input.endsWith("?") then 1 else 0

    if input.isEmpty then "Fine. Be that way!"
    else Answers(isQuestion + isShout)
```

In this approach you define a [`Vector`][vector] that contains Bob’s answers, and each condition is given a score.
The correct answer is selected from the `Vector` by using the score as the index.

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

The conditions of being a question and being a shout are assigned scores through [`if` expressions][if-then-else],
which Scala uses where other languages have a ternary operator.
For example, giving a question a score of `1` would use an index of `1` to get the element from the answers `Vector`, which is `"Sure."`.
`Answers(index)` is short for `Answers.apply(index)`.

| isShout | isQuestion | Index     | Answer                                |
| ------- | ---------- | --------- | ------------------------------------- |
| `false` | `false`    | 0 + 0 = 0 | `"Whatever."`                         |
| `false` | `true`     | 0 + 1 = 1 | `"Sure."`                             |
| `true`  | `false`    | 2 + 0 = 2 | `"Whoa, chill out!"`                  |
| `true`  | `true`     | 2 + 1 = 3 | `"Calm down, I know what I'm doing!"` |

[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[endswith]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[trim]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[exists]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[isletter]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[if-then-else]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
