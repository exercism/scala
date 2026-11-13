# Recursion

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

This approach starts by defining a [`Map`][map] for associating beginning brackets with their ending brackets.
A [`Set`][set] is defined to hold the ending brackets.

A private helper method is defined to determine if an ending bracket is valid:
the "stack" must not be empty, and its first element must be that ending bracket.
Another private helper method returns all but the first element from a [`List`][list], or returns the `List`
as is if it is already empty.

The `isPaired()` method returns the result of calling the private recursive method, to which it passes the input `String`
and an empty `List` for the "stack".

~~~~exercism/note
Scala has no immutable `Stack`: the old one was deprecated in Scala 2.12 and removed in 2.13.
An immutable `List` works as a stack instead: `::` pushes an element onto the front, `head` peeks at it and `tail` pops it.
There is a [mutable `Stack`][mutable-stack], but using it would mean giving up immutability.
~~~~

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

If the input `String` is empty, then the method returns whether the "stack" `List` is empty.

Otherwise, the [`head`][head] method is used to get the first character in the input `String`.
It is wrapped in a [tuple][tuple] with the "stack" `List`, which is passed to the [`match`][match].

The [pattern matching][pattern-matching] uses the [`contains()`][set-contains] method to check if the character is an ending bracket.
If so, and it is valid, then the recursive method calls itself, passing the [`tail`][tail] of the input `String` and all but the first element of the "stack" `List`.
If it is not a valid ending bracket, then the recursive method returns `false` without calling itself again.

If the character is not an ending bracket, then the [`contains()`][map-contains] method is used to check for it being a beginning bracket.
If so, then the recursive method calls itself, passing the [`tail`][tail] of the input `String` and the associated ending bracket
prepended to the "stack" `List`.

Note that the operations on the `List` create a new `List`, thus supporting [immutability][immutability].

If the character is not any bracket, then the [underscore][underscore] (`_`) wildcard is used to have the recursive method call itself,
passing the [`tail`][tail] of the input `String` and the "stack" `List` as is.

[map]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[set]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[mutable-stack]: https://www.scala-lang.org/api/current/scala/collection/mutable/Stack.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[set-contains]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[map-contains]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[underscore]: https://www.baeldung.com/scala/underscore
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tail]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
