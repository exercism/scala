# `filter()` with `distinct`

```scala
object Isogram:
  def isIsogram(s: String): Boolean =
    val letters = s.toLowerCase.filter(_.isLetter)
    letters.distinct.length == letters.length
```

This approach starts by lowercasing the string with `toLowerCase`, so that `A` and `a` count as the same letter.

The [`filter()`][stringops] method keeps only the characters for which the function passed to it returns `true`.
`_.isLetter` is a short way to write the [lambda][lambda] `c => c.isLetter`.
[`isLetter`][richchar] is `true` for a letter and `false` for a space, a hyphen or a digit, so those are dropped.
The result is kept in the value `letters`, because it is needed twice.

The [`distinct`][stringops] method removes the repeated characters, keeping the first occurrence of each.
If no letter is repeated, there is nothing to remove, and the result is as long as `letters`.
If any letter is repeated, the result is shorter.
So comparing the two lengths answers the question.

Since the comparison is the last expression of the method, its value is returned without needing the `return` keyword.

[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[richchar]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
