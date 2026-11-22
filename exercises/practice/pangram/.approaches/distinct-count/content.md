# `filter()` with `distinct`

```scala
import java.util.Locale

object Pangrams:
  def isPangram(input: String): Boolean =
    input.toLowerCase(Locale.ROOT).filter(c => 'a' <= c && c <= 'z').distinct.length == 26
```

This approach is a chain of calls, each of which works on the result of the one before it.

It starts by lowercasing the sentence with `toLowerCase`, so that `A` and `a` count as the same letter.

The [`filter()`][stringops] method keeps only the characters for which its [lambda][lambda] returns `true`.
[`Char`][char]s can be compared with `<=`, so the lambda keeps the characters from `a` through `z` and drops the spaces, digits and punctuation marks.

The [`distinct`][stringops] method removes the repeated characters, keeping the first occurrence of each.
What is left is a `String` with one character for each different letter in the sentence.
If its `length` is `26`, then every letter of the alphabet is in the sentence.

~~~~exercism/caution
The filter has to be exactly the range from `a` through `z`.
The `isLetter` method looks like a shorter way to write it, but it is also `true` for letters such as `é` and `я`.
Those would be counted too, so a sentence with only 25 letters of the alphabet and one `é` would be taken for a pangram.
~~~~

[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
