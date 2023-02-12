# Introduction

There are many idiomatic ways to solve Food Chain.
Many approaches chop up the data in various ways and use code of varying complexity to reassemble it.
Another approach could be to use code to define the data so that the code can be very simple and readable.

## General guidance

A quote by by Eric S. Raymond in _The Art of Unix Programming_  is particularly applicable to Food Chain:

>Fold knowledge into data so program logic can be stupid and robust.
>Data is more tractable than program logic.
>It follows that where you see a choice between complexity in data structures and complexity in code, choose the former.
>More: in evolving a design, you should actively seek ways to shift complexity from code to data.

For Food Chain, the pattern in the data is that, after the second verse and until the last verse, each verse takes from the third line to the end
of the previous verse.

## Approach: Move compexity to data

```scala
object FoodChain {

  def recite(fromVerse: Int, toVerse: Int): String = {
    (fromVerse to toVerse)
      .map(verses)
      .mkString("", "\n", "\n")
  }

  private def backwash(reflux: String, start: Int, end: Int): String =
    reflux.split("\n").slice(start, end).mkString("\n")

  private val fly = """I know an old lady who swallowed a fly.
I don't know why she swallowed the fly. Perhaps she'll die.
"""

  private val spider = s"""I know an old lady who swallowed a spider.
It wriggled and jiggled and tickled inside her.
She swallowed the spider to catch the fly.
${backwash(fly, 1, 2)}
"""

  private val bird = s"""I know an old lady who swallowed a bird.
How absurd to swallow a bird!
She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
${backwash(spider, 2, 4)}
"""

  private val cat = s"""I know an old lady who swallowed a cat.
Imagine that, to swallow a cat!
She swallowed the cat to catch the bird.
${backwash(bird, 2, 5)}
"""

  private val dog = s"""I know an old lady who swallowed a dog.
What a hog, to swallow a dog!
She swallowed the dog to catch the cat.
${backwash(cat, 2, 6)}
"""

  private val goat = s"""I know an old lady who swallowed a goat.
Just opened her throat and swallowed a goat!
She swallowed the goat to catch the dog.
${backwash(dog, 2, 7)}
"""
  private val cow = s"""I know an old lady who swallowed a cow.
I don't know how she swallowed a cow!
She swallowed the cow to catch the goat.
${backwash(goat, 2, 8)}
"""

  private val horse = """I know an old lady who swallowed a horse.
She's dead, of course!
"""

  private val verses = Vector("", fly, spider, bird, cat, dog, goat, cow, horse)

}
```

For more information, check the [Move complexity to data approach][approach-move-complexity-to-data].

[approach-move-complexity-to-data]: https://exercism.org/tracks/scala/exercises/food-chain/approaches/move-complexity-to-data
