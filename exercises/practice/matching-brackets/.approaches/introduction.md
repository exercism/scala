# Introduction

There are at least two idiomatic ways to solve Matching Brackets.
One approach is to use the [`foldLeft()`][foldleft] method.
Another is to use [recursion][recursion].

## General guidance

One concern is to try to maintain [immutability][immutability].

## Approach: `foldLeft()`

```scala
object MatchingBrackets:

  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends     = Set(')', '}', ']')

  private def isValid(stack: List[Char], char: Char): Boolean = stack.nonEmpty && stack.head == char

  private def safeTail(stack: List[Char]): List[Char] = if stack.nonEmpty then stack.tail else stack

  def isPaired(input: String): Boolean =
    val (finalStack, allValid) = input.foldLeft((List.empty[Char], true)): (tup, chr) =>
      tup match
        case (stack, valid) if ends.contains(chr)     => (safeTail(stack), valid && isValid(stack, chr))
        case (stack, valid) if brackets.contains(chr) => (brackets(chr) :: stack, valid)
        case _                                        => tup
    finalStack.isEmpty && allValid
```

For more information, check the [`foldLeft()` approach][approach-foldleft].

## Approach: Recursion

```scala
import scala.annotation.tailrec

object MatchingBrackets:

  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends     = Set(')', '}', ']')

  private def isValid(stack: List[Char], char: Char): Boolean = stack.nonEmpty && stack.head == char

  private def safeTail(stack: List[Char]): List[Char] = if stack.nonEmpty then stack.tail else stack

  def isPaired(input: String): Boolean = isPairedRecur(input, List.empty)

  @tailrec
  private def isPairedRecur(input: String, stack: List[Char]): Boolean =
    if input.isEmpty then stack.isEmpty
    else
      (input.head, stack) match
        case (chr, stack) if ends.contains(chr)     =>
          if isValid(stack, chr) then isPairedRecur(input.tail, safeTail(stack))
          else false
        case (chr, stack) if brackets.contains(chr) => isPairedRecur(input.tail, brackets(chr) :: stack)
        case _                                      => isPairedRecur(input.tail, stack)
```

For more information, check the [Recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-foldleft]: https://exercism.org/tracks/scala/exercises/matching-brackets/approaches/foldleft
[approach-recursion]: https://exercism.org/tracks/scala/exercises/matching-brackets/approaches/recursion
