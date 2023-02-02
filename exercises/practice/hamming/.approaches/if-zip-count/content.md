# `if` with `zip()` and `count()`

```scala
object Hamming {

  def distance(strand1: String, strand2: String): Option[Int] = {
    if (strand1.length != strand2.length)
      return None
    Some(strand1 zip strand2 count (strand => strand._1 != strand._2))
  }
}
```

This approach starts by using an `if` statement to compare the lengths of the strands.
If the lengths are not equal the function returns [`None`][none].

Otherwise, the two strands are [`zip`][zip]ped together into [tuples][tuple] of a [`Char`][char] from each of the strands.
So the first tuple could have the first `Char` from each of the strands, the second tuple would have the second `Char` from each of the stands,
and so on.
Each tuple is passed to the [`count()`][count] method.
The [lambda][lambda] in the `count()` method compares the `Char`s in the tuple and adds to the count only when the two `Char`s are different from each other.

When the `zip()`method has iterated all of the tuples, the result of the count is wrapped in a [`Some`][some].
Since all of that is the last line in the function, the `Some` expression is implicitly returned from the function without needing the `return` keyword.

[none]: https://www.scala-lang.org/api/2.13.3/scala/None$.html
[some]: https://www.scala-lang.org/api/2.13.3/scala/Some.html
[zip]: https://www.scala-lang.org/api/2.13.3/scala/collection/Iterable.html#zip[B](that:scala.collection.IterableOnce[B]):CC[(A@scala.annotation.unchecked.uncheckedVariance,B)]
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[char]: https://www.scala-lang.org/api/2.12.1/scala/Char.html
[count]: https://www.scala-lang.org/api/2.13.3/scala/collection/mutable/Iterable.html#count(p:A=%3EBoolean):Int
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
