# `SortedMap` with `filterKeys()` and `match`

```scala
import scala.collection.immutable.SortedMap

object Raindrops:

  private val sound = SortedMap(
    3 -> "Pling",
    5 -> "Plang",
    7 -> "Plong",
  )

  def convert(n: Int): String =
    sound.view.filterKeys(n % _ == 0).values match
      case drops if drops.nonEmpty => drops.mkString
      case _                       => n.toString
```

This approach starts by defining a [`SortedMap`][sortedmap] with the numbers for keys and the sounds for values.
The [`filterKeys()`][filterkeys] method is called on a [`view`][view] of the `SortedMap`.
Since Scala 2.13, `filterKeys()` on a `Map` itself is deprecated, because it returned a lazy view anyway; calling it on the view says so.
The view keeps the order of the `SortedMap`, so the sounds come out in the order of their keys.
Each key is passed to the [lambda][lambda], where the input number is tested to be evenly divisible by the key.

Those keys by which the input number can be evenly divided have their values passed to the [`match`][match].
A [pattern guard][guard] checks that the values are not empty.
If so, then the [`mkString`][mkstring] method is used to return the values as a `String` from the `convert()` method.

Otherwise, if there are no matching keys, then the values will be empty, and the `match` returns the original number as a `String` from the `convert()` method.

[sortedmap]: https://www.scala-lang.org/api/current/scala/collection/immutable/SortedMap.html
[view]: https://www.scala-lang.org/api/current/scala/collection/immutable/SortedMap.html
[filterkeys]: https://www.scala-lang.org/api/current/scala/collection/MapView.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[guard]: https://docs.scala-lang.org/scala3/book/control-structures.html#using-if-guards-in-case-clauses
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
