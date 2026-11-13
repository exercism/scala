# Sequentially take from shuffled names

```scala
import scala.util.Random

class Robot:
  var name: String = genName()

  def reset(): Unit = name = genName()

  private def genName(): String = Robot.names.next()

object Robot:
  private val names: Iterator[String] =
    val seqNames =
      for
        ltr1 <- 'A' to 'Z'
        ltr2 <- 'A' to 'Z'
        num  <- 0 to 999
      yield f"$ltr1%c$ltr2%c$num%03d"
    Random.shuffle(seqNames).iterator
```

This approach uses a [`for` comprehension][for-comprehension] for generating the two letters and number in sequence.
It [`yield`][yield]s a string, using an [f interpolator][f-interpolator] to format leading zeroes for the number as needed for a three-digit
representation.

The names are generated into an [IndexedSeq][indexedseq] which is then [`shuffle()`][shuffle]d and turned into an [Iterator][iterator].
The `Iterator` is what is held by the private `names` value, which the `Robot` class can use because the `Robot` object is its companion.

Although `names.next()` returns a different value every time it is called, `names` is an immutable `val` because it is not reassigned.

The shuffled approach can finish all the tests, including generating all 676,000 unique names, in under a second.

[for-comprehension]: https://docs.scala-lang.org/scala3/book/control-structures.html#for-expressions
[yield]: https://www.geeksforgeeks.org/scala-yield-keyword/
[f-interpolator]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[indexedseq]: https://www.scala-lang.org/api/current/scala/collection/immutable/IndexedSeq.html
[shuffle]: https://www.scala-lang.org/api/current/scala/util/Random.html
[iterator]: https://www.scala-lang.org/api/current/scala/collection/Iterator.html
