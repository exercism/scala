# Introduction

There are various idiomatic approaches to solve Bob.
A basic approach can use a series of `if` expressions to test the conditions.
Or a [match][match] on a [tuple][tuple] of the conditions can be used.
A [`Vector`][vector] can contain answers from which the right response is selected by an index calculated from scores given to the conditions.

## General guidance

Regardless of the approach used, some things you could look out for include

- If the input is trimmed, [`trim()`][trim] only once.

- Use the [endsWith][endswith] `String` method instead of checking the last character by index for `?`.

- Don't copy/paste the logic for determining a shout and for determing a question into determing a shouted question.
  Combine the two determinations instead of copying them.
  Not duplicating the code will keep the code [DRY][dry].

- Perhaps consider making `IsQuestion` and `IsShout` values set once instead of functions that are possibly called twice.

- By convention, functions with no arguments that have side effects, such as `trim()`, are called with parentheses;
those without side effects are called without parentheses.
When looking at how some `String` methods are defined in [`StringOps`][stringops], we see that `isEmpty` is not defined with empty parens,
but `trim()` and `toUpperCase()` are defined with empty parens.

## Approach: `if` expressions

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

For more information, check the [`if` expressions approach][approach-if].

## Approach: `match` expression

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

For more information, check the [`match` expression approach][approach-match].

## Approach: Answers Vector

```scala
object Bob {

  val ANSWERS = Vector(
    "Whatever.",
    "Sure.",
    "Whoa, chill out!",
    "Calm down, I know what I'm doing!"
  )

  def response(statement: String): String = {
    val input = statement.trim()
    if (input.isEmpty) return "Fine. Be that way!"
    
    val isShout =
      if (input.exists(_.isLetter) && input.toUpperCase() == input) 2 else 0
    val isQuestion = if (input.endsWith("?")) 1 else 0

    ANSWERS.apply(isQuestion + isShout)
  }
}
```

For more information, check the [Answers Vector approach][approach-vector].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between approaches can be made by perceived readability.

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[underscore]: https://www.baeldung.com/scala/underscore
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[stringops]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html
[vector]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/Vector.html
[endswith]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html
[trim]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#trim():String
[dry]: https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
[approach-if]: https://exercism.org/tracks/scala/exercises/bob/approaches/if-expressions
[approach-match]: https://exercism.org/tracks/scala/exercises/bob/approaches/match-expression
[approach-vector]: https://exercism.org/tracks/scala/exercises/bob/approaches/answers-vector
