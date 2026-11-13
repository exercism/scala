# `List` with `foldRight()` and `match`

```scala
object Raindrops:

  private val drops = List(3 -> "Pling", 5 -> "Plang", 7 -> "Plong")

  def convert(n: Int): String =
    drops.foldRight(List.empty[String]) { case ((factor, drop), acc) =>
      if n % factor == 0 then drop :: acc else acc
    } match
      case drops if drops.nonEmpty => drops.mkString
      case _                       => n.toString
```

This approach starts by defining a [`List`][list] of [tuples][tuple] with numbers for the divisors and strings for the sounds.
The [`foldRight()`][foldright] method is initialized with an empty `List` for its output and is called on the `List` of drops.
Each tuple and the output so far are passed to the [lambda][lambda].
The lambda is a `case` clause, so it can destructure the tuple into `factor` and `drop` in its parameters.
The input number is tested to be evenly divisible by the `factor`.

The sounds whose factors evenly divide the input number are prepended to the output `List` with the cons operator (`::`).
When `foldRight()` is finished, its output `List` is passed to the [`match`][match], where the `List` is checked for being empty.
If not empty, then the [`mkString`][mkstring] method is used to return the `List` elements as a `String` from the `convert()` method.

Otherwise, if the `List` is empty, then the `match` returns the original number as a `String` from the `convert()` method.

[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[foldright]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
