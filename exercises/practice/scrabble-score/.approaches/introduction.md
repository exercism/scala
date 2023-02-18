# Introduction

There are at least two general ways to solve Scrabble Score.
One approach is to look up the scores with a [`match`][match] expression.
Another approach is to look up the scores with a [`Map`][map-class].

## General guidance

As always, a concern is to try to maintain [immutability][immutability].

## Approach: `match` with `map()`

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

For more information, check the [`match` with `map()` approach][approach-match-with-map].

## Approach: `Map` with `map()`

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

  def score(word: String): Int = word.toUpperCase.map(lookup).sum
}
```

For more information, check the [`Map` with `map()` approach][approach-map-with-map].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between the approaches can be made by personal preference.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[map-class]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-match-with-map]: https://exercism.org/tracks/scala/exercises/scrabble-score/approaches/match-with-map
[approach-map-with-map]: https://exercism.org/tracks/scala/exercises/scrabble-score/approaches/map-with-map
