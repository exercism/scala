# Bit field with `foldLeft()`

```scala
object Pangrams:
  private val allLetters = (1 << 26) - 1

  def isPangram(input: String): Boolean =
    val seen = input.foldLeft(0): (bits, c) =>
      val letter = c.toLower
      if 'a' <= letter && letter <= 'z' then bits | (1 << (letter - 'a')) else bits
    seen == allLetters
```

This approach uses the bits of an [`Int`][int] to record which letters are in the sentence.
An `Int` has 32 bits, which is enough for the 26 letters: bit `0` stands for `a`, bit `1` for `b`, and so on up to bit `25` for `z`.

The value `allLetters` is the number in which all 26 of those bits are set.
The [shift left operator][int] `<<` moves the single bit of `1` to the left by 26 positions, and subtracting `1` from the result sets the 26 bits below it.

The [`foldLeft()`][stringops] method goes through the sentence one [`Char`][char] at a time.
It starts with `0`, a number with no bits set, and passes the bits collected so far and the next character to its [lambda][lambda].
The value of the lambda becomes the collected bits for the next character.
The lambda is passed after a colon and on indented lines, which Scala 3 allows instead of [enclosing it in braces][optional-braces].

The lambda lowercases the character with [`toLower`][char], so that `A` and `a` set the same bit.
If the character is a letter from `a` through `z`, then subtracting `'a'` from it gives its position in the alphabet, from `0` to `25`.
`1 << (letter - 'a')` is the number with only the bit at that position set, and the [bitwise OR operator][int] `|` sets that bit in the collected bits.
Setting a bit that is already set changes nothing, so a letter that is repeated is counted only once.
Any other character leaves the bits as they are.

When all the characters have been visited, the collected bits are compared with `allLetters`.
They are equal only if every one of the 26 letters was seen.

The sentence is visited once, and no new string or collection is created along the way.
The price is readability: the intent is harder to see here than in the [`Set` with `subsetOf()` approach][approach-set-subset].

[int]: https://www.scala-lang.org/api/current/scala/Int.html
[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[optional-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[approach-set-subset]: https://exercism.org/tracks/scala/exercises/pangram/approaches/set-subset
