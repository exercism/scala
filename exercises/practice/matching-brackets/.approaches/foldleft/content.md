# `foldLeft()`

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

This approach starts by defining a [`Map`][map] for associating beginning brackets with their ending brackets.
A [`Set`][set] is defined to hold the ending brackets.

A private helper method is defined to determine if an ending bracket is valid:
the "stack" must not be empty, and its first element must be that ending bracket.
Another private helper method returns all but the first element from a [`List`][list], or returns the `List`
as is if it is already empty.

The `isPaired()` method starts by calling the [`foldLeft()`][foldleft] method on the input `String`.
It is initialized with an empty `List` for the "stack" and `true` for a validity flag, both wrapped in a [tuple][tuple].

~~~~exercism/note
Scala has no immutable `Stack`: the old one was deprecated in Scala 2.12 and removed in 2.13.
An immutable `List` works as a stack instead: `::` pushes an element onto the front, `head` peeks at it and `tail` pops it.
There is a [mutable `Stack`][mutable-stack], but using it would mean giving up immutability.
~~~~

The tuple and each character from the input is passed into the [lambda][lambda].
The lambda is the last argument of `foldLeft()`, so it can follow a [colon instead of being wrapped in braces][fewer-braces].
A [match][match] is used to destructure the tuple and uses the [`contains()`][set-contains] method
to check if the character is an ending bracket.
If so, then a new tuple is created, passing all but the first element of the `List` and setting the validity flag to if
it was already `true` and whether the ending bracket is valid.

If the character is not an ending bracket, then the [`contains()`][map-contains] method is used to check for it being a beginning bracket.
If so, then a new tuple is created, passing the associated ending bracket prepended to the `List` along with the validity flag as is.

Note that the operations on the `List` create a new `List`, thus supporting [immutability][immutability].

If the character is not any bracket, then the [underscore][underscore] (`_`) wildcard is used to pass the tuple as is to the next iteration of `foldLeft()`.

After the `foldLeft()` is done, its final tuple is destructured into `finalStack` and `allValid`.
The method returns whether the "stack" `List` is empty and the validity flag is `true`.

[foldleft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
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
