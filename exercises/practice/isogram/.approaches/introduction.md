# Introduction

An isogram is a word or phrase in which no letter is repeated.
Spaces and hyphens may appear more than once, and upper and lower case count as the same letter.
So a solution has to ignore everything that is not a letter, treat `A` and `a` alike, and then find out whether any letter occurs twice.

The examples that lowercase the whole string use `Locale.ROOT` so that `I` and `i` count as the same letter even when the JVM’s default locale is Turkish.

There are several idiomatic ways to do that.
One approach removes the duplicates and checks that nothing was removed.
Another counts how often each letter occurs.
A third walks the string and stops as soon as it meets a letter it has seen before.
The last one records the letters in the bits of an `Int`.

## Approach: `filter()` with `distinct`

```scala
import java.util.Locale

object Isogram:
  def isIsogram(s: String): Boolean =
    val letters = s.toLowerCase(Locale.ROOT).filter(_.isLetter)
    letters.distinct.length == letters.length
```

For more information, check the [`filter()` with `distinct` approach][approach-distinct].

## Approach: counting with `groupMapReduce()`

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

For more information, check the [counting with `groupMapReduce()` approach][approach-counting].

## Approach: recursion with a `Set`

```scala
import scala.annotation.tailrec

object Isogram:
  def isIsogram(s: String): Boolean =
    @tailrec
    def loop(index: Int, seen: Set[Char]): Boolean =
      if index == s.length then true
      else
        val c = s(index).toLower
        if !c.isLetter then loop(index + 1, seen)
        else if seen(c) then false
        else loop(index + 1, seen + c)

    loop(0, Set.empty)
```

For more information, check the [recursion with a `Set` approach][approach-recursion-set].

## Approach: bit field with `bitCount()`

```scala
import java.util.Locale

object Isogram:
  def isIsogram(s: String): Boolean =
    val letters = s.toLowerCase(Locale.ROOT).filter(c => 'a' <= c && c <= 'z')
    val seen    = letters.foldLeft(0)((bits, c) => bits | (1 << (c - 'a')))
    Integer.bitCount(seen) == letters.length
```

For more information, check the [bit field with `bitCount()` approach][approach-bit-field].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The `distinct` approach is the shortest, and it says what an isogram is: a phrase whose letters are all different.
For a word or a phrase it is the natural choice.

Counting does more work than the question needs, since it only matters whether a count is above `1`.
It is the approach to reach for when the counts themselves are wanted.

The first two approaches always read the whole string.
The recursion stops at the first repeated letter, which pays off only when the input is long.
It is also the longest of the four.

The bit field builds no collection for the letters it has seen, but it handles only the letters from `a` through `z`.
The other three approaches also recognize letters such as `é` and `я`.

[approach-distinct]: https://exercism.org/tracks/scala/exercises/isogram/approaches/distinct
[approach-counting]: https://exercism.org/tracks/scala/exercises/isogram/approaches/counting
[approach-recursion-set]: https://exercism.org/tracks/scala/exercises/isogram/approaches/recursion-set
[approach-bit-field]: https://exercism.org/tracks/scala/exercises/isogram/approaches/bit-field
