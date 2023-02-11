# Move compexity to data

```scala
object FoodChain {

  def recite(fromVerse: Int, toVerse: Int): String = {
    (fromVerse to toVerse)
      .map(verses)
      .mkString("", "\n", "\n")
  }

  private def backwash(reflux: String, start: Int, end: Int): String =
    reflux.split("\n").slice(start, end).mkString("\n")

  private val fly = s"""I know an old lady who swallowed a fly.
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

