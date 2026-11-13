# `match` with `map()`

```scala
object ScrabbleScore:

  private def letterValue(c: Char): Int = c match
    case 'A' | 'E' | 'I' | 'O' | 'U' | 'L' | 'N' | 'R' | 'S' | 'T' => 1
    case 'D' | 'G'                                                 => 2
    case 'B' | 'C' | 'M' | 'P'                                     => 3
    case 'F' | 'H' | 'V' | 'W' | 'Y'                               => 4
    case 'K'                                                       => 5
    case 'J' | 'X'                                                 => 8
    case 'Q' | 'Z'                                                 => 10

  def score(word: String): Int = word.toUpperCase.map(letterValue).sum
```

This approach starts by defining a private method that uses a [`match`][match] for returning a score based on the letter.
Several letters share a `case` by being separated with `|`.
Note that the most likely letters are in the first case, and the least likely letters are in the last case.

The `score()` method passes each uppercased character to the [`map()`][map] method, which passes each character to the lookup method,
which returns the score for the character.

The `score()` method returns the result of passing all of the scores to the [`sum()`][sum] method.

[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[map]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[sum]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
