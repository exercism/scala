# `iterate()` with `takeWhile()` and `last`

```scala
import scala.math.{log10, pow}

object ArmstrongNumbers:

  def isArmstrongNumber(num: Int): Boolean =
    if num < 10 then true
    else
      val len: Double = log10(num).toInt + 1
      Iterator
        .iterate((num, 0.0, false))((nbr, total, _) => (nbr / 10, total + pow(nbr % 10, len), nbr == 0))
        .takeWhile((_, _, stop) => !stop)
        .to(Seq)
        .last
        ._2 == num
```

This approach starts by importing from the packages for what is needed.

The `isArmstrongNumber()` method uses an `if` and `else` expression.
Given that the test input is never less than `1`, if the input `Int` is less than `10`, then `true` is returned.
Otherwise the result of the `else` expression is returned.

The `else` expression starts by using the [`log10()`][log10] method to calculate the number of digits in the input `Int`.

The [`iterate()`][iterate] method is called with a three-element [tuple][tuple] for a start value.
The starting tuple is made of the input `Int`, a `Double` value of `0.0` for the total, and a `Boolean` `false` to stop.
The tuple is passed into the lambda where a new tuple is generated for the next iteration.
The lambda takes the tuple apart into `nbr`, `total` and an ignored flag with Scala 3's [parameter untupling][untupling],
so it doesn't need to write `tup._1` and `tup._2`.

Since `pow()` takes two `Double` arguments,
the number of digits is set to the `Double` type to keep from doing a widening conversion of `Int` to `Double` on every call
to `pow()`.

For an input number of `153`, the iterations would give the following tuples

```
(153,0.0,false), (15,27.0,false), (1,152.0,false), (0,153.0,false), (0,153.0,true)
```

The call to `iterate()` is chained to the [`takeWhile()`][takewhile] method, which uses its lambda to determine
what iterations it will ask for.
In this case it will keep taking iterations until the third element of the tuple, `stop`, is `true`, which is not set until
creating the tuple for the iteration _after_ the first element number has been calculated down to 0.

~~~~exercism/caution
We might be tempted to use a tuple of only two elements and `takeWhile((nbr, _) => nbr > 0)`,
but this will omit the last iteration where the tuple generated is `(0,153.0)`.
The tuple is generated, but since it has a first element of `0`, it is not _taken_.
This is why we need the `Boolean` flag to stop _after_ we've taken the tuple with a first element of `0`.
~~~~

An `Iterator` has no `last`, so the qualifying tuples are collected into a `Seq` with `.to(Seq)`.
The method then returns whether the [`last`][last] of those tuples has its second element, the total, equal to the original number.

[log10]: https://www.scala-lang.org/api/current/scala/math.html
[iterate]: https://www.scala-lang.org/api/current/scala/collection/Iterator$.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[untupling]: https://docs.scala-lang.org/scala3/reference/other-new-features/parameter-untupling.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[takewhile]: https://www.scala-lang.org/api/current/scala/collection/Iterator.html
[last]: https://www.scala-lang.org/api/current/scala/collection/IterableOps.html
