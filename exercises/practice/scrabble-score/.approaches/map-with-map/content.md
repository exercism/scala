#  `Map` with `map()`

```scala
object ScrabbleScore {

  val lookup: Map[Char, Int] = Map(
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

TODO
