# Introduction

There are at least two idiomatic ways to solve Matching Brackets.
One approach is to use the [`foldLeft()`][foldleft] method.
Another is to use [recursion][recursion].

## Approach: `foldLeft()`

```scala
object MatchingBrackets {
  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends = Set(')', '}', ']')
  def isValid(stack: Vector[Char], char: Char): Boolean =
    stack.length > 0 && stack.last == char
  def safeInit(stack: Vector[Char]): Vector[Char] = {
    if (!stack.isEmpty) stack.init else stack
  }

  def isPaired(input: String): Boolean = {
    input
      .foldLeft((Vector[Char](), true))((tup, chr) =>
        tup match {
          case (stack, valid) if ends.contains(chr) =>
            (safeInit(stack), valid && isValid(stack, chr))
          case (stack, valid) if (brackets.contains(chr)) =>
            (stack.appended(brackets(chr)), valid)
          case _ => tup
        }
      ) match {
      case (stack, isValid) => stack.isEmpty && isValid
    }
  }
}
```

For more information, check the [`foldLeft()` approach][approach-foldleft].

## Approach: Recursion

```scala

```

For more information, check the [Recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[foldleft]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#foldLeft[B](z:B)(op:(B,A)=%3EB):B
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/matching-brackets/approaches/foldleft
[approach-recursion]: https://exercism.org/tracks/scala/exercises/matching-brackets/approaches/recursion
