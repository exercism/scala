# Counting with `groupMapReduce()`

```scala
import java.util.Locale

object Isogram:
  def isIsogram(s: String): Boolean =
    s.toLowerCase(Locale.ROOT)
      .filter(_.isLetter)
      .groupMapReduce(identity)(_ => 1)(_ + _)
      .values
      .forall(_ == 1)
```

This approach is a chain of calls, each of which works on the result of the one before it.

It starts by lowercasing the string with `toLowerCase`, so that `A` and `a` count as the same letter.
The [`filter()`][stringops] method then keeps only the letters, and drops the spaces and hyphens.

The [`groupMapReduce()`][stringops] method counts how often each letter occurs.
It takes three functions, each in its own pair of parentheses:

- The first one gives the key under which a character is grouped.
  [`identity`][predef] returns its argument, so each letter is grouped with the other occurrences of the same letter.
- The second one turns a character into a value.
  `_ => 1` ignores the character and gives `1`, so each occurrence counts as one.
- The third one combines two values of the same group.
  `_ + _` adds them, so the ones of a group add up to the number of occurrences.

The result is a [`Map`][map] from each letter to its count.
For `"eleven"` it has `3` for `e`, and `1` for `l`, `v` and `n`.

The [`values`][map] method gives the counts without the letters,
and [`forall()`][iterable] checks that every one of them is `1`.
For a string with no letters the map is empty, and `forall()` is `true` for an empty collection, so an empty string is an isogram.

~~~~exercism/note
`groupMapReduce()` does in one pass what `groupBy(identity)` followed by a count of each group does in two.
With `groupBy()` the values of the map are the groups themselves, such as `"eee"`, and each of them has to be measured afterwards.
~~~~

[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[predef]: https://www.scala-lang.org/api/current/scala/Predef$.html
[map]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[iterable]: https://www.scala-lang.org/api/current/scala/collection/Iterable.html
