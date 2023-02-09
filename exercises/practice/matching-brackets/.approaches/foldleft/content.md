# `foldLeft()`

```scala
object MatchingBrackets {
  private val brackets = Map('(' -> ')', '{' -> '}', '[' -> ']')
  private val ends = Set(')', '}', ']')
  
  private def isValid(stack: Vector[Char], char: Char): Boolean =
    stack.length > 0 && stack.last == char
  private def safeInit(stack: Vector[Char]): Vector[Char] = {
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

This approach starts by defining a [`Map`][map] for associating beginning brackets with their ending brackets.
A [`Set`][set] is defined to hold the ending brackets.

A private helper method is defined to determine if an ending bracket is valid.
Another private helper method returns all but the last element from a [`Vector`][vector], or returns the `Vector`
as is if it is already empty.

The `isPaired()` method starts by calling the [`foldLeft()`][foldleft] method on the input `String`.
It is initialized with an empty `Vector` for the "stack" and `true` for a validity flag, both wrapped in a [tuple][tuple].

The tuple and each character from the input is passed into the [lambda][lambda].
A [match][match] is used to destructure the tuple and uses the [`contains()`][set-contains] method
to check if the character is an ending bracket.
If so, then a new tuple is created, passing all but the last element of the `Vector` and setting the validity flag to if
it was already `true` and whether the ending bracket is valid.

If the character is not an ending bracket, then the [`contains()`][map-contains] method is used to check for it being a beginning bracket.
If so, then a new tuple is created, passing the [`appended()`][appended] `Vector` and the validity flag as is.

Note that the operations on the `Vector` create a new `Vector`, thus supporting [immutability][immutability].

If the character is not any bracket, then the underscore (`_`) wildcard is used to pass the tuple as is to the next iteration of `foldLeft()`.

After the `foldLeft()` is done, its final tuple is passed to a `match`, where it is destructured.
The method returns whether the stack `Vector` is empty and if the validity flag is `true`.

[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[set]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Set.html
[vector]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Vector.html
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
