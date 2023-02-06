# `toString() with `map() and `sum()`

```scala
object ArmstrongNumbers {
  def isArmstrongNumber(num: Int): Boolean = {
    val text = num.toString()
    val len: Double = text.length()
    text.map(chr => Math.pow(chr.asDigit, len)).sum == num
  }
}
```

This approach starts be calling the [`toString()`][tostring] method on the input `Int`.

After the length is taken of the `String`, the [`map()`][map] method is called in the `String`.
This passes each character in the `String` to a [lambda][lambda] which uses [`asDigit`][asdigit] to convert the character to
an `Int` and passes it as the first argument to the [`pow()`][pow] method.
The second argument is the length of the `String`.

Since `pow()` take two `Double` arguments, 
the length of the `String` is set to the `Double` type to keep from doing a widening conversion of `Int` to `Double` on every call
to `pow()`.

After `map()` is done iterating all the characters, the [`sum()`][sum] method is called to total all the calls to `pow()`.
The method returns whether that total is equal to the original number.

[tostring]: https://www.scala-lang.org/api/2.13.10/scala/Double.html#toString():String
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/StringOps.html#map[B](f:Char=%3EB):IndexedSeq[B]
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[asdigit]: https://www.scala-lang.org/api/2.13.10/scala/Char.html#asDigit:Int
[pow]: https://www.scala-lang.org/api/2.13.5/scala/math/index.html#pow(x:Double,y:Double):Double
[sum]: https://www.scala-lang.org/api/2.13.10/scala/collection/IterableOnce.html#sum(implicitnum:scala.math.Numeric[A]):A
