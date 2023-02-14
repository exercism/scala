# Introduction

There are several ways to solve Raindrops.
One way is to traverse a [`SortedMap`][sortedmap] from left to right.
Another way is to traverse a [`List`][list] from right to left.

## General Guidance

One concern is to try to maintain [immutability][immutability].

## Approach: `SortedMap` with `filterKeys()` and `match`

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

For more information, check the [`SortedMap` with `filterKeys()` and `match` approach][approach-sortedmap-filterkeys-match].

## Approach: `List` with `foldRight()` and `match`

```scala
object Raindrops {

  private val drops = List((3, "Pling"), (5, "Plang"), (7, "Plong"))

  def convert(n: Int): String =
    drops.foldRight(List[String]())((factor_drop, acc) =>
      if (n % factor_drop._1 == 0) factor_drop._2 :: acc else acc
    ) match {
      case drops if !drops.isEmpty => drops.mkString
      case _                       => n.toString()
    }
}
```

For more information, check the [`List` with `foldRight()` and `match` approach][approach-list-foldright-match].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between the approaches can be made by perceived readability.

[sortedmap]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/SortedMap.html
[list]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/List.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-sortedmap-filterkeys-match]: https://exercism.org/tracks/scala/exercises/raindrops/approaches/sortedmap-filterkeys-match
[approach-list-foldright-match]: https://exercism.org/tracks/scala/exercises/raindrops/approaches/list-foldright-match
