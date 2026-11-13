# Introduction

There are several ways to solve Pig Latin.
Some use regular expressions or replacing characters or a combination of both.
The regular expressions may be more or less readable (often less), and replacing characters can be expensive.
Another approach could use [`map()`][map] with [recursion][recursion] and [`mkString()`][mkstring] for both a readable and reasonably efficient way.

## General guidance

At the time of writing only four rules need to be handled, but if they have similar output, they don't need to be handled completely separately.

## Approach: `map()` with recursion and `mkString()`

```scala
import scala.annotation.tailrec

object PigLatin:

  private val vowels   = Set('a', 'e', 'i', 'o', 'u')
  private val vowelsY  = Set('a', 'e', 'i', 'o', 'u', 'y')
  private val specials = Set("xr", "yt")

  def translate(phrase: String): String =
    phrase
      .split(" ")
      .map: word =>
        if vowels.contains(word.head) || specials.contains(word.slice(0, 2)) then word + "ay"
        else findVowel(word, 1)
      .mkString(" ")

  @tailrec
  private def findVowel(word: String, pos: Int): String =
    word.charAt(pos) match
      case ltr if vowelsY.contains(ltr) =>
        val posFix = if word.slice(pos - 1, pos + 1) == "qu" then pos + 1 else pos
        word.slice(posFix, word.length) + word.slice(0, posFix) + "ay"
      case _                            => findVowel(word, pos + 1)
```

For more information, check the [`map()` with recursion and `mkString()` approach][approach-map-recursion-mkstring].

[map]: https://www.scala-lang.org/api/current/scala/collection/ArrayOps.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
[approach-map-recursion-mkstring]: https://exercism.org/tracks/scala/exercises/pig-latin/approaches/map-recursion-mkstring
