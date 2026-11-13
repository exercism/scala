# `filter()` and `map()` with `LazyList` and `takeWhile()`

```scala
object CollatzConjecture:

  def steps(start: Int): Option[Int] =
    Option(start)
      .filter(_ > 0)
      .map(collatzSequence(_).takeWhile(_ != 1).length)

  private def collatzSequence(n: Int): LazyList[Int] =
    LazyList.iterate(n)(n => if n % 2 == 0 then n / 2 else n * 3 + 1)
```

This approach starts by creating an [`Option`][option] for the passed in `Int`.

The `Option` is chained to [`filter()`][filter], which transforms a `Some` value to `None` if it does not satisfy its [lambda][lambda] function.
Here, the lambda checks that the value is greater than `0`.
Since the name of the value is not needed, the [underscore][underscore] `_` is used to avoid naming the variable passed into the lambda.
So, `.filter(_ > 0)` is used instead of something like `.filter(num => num > 0)`.

The `Option` from `filter()` is chained to the [`map()`][map] method.
If the Option is not `None`, then `map()` calls the `collatzSequence()` method, passing in the `Option`'s value.
It is passed as `_` since its name isn't needed to make the call.

The `collatzSequence()` method calls the [`LazyList.iterate()`][lazylist-iterate] method, which repeatedly calls its lambda.
`iterate()` is passed a starting value of the number passed in to the `collatzSequence()` method.
That number is continually modified by the result of the lambda.
It does that by using an [`if` expression][if-expression] that calculates the new value depending on whether the number is evenly divisible
by `2` or not.

A [`LazyList`][lazylist] computes its elements only when they are needed, so it can describe an endless sequence.
It would go on forever except that the `LazyList` is chained to the [`takeWhile()`][takewhile] method whose lambda
keeps asking for the next number as long as the number being calculated does not equal `1`.
Once the `takeWhile()` condition returns false, the length of the numbers generated in the sequence is returned
from the `map()` method which is then returned from the `steps()` method.

An [`Iterator`][iterator] works the same way here: `Iterator.iterate(n)(...)` would also produce the numbers one at a time.
Unlike a `LazyList`, an `Iterator` does not remember the numbers it has already produced, but this solution doesn't need them.

[option]: https://www.baeldung.com/scala/option-type
[filter]: https://www.scala-lang.org/api/current/scala/Option.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[underscore]: https://www.baeldung.com/scala/underscore
[map]: https://www.scala-lang.org/api/current/scala/Option.html
[lazylist]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList.html
[lazylist-iterate]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList$.html
[if-expression]: https://docs.scala-lang.org/scala3/book/control-structures.html#the-ifthenelse-construct
[takewhile]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList.html
[iterator]: https://www.scala-lang.org/api/current/scala/collection/Iterator.html
