# Introduction

A pangram uses every letter of the alphabet at least once, and upper and lower case count as the same letter.
So a solution has to answer one question: are all 26 letters from `a` to `z` in the sentence?

There are several idiomatic ways to answer it.
One approach turns the sentence into a [`Set`][set] and checks that the alphabet is a subset of it.
Another asks, for every letter of the alphabet, whether the sentence contains it.
A third keeps only the letters, removes the duplicates and counts what is left.
The last one does not build a collection at all: it records the letters in the bits of an `Int`.

## Approach: `Set` with `subsetOf()`

```scala
object Pangrams:
  private val alphabet = ('a' to 'z').toSet

  def isPangram(input: String): Boolean =
    alphabet.subsetOf(input.toLowerCase.toSet)
```

For more information, check the [`Set` with `subsetOf()` approach][approach-set-subset].

## Approach: `forall()` with `contains()`

```scala
object Pangrams:
  def isPangram(input: String): Boolean =
    val lowercase = input.toLowerCase
    ('a' to 'z').forall(lowercase.contains)
```

For more information, check the [`forall()` with `contains()` approach][approach-forall].

## Approach: `filter()` with `distinct`

```scala
object Pangrams:
  def isPangram(input: String): Boolean =
    input.toLowerCase.filter(c => 'a' <= c && c <= 'z').distinct.length == 26
```

For more information, check the [`filter()` with `distinct` approach][approach-distinct-count].

## Approach: bit field with `foldLeft()`

```scala
object Pangrams:
  private val allLetters = (1 << 26) - 1

  def isPangram(input: String): Boolean =
    val seen = input.foldLeft(0): (bits, c) =>
      val letter = c.toLower
      if 'a' <= letter && letter <= 'z' then bits | (1 << (letter - 'a')) else bits
    seen == allLetters
```

For more information, check the [bit field with `foldLeft()` approach][approach-bit-field].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The `subsetOf()` and `forall()` approaches read almost like the definition of a pangram, and neither needs the number `26`.
They differ in how they walk the sentence.
`subsetOf()` goes through the sentence once to build the set, and then looks up each letter in it.
`forall()` builds no set, but `contains()` searches the sentence from its start for each letter, so the sentence can be scanned up to 26 times.
For a sentence that is a line of text this does not matter; for a very long input it can.

The `distinct` approach is a single chain of calls, but it has to filter out everything that is not a letter from `a` to `z`,
which the first two approaches get for free.

The bit field visits each character once and allocates nothing.
It is also the hardest to read, so it is best kept for code where that cost has been measured.

[set]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[approach-set-subset]: https://exercism.org/tracks/scala/exercises/pangram/approaches/set-subset
[approach-forall]: https://exercism.org/tracks/scala/exercises/pangram/approaches/forall
[approach-distinct-count]: https://exercism.org/tracks/scala/exercises/pangram/approaches/distinct-count
[approach-bit-field]: https://exercism.org/tracks/scala/exercises/pangram/approaches/bit-field
