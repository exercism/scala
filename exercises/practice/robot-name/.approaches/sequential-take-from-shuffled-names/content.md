# Sequentially take from shuffled names

```scala
import scala.util.Random

class Robot {

  var name = genName()

  def reset() = name = genName()

  private def genName(): String = Robot.names.next()
}

object Robot {
  val names: Iterator[String] = {
    val seqNames =
      for {
        ltr1 <- ('A' to 'Z')
        ltr2 <- ('A' to 'Z')
        num <- (0 to 999)
      } yield {
        f"$ltr1%c$ltr2%c$num%03d"
      }
    Random.shuffle(seqNames).to(LazyList).iterator
  }
}
```

This approach uses a [`for` comprehension][for-comprehension] for generating the two letters and number in sequence.
It [`yield`][yield]s a string, using an [f interpolator][f-interpolator] to format leading zeroes for the number as needed for a three-digit
representation.

The names are generated into an [IndexedSeq][indexedseq] which is then [`shuffle()`][shuffle]ed and collected into a [`LazyList`][lazylist],
which is then chained to an [Iterator][iterator].
The `Iterator` is what is returned from the `names` value.

Although `names` keeps returning a different value every time it is called, it is an immutable `val` because it is not reassigned.

The shuffled approach can finish all the tests, including generating all 676,000 unique names, in about one-and-a-half seconds.

[for-comprehension]: https://docs.scala-lang.org/tour/for-comprehensions.html
[yield]: https://www.geeksforgeeks.org/scala-yield-keyword/
[f-interpolator]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[indexedseq]: https://www.scala-lang.org/api/2.13.5/scala/collection/immutable/IndexedSeq.html
[shuffle]: https://www.scala-lang.org/api/2.13.5/scala/util/Random.html#shuffle[T,C](xs:IterableOnce[T])(implicitbf:scala.collection.BuildFrom[xs.type,T,C]):C
[lazylist]: https://www.scala-lang.org/api/2.13.5/scala/collection/immutable/LazyList.html
[iterator]: https://www.scala-lang.org/api/2.13.5/scala/collection/Iterator.html
