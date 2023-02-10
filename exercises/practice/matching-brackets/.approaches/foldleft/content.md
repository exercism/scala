# `foldLeft()`

```scala
object MatchingBrackets {
  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends = Set(')', '}', ']')
  def isValid(stack: List[Char], char: Char): Boolean =
    stack.length > 0 && stack.head == char
  def safeTail(stack: List[Char]): List[Char] = {
    if (!stack.isEmpty) stack.tail else stack
  }

  def isPaired(input: String): Boolean = {
    input
      .foldLeft((List[Char](), true))((tup, chr) =>
        tup match {
          case (stack, valid) if ends.contains(chr) =>
            (safeTail(stack), valid && isValid(stack, chr))
          case (stack, valid) if (brackets.contains(chr)) =>
            (brackets(chr) :: stack, valid)
          case _ => tup
        }
      ) match {
      case (stack, isValid) => stack.isEmpty && isValid
    }
  }
}
```

This approach starts by defining a [`Map`][map] for associating beginning brackets with their ending brackets.
A [`Set`][set] is defined to hold the ending brackets.

A private helper method is defined to determine if an ending bracket is valid.
Another private helper method returns all but the first element from a [`List`][list], or returns the `List`
as is if it is already empty.

The `isPaired()` method starts by calling the [`foldLeft()`][foldleft] method on the input `String`.
It is initialized with an empty `List` for the "stack" and `true` for a validity flag, both wrapped in a [tuple][tuple].

```exercism/note
Note that the [immutable `Stack`](https://www.scala-lang.org/api/2.12.17/scala/collection/immutable/Stack.html)
exists only for historical reason and as an analogue of mutable stacks.
Instead of an immutable stack you can just use a list.

The [mutable `Stack`](https://www.scala-lang.org/api/2.12.17/scala/collection/mutable/Stack.html) was deprecated since version 2.12.0.
`Stack` is an inelegant and potentially poorly-performing wrapper around List.
Use a List assigned to a var instead.
```

The tuple and each character from the input is passed into the [lambda][lambda].
A [match][match] is used to destructure the tuple and uses the [`contains()`][set-contains] method
to check if the character is an ending bracket.
If so, then a new tuple is created, passing all but the first element of the `List` and setting the validity flag to if
it was already `true` and whether the ending bracket is valid.

If the character is not an ending bracket, then the [`contains()`][map-contains] method is used to check for it being a beginning bracket.
If so, then a new tuple is created, passing the associated ending bracket prepended to the `List` along with the validity flag as is.

Note that the operations on the `List` create a new `List`, thus supporting [immutability][immutability].

If the character is not any bracket, then the underscore (`_`) wildcard is used to pass the tuple as is to the next iteration of `foldLeft()`.

After the `foldLeft()` is done, its final tuple is passed to a `match`, where it is destructured.
The method returns whether the stack `List` is empty and if the validity flag is `true`.

[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[set]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Set.html
[list]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/List.html
[foldleft]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#foldLeft[B](z:B)(op:(B,Char)=%3EB):B
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[set-contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Set.html#contains(elem:A):Boolean
[map-contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[appended]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Vector.html#appended[B%3E:A](elem:B):scala.collection.immutable.Vector[B]
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[underscore]: https://www.baeldung.com/scala/underscore
