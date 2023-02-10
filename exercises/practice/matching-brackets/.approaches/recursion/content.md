# Recursion

```scala
object MatchingBrackets {
  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends = Set(')', '}', ']')

  private def isValid(stack: List[Char], char: Char): Boolean =
    stack.length > 0 && stack.head == char
  private def safeTail(stack: List[Char]): List[Char] = {
    if (!stack.isEmpty) stack.tail else stack
  }

  def isPaired(input: String): Boolean = {
    isPairedRecur(input, List[Char]())
  }

  @scala.annotation.tailrec
  private def isPairedRecur(input: String, stack: List[Char]): Boolean = {
    if (input.isEmpty) return stack.isEmpty
    (input.head, stack) match {
      case (chr, stack) if ends.contains(chr) =>
        if (isValid(stack, chr))
          isPairedRecur(input.tail, safeTail(stack))
        else
          false
      case (chr, stack) if (brackets.contains(chr)) =>
        isPairedRecur(input.tail, brackets(chr) :: stack)
      case _ => isPairedRecur(input.tail, stack)
    }
  }
}
```

This approach starts by defining a [`Map`][map] for associating beginning brackets with their ending brackets.
A [`Set`][set] is defined to hold the ending brackets.

A private helper method is defined to determine if an ending bracket is valid.
Another private helper method returns all but the first element from a [`List`][list], or returns the `List`
as is if it is already empty.

The `isPaired()` method returns the result of calling the private recursive method, to which it passes the input `String`.
and an empty `List` for the "stack".

```exercism/note
Note that the [immutable `Stack`](https://www.scala-lang.org/api/2.12.17/scala/collection/immutable/Stack.html)
exists only for historical reason and as an analogue of mutable stacks.
Instead of an immutable stack you can just use a list.

The [mutable `Stack`](https://www.scala-lang.org/api/2.12.17/scala/collection/mutable/Stack.html) was deprecated since version 2.12.0.
`Stack` is an inelegant and potentially poorly-performing wrapper around List.
Use a List assigned to a var instead.
```

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

If the input `String` is empty, then the method returns if the "stack" `List` is empty.

The [`head`][head] method is used to get the first character in the input `String`. It is wrapped in a [tuple][tuple] with
the "stack" `List`, which is passed to the [`match`][match].

The [pattern matching][pattern-matching] uses the [`contains()`][set-contains] method to check if the character is an ending bracket.
If so, and it is valid, then the recursive method calls itself, passing the [tail][tail] of the input `String` and all but the first element of the "stack" `List`.
If it is not a valid ending bracket, then the recursive method immediately returns `false`.

If the character is not an ending bracket, then the [`contains()`][map-contains] method is used to check for it being a beginning bracket.
If so, then the recursive method calls itself, passing the [`tail`][tail] of the input `String` and the the associated ending bracket
prepended to the "stack" `List`.

Note that the operations on the `List` create a new `List`, thus supporting [immutability][immutability].

If the character is not any bracket, then the underscore (`_`) wildcard is used to have the the recursive method call itself,
passing the [`tail`][tail] of the input `String` and the "stack" `List` as is.

[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[set]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Set.html
[list]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/List.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[set-contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Set.html#contains(elem:A):Boolean
[map-contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[appended]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Vector.html#appended[B%3E:A](elem:B):scala.collection.immutable.Vector[B]
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[underscore]: https://www.baeldung.com/scala/underscore
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#head:A
[tail]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#tail:Repr
