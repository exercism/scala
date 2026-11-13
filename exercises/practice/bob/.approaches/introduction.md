# Introduction

There are various idiomatic approaches to solve Bob.
A basic approach can use a series of `if` expressions to test the conditions.
Or a [match][match] on a [tuple][tuple] of the conditions can be used.
A [`Vector`][vector] can contain answers from which the right response is selected by an index calculated from scores given to the conditions.

## General guidance

Regardless of the approach used, some things you could look out for include

- If the input is trimmed, [`trim()`][trim] only once.

- Use the [`endsWith`][endswith] `String` method instead of checking the last character by index for `?`.

- Don't copy/paste the logic for determining a shout and for determining a question into determining a shouted question.
  Combine the two determinations instead of copying them.
  Not duplicating the code will keep the code [DRY][dry].

- Perhaps consider making `isQuestion` and `isShout` values set once instead of functions that are possibly called twice.

- There is no need for `return`.
  In Scala, `if` and `match` are expressions, so the check for silence can be the first branch of the expression that gives the answer.

- `trim()`, `toUpperCase()`, `isEmpty` and `endsWith` are methods of Java's [`String`][java-string],
  while `exists` comes from Scala's [`StringOps`][stringops], which adds the collection methods to `String`.
  Scala lets you call a Java method that takes no arguments with or without parentheses.

## Approach: `if` expressions

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

For more information, check the [`if` expressions approach][approach-if].

## Approach: `match` expression

```scala
object Bob:

  def response(statement: String): String =
    val input      = statement.trim()
    val isShout    = input.exists(_.isLetter) && input.toUpperCase() == input
    val isQuestion = input.endsWith("?")

    if input.isEmpty then "Fine. Be that way!"
    else
      (isQuestion, isShout) match
        case (true, true) => "Calm down, I know what I'm doing!"
        case (_, true)    => "Whoa, chill out!"
        case (true, _)    => "Sure."
        case _            => "Whatever."
```

For more information, check the [`match` expression approach][approach-match].

## Approach: Answers Vector

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

For more information, check the [Answers Vector approach][approach-vector].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between approaches can be made by perceived readability.

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[java-string]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[endswith]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[trim]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/String.html
[dry]: https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
[approach-if]: https://exercism.org/tracks/scala/exercises/bob/approaches/if-expressions
[approach-match]: https://exercism.org/tracks/scala/exercises/bob/approaches/match-expression
[approach-vector]: https://exercism.org/tracks/scala/exercises/bob/approaches/answers-vector
