#  `Map` with `map()`

```scala
object ScrabbleScore {

  private val lookup: Map[Char, Int] = Map(
    1 -> "AEIOULNRST",
    2 -> "DG",
    3 -> "BCMP",
    4 -> "FHVWY",
    5 -> "K",
    8 -> "JX",
    10 -> "QZ"
  ).flatMap(tuple => tuple._2.map((_, tuple._1)))

  def score(word: String): Int = word.toUpperCase.map(lookup(_)).sum
}
```

This approach starts be defining a private [`Map`][map-class] for associating a score with a `String` of letters.
To reduce some tedious typing, the [`flatMap()`][flatmap] and [`map()`][map] methods are used to transform the `Map` that looks like

`8 -> 'JX', 10 -> 'QZ'`

to one that looks like 

`'J' -> 8, 'X' -> 8, 'Q' -> 10, 'Z' -> 10`.

Each key/value entry in the `Map` is passed as a [tuple][tuple] to the [lambda][lambda] in `flatMap()`,
in which each character in the value `String` is passed to the  `map()` method.
Inside the `map()` method, a new tuple is created, with the character for the key and the score for the value.

The `score()` method passes each uppercased character to the [`map()`][map] method, which passes each character to the lookup `Map`,
which returns the score for that character.

The `score()` method returns the result of passing all of the scores to the [`sum()`][sum] method.

[map-class]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[flatmap]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html#flatMap[K2,V2](f:((K,V))=%3Escala.collection.IterableOnce[(K2,V2)]):CC[K2,V2]
[map]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html#map[B](f:Char=%3EB):IndexedSeq[B]
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[sum]: https://www.scala-lang.org/api/current/scala/collection/immutable/IndexedSeq.html#sum[B%3E:A](implicitnum:scala.math.Numeric[B]):B
