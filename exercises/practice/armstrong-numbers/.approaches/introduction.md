# Introduction

There are at least three ways to solve Armstrong Numbers.
One approach is to convert the input number to a `String` with [`map()`][map] to iterate and process
the characters and [`sum()`][sum] to add up the calculation results.
Another approach is to [`iterate()`][iterate] with [`takeWhile()`][takewhile] and [`last`][last].
And another aproach is to use [recursion][recursion].

## General Guidance

Paying attention to data types, particularly to which values are `Double` and which are `Int`, is crucial to getting the expected results
from calculations.

## Approach: `toString() with `map() and `sum()`

```scala
object ArmstrongNumbers {
  def isArmstrongNumber(num: Int): Boolean = {
    val text = num.toString()
    val len: Double = text.length()
    text.map(chr => Math.pow(chr.asDigit, len)).sum == num
  }
}
```

For more information, check the [`toString()` with `map()` and `sum()` approach][approach-tostring-map-sum].

## Approach: `iterate()` with `takeWhile()` and `last`

```scala
import math.{log10, pow}

object ArmstrongNumbers {

  def isArmstrongNumber(num: Int): Boolean =
    if (num < 10) true
    else {
      val len: Double = log10(num).toInt + 1
      Iterator
        .iterate((num, 0.0, false))(tup =>
          (tup._1 / 10, tup._2 + pow(tup._1 % 10, len), tup._1 == 0)
        )
        .takeWhile(tup => !tup._3)
        .to(Seq)
        .last
        ._2 == num
    }
}
```

For more information, check the [`iterate()` with `takeWhile()` and `last` approach][approach-iterate-takewhile-last].

## Approach: recursion

```scala
import scala.annotation.tailrec;

import math.{log10, pow}

object ArmstrongNumbers {

  def isArmstrongNumber(num: Int): Boolean =
    if (num < 10) true
    else {
      val len: Double = log10(num).toInt + 1

      @tailrec
      def recurMe(nbr: Int, total: Double): Boolean =
        nbr match {
          case 0 => total == num
          case n => recurMe(n / 10, total + pow(n % 10, len))
        }
        
      recurMe(num, 0.0)
    }

}
```

For more information, check the [recursion approach][approach-recursion].

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.
The `toString()` approach has less volume of code and is clear, so it may be preferred for readability.
The `iterate()` approach has less type conversions, so it may be more efficient, and if so, may be preferred for performance. 
The recursive approach is a blend between the succinctness of `toString()` and the performance of `iterate()`.

[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#map[B](f:Char=%3EB):IndexedSeq[B]
[sum]: https://www.scala-lang.org/api/2.13.10/scala/collection/IterableOnce.html#sum(implicitnum:scala.math.Numeric[A]):A
[iterate]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator$.html#iterate[A](start:A,len:Int)(f:A=%3EA):CC[A]
[takewhile]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[last]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#last:A
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[approach-tostring-map-sum]: https://exercism.org/tracks/scala/exercises/armstrong-numbers/approaches/tostring-map-sum
[approach-iterate-takewhile-last]: https://exercism.org/tracks/scala/exercises/armstrong-numbers/approaches/iterate-takewhile-last
[approach-recursion]: https://exercism.org/tracks/scala/exercises/armstrong-numbers/approaches/recursion
