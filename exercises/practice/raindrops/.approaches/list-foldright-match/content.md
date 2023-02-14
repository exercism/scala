# `List` with `foldRight()` and `match`

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

This approach starts by defining a [`List`][list] of [tuples][tuple] with numbers for the divisors and strings for the sounds.
The [`foldRight()`][foldright] method is initialized with an empty `List` for its output and is called on the `List` of drops.
Each tuple is passed to the [lambda][lambda], where the input number is tested to be evenly divisible by number in the tuple.

Those tuple numbers by which the input number can be evenly divided have their strings prepended to the output List with the cons operator (`::`).
When `foldRight()` is finished, its output `List` is passed to the [`match`][match], where the `List` is checked for being empty.
If not empty, then the [`mkString`][mkstring] method is used to return the `List` elements as a `String` from the `convert()` method.

Otherwise, if the `List` is empty, then the `match` returns the original number as a `String` from the `convert()` method.

[list]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/List.html
[foldRight]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/List.html#foldRight[B](z:B)(op:(A,B)=%3EB):B
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[mkstring]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#mkString:String
