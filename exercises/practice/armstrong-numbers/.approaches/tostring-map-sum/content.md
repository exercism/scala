# `toString()` with `map()` and `sum()`

```scala
object ArmstrongNumbers:
  def isArmstrongNumber(num: Int): Boolean =
    val text        = num.toString()
    val len: Double = text.length()
    text.map(chr => Math.pow(chr.asDigit, len)).sum == num
```

This approach starts by calling the [`toString()`][tostring] method on the input `Int`.

After the length is taken of the `String`, the [`map()`][map] method is called on the `String`.
This passes each character in the `String` to a [lambda][lambda] which uses [`asDigit`][asdigit] to convert the character to
an `Int` and passes it as the first argument to the [`pow()`][pow] method.
The second argument is the length of the `String`.

Since `pow()` takes two `Double` arguments,
the length of the `String` is set to the `Double` type to keep from doing a widening conversion of `Int` to `Double` on every call
to `pow()`.

After `map()` is done iterating all the characters, the [`sum()`][sum] method is called to total all the calls to `pow()`.
The method returns whether that total is equal to the original number.

[tostring]: https://www.scala-lang.org/api/current/scala/Int.html
[map]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[asdigit]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[pow]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html
[sum]: https://www.scala-lang.org/api/current/scala/collection/IterableOnceOps.html
