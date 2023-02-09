# Recursion

```scala
case class DNA(strand: String) {

  def nucleotideCounts: Either[String, Map[Char, Int]] = {
    countRecur(strand, Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0))
  }
  
  @scala.annotation.tailrec
  private def countRecur(
      strand: String,
      output: Map[Char, Int]
  ): Either[String, Map[Char, Int]] = {
    if (strand.isEmpty) return Right(output)
    strand.head match {
      case chr if output.contains(chr) =>
        countRecur(strand.tail, output + (chr -> (output(chr) + 1)))
      case chr => Left(s"invalid nucleotide '$chr'")
    }
  }
}
```

The `nucleotideCounts()` method returns the result of calling the private recursive method, passing in the
input `String` and a [`Map`][map].

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

If the input `String` is empty, then the method returns the `Map` wrapped as a [`Right`][right] value.

Otherwise, the [head][head] method is used to get the first character in the `String`, which is passed to the [`match`][match].
The [pattern matching][pattern-matching] checks to see if the character is valid.

If the `Map` [`contains()`][contains] the character as a key, then the recursive method calls itself, passing the
[tail][tail] of the input `String` and a new `Map` created by using the `+` alias for the [`updated()`][updated] method.
Recreating an immutable `Map` instead of changing a mutable `Map` is how the approach maintains [immutability][immutability].

If the `Map` does not contain the character, then an error `String` is created and wrapped as a [`Left`][left] value,
which is immediately returned from the recursive method.

[left]: https://www.scala-lang.org/api/2.13.10/scala/util/Left.html
[right]: https://www.scala-lang.org/api/2.13.10/scala/util/Right.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[contains]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[updated]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html#contains(key:K):Boolean
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#head:A
[tail]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#tail:Repr
