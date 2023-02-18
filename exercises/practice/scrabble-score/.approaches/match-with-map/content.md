# `match` with `map()`

```scala
object ScrabbleScore {
  
  private def letterValue(c: Char): Int = c match {
    case 'A' | 'E' | 'I' | 'O' | 'U' | 'L' | 'N' | 'R' | 'S' | 'T' => 1
    case 'D' | 'G'                                                 => 2
    case 'B' | 'C' | 'M' | 'P'                                     => 3
    case 'F' | 'H' | 'V' | 'W' | 'Y'                               => 4
    case 'K'                                                       => 5
    case 'J' | 'X'                                                 => 8
    case 'Q' | 'Z'                                                 => 10
  }

  def score(word: String): Int = word.toUpperCase.map(letterValue).sum
}
```

This approach starts be defining a private method that uses a [`match`][match] for returning a score based on the letter.
Note that the most likely cases values are in the first arm, and the least likely values are in the last arm.

The `score()` method passes each uppercased character to the [`map()`][map] method, which passes each character to the lookup method,
which returns the score for the character.

The `score()` method returns the result of passing all of the scores to the [`sum()`][sum] method.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[map]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html#map[B](f:Char=%3EB):IndexedSeq[B]
[sum]: https://www.scala-lang.org/api/current/scala/collection/immutable/IndexedSeq.html#sum[B%3E:A](implicitnum:scala.math.Numeric[B]):B
