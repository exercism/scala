# `forall()` with `contains()`

```scala
object Pangrams:
  def isPangram(input: String): Boolean =
    val lowercase = input.toLowerCase
    ('a' to 'z').forall(lowercase.contains)
```

This approach starts by lowercasing the sentence with `toLowerCase`, so that `A` and `a` count as the same letter.
The result is kept in a value, so the sentence is lowercased only once.

The [`to`][to] method creates the range of [`Char`][char]s from `a` through `z`.
The [`forall()`][numeric-range] method takes a function from an element to a `Boolean`, and returns `true` only if the function returns `true` for every element of the range.
It stops at the first element for which the function returns `false`, so a sentence without an `a` is rejected after a single search.

The function passed here is the [`contains()`][stringops] method of the lowercased sentence.
Writing `lowercase.contains` is a shorter way of writing the [lambda][lambda] `c => lowercase.contains(c)`.

The spaces, digits and punctuation marks in the sentence need no special handling, because only the letters are searched for.

Each call to `contains()` searches the sentence from its start and stops when it finds the letter.
A letter that appears only near the end costs a scan of almost the whole sentence, so a long sentence can be read up to 26 times.
For a sentence of ordinary length that is not a concern, but the [`Set` with `subsetOf()` approach][approach-set-subset] goes through the sentence only once.

[to]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
[numeric-range]: https://www.scala-lang.org/api/current/scala/collection/immutable/NumericRange.html
[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[approach-set-subset]: https://exercism.org/tracks/scala/exercises/pangram/approaches/set-subset
