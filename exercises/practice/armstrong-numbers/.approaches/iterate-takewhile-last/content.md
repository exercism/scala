# `iterate()` with `takeWhile()` and `last`

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

This approach starts by importing from the packages for what is needed.

The `isArmstrongNumber()` method uses an `if` and `else` expression.
Given that the test input is never less than `1`, if the input `Int` is less than `10`, then `true` is returned.
Otherwise the result of the `else` expression is returned.

The `else` expression starts by using the [`log10()`][log10] method to calculate the number of digits in the input `Int`.

The [`iterate()`][iterate] method is called with a three-element [tuple][tuple] for a start value.
The starting tuple is made of the input `Int`, a `Double` value of `0.0` for the total, and a `Boolean` `false` to stop.
The tuple is passed into the lambda where a new tuple is generated for the next iteration.

Since `pow()` take two `Double` arguments, 
the length of the `String` was set to the `Double` type to keep from doing a widening conversion of `Int` to `Double` on every call
to `pow()`.

For an input number of `153`, the iterations would give the following tuples

```
(153,0.0), (15,27.0), (1,152.0), (0,153.0)
```

The call to `iterate()` is chained to the [`takewhile()`][takewhile] method, which uses its lambda to determine
what iterations it will ask for.
In this case it will keep taking iterations until the third element of the tuple is `true`, which is not set until
creating the tuple for the iteration _after_ the first element number has been calculated down to 0.

```exercism/caution
We might be tempted to use a tuple of only two elements and `takeWhile(tup => tup._1 > 0)`,
but this will omit the last iteration where the tuple generated is `(0,153.0)`.
The tuple is generated, but since it has a first element of `0`, it is not _taken_.
This is why we need the `Boolean` flag to stop _after_ we've taken the tuple with a first element of `0`.
```

As of this writing, Scala 3 is not yet supported on the Scala track. With Scala 3 we could destructure the tuple in the lambda like so:

```scala
.iterate((num, 0.0, false))((nbr, total, stop) =>
  (nbr / 10, total + pow(nbr % 10, len), nbr == 0)
)
.takeWhile((_, _, stop) => !stop)
```

which may be considered a bit more readable.

After all of the qualifying iterations are taken, The method returns whether the [`last`][last] of the sequence of tuples has its second element
total equalling the original number.

[log10]: https://www.scala-lang.org/api/2.13.5/scala/math/index.html#log10(x:Double):Double
[iterate]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator$.html#iterate[A](start:A,len:Int)(f:A=%3EA):CC[A]
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[takewhile]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[last]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#last:A
