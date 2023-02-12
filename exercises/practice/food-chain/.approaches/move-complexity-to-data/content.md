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

The `recite` method uses a [Range][range] to iterate from the starting verse number through the ending verse number.
Each number in the `Range` is passed to the [`map()`][map] method, which in turn passes the number as the index into the [`Vector`][vector] of verses
to get the verse at that index.
The [IndexedSeq][indexedseq] of verses are chained from `map()` to the [`mkString()`][mkstring-triple] method, where the verse or verses are assembled
into a `String` that is returned from `recite()`.

The `backwash()` method is used when building the verses.
It is passed the name of the previous verse, the index for the starting line (inclusive), and the index for the ending line (exclusive).
It splits the verse into lines, takes a slice of lines from the starting index up to but not including the ending index, and uses the
[`mkString()`][mkstring-single] method to return the slice of lines from `backwash()` as a string.

All that is left is the definition of the verses.

[Multiline strings][multiline-strings] are used to define the verses.
Except for the first and last verses, they are also [interpolated strings][interpolated-strings] (also called `s` strings).
The second verse uses the `backwash()` method to interpolate the the last line of the first verse into the end of itself.
After that, all of the verses, except the last verse, use the `backwash()` method to take the third line of the previous verse
up to the end of that verse, and interpolate those lines into the end of itself.
Using the `backwash()` method keeps the code [DRY][dry], so that a change in a previous verse will "automatically" cascade to the following verses,
instead of having to be edited in each successive verse.

[range]: https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Range.html
[map]: https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Range.html#map[B](f:A=%3EB):scala.collection.immutable.IndexedSeq[B]
[vector]: https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Vector.html
[indexedseq]: https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/IndexedSeq.html
[mkstring-triple]: https://www.scala-lang.org/api/2.12.9/scala/collection/Iterable.html#mkString(start:String,sep:String,end:String):String
[mkstring-single]: https://www.scala-lang.org/api/2.12.9/scala/collection/Iterable.html#mkString(sep:String):String
[multiline-strings]: https://docs.scala-lang.org/scala3/book/first-look-at-types.html#two-notes-about-strings
[interpolated-strings]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[dry]: https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
