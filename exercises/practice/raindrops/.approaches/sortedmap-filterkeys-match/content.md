# `SortedMap` with `filterKeys()` and `match`

```scala
import scala.collection.SortedMap

object Raindrops {
  private val sound = SortedMap(
    3 -> "Pling",
    5 -> "Plang",
    7 -> "Plong"
  )
  
  def convert(n: Int): String =
    sound.filterKeys(n % _ == 0).values match {
      case drops if (!drops.isEmpty) => drops.mkString
      case _                         => n.toString
    }
}
```

This approach starts by defining a [`SortedMap`][sortedmap] with the numbers for keys and the sounds for values.
The [`filterKeys()`][filterkeys] method is called on the `SortedMap`.
Each key is passed to the [lambda][lambda], where the input number is tested to be evenly divisible by the key.

Those keys by which the input number can be evenly divided have their values passed to the [`match`][match], where the values are checked for being empty.
If not empty, then the [`mkString`][mkstring] method is used to return the values as a `String` from the `convert()` method.

Otherwise, if there are no matching keys, then the values will be empty, and the `match` returns the original number as a `String` from the `convert()` method.

[sortedmap]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/SortedMap.html
[filterkeys]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/SortedMap.html#filterKeys(p:A=%3EBoolean):scala.collection.immutable.SortedMap[A,B]
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[mkstring]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/SortedMap.html#mkString:String
