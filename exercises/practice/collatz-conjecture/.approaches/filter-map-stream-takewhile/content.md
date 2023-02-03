# `filter()` and `map()` with `Stream()` and `takeWhile()`

```java
object CollatzConjecture {

  def steps(start: Int): Option[Int] = {
    Option(start)
      .filter(_ > 0)
      .map(collatzStream(_).takeWhile(_ != 1).length)
  }
  
  private def collatzStream(n: Int): Stream[Int] =
    Stream.iterate(n)(n => if (n % 2 == 0) n / 2 else (n * 3) + 1)
}
```

This approach starts by creating an [`Option`][option] for the passed in `Int` which creates a `Some` value for it if it is not null.

The `Option` is chained to [`filter()`][filter], which transforms a `Some` value to `None` if it does not satisfy its [lambda][lambda] function.
Here, the lambda checks that the value is greater than `0`.
Since the name of the value is not needed, the [underscore][underscore] `_` is used to avoid naming the variable passed into the lambda.
So, `.filter(_ > 0)` is used instead of something like `.filter(num => num > 0)`.

The `Option` from `filter()` is chained to the [`map()`][map] method.
If the Option is not `None`, then `map()` calls the `collatzStream()` method, passing in the `Option`'s value.
It it is passed as `_` since its name isn't needed to make the call.

The `collatzStream()` method starts by calling the [`Stream.iterate()`][stream] method which repeatedly calls its lambda.
`iterate()` is passed a starting value of the number passed in to the `collatzStream()` method.
That number is continually modified by the result of the lambda.
It does that by using a [ternary][ternary] expression that calculates the new value depending on whether the number is evenly divisible
by `2` or not.
It would go on forever except that the `Stream` is chained to the [`takeWhile()`][takewhile] method whose lambda
keeps asking for an iteration of the `Stream` as long as the number being calculated does not equal `1`.
Once the `takeWhile()` condition returns false, the length of the numbers generated in the `Stream` sequence is returned
from the `map()` method which is then returned from the `steps()` method.

[option]: https://www.baeldung.com/scala/option-type
[filter]: https://www.scala-lang.org/api/2.13.6/scala/Option.html#filter(p:A=%3EBoolean):Option[A]
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[underscore]: https://www.baeldung.com/scala/underscore
[map]: https://www.scala-lang.org/api/2.13.6/scala/Option.html#map[B](f:A=%3EB):Option[B]
[stream]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/Stream$.html#iterate[A](start:A)(f:A=%3EA):scala.collection.immutable.Stream[A]
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[takewhile]: https://www.scala-lang.org/api/2.13.6/scala/collection/immutable/Stream.html#takeWhile(p:A=%3EBoolean):C
