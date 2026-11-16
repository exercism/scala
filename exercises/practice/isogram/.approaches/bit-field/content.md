# Bit field with `bitCount()`

```scala
object Isogram:
  def isIsogram(s: String): Boolean =
    val letters = s.toLowerCase.filter(c => 'a' <= c && c <= 'z')
    val seen    = letters.foldLeft(0)((bits, c) => bits | (1 << (c - 'a')))
    Integer.bitCount(seen) == letters.length
```

This approach uses the bits of an [`Int`][int] to record which letters are in the string.
An `Int` has 32 bits, which is enough for the 26 letters: bit `0` stands for `a`, bit `1` for `b`, and so on up to bit `25` for `z`.

It starts by lowercasing the string with `toLowerCase`, so that `A` and `a` set the same bit.
The [`filter()`][stringops] method keeps the characters from `a` through `z`, and drops the spaces and hyphens.

The [`foldLeft()`][stringops] method then goes through the letters.
It starts with `0`, a number with no bits set, and passes the bits collected so far and the next letter to its [lambda][lambda].
Subtracting `'a'` from the letter gives its position in the alphabet, from `0` to `25`.
The [shift left operator][int] `<<` moves the single bit of `1` to that position, and the [bitwise OR operator][int] `|` sets that bit in the collected bits.
Setting a bit that is already set changes nothing, so a letter that is repeated still sets only one bit.

That is what the last line relies on.
[`Integer.bitCount()`][bitcount] returns the number of bits that are set, which is the number of different letters.
If it is equal to the number of letters, then no letter is repeated.

~~~~exercism/caution
The bits stand for the letters from `a` through `z` only, so the filter cannot be `isLetter`.
A letter such as `é` or `я` is dropped like a space, and a word that repeats one of them would be taken for an isogram.
Use one of the other approaches if the input is not limited to English letters.
~~~~

[int]: https://www.scala-lang.org/api/current/scala/Int.html
[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[bitcount]: https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Integer.html#bitCount(int)
