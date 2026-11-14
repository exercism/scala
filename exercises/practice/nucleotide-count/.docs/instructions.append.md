# Hints
A common use of `Either` is to indicate a computation that may possibly result in an error
(if the actual error is of no interest then the simpler `Option` type might be a better choice).
In the absence of an error the result is usually a `Right` (mnemonic: the "right" value)
whereas an error is a `Left`, for example a `Left[String]` containing an error message.
`Either` is right-biased, so operations like `map`, `flatMap` or a for-comprehension work on the `Right` value.
If you are unfamiliar with `Either` you may read [Functional Error Handling](https://docs.scala-lang.org/scala3/book/fp-functional-error-handling.html) in the Scala 3 book.
`Either` is a so-called [Monad](https://en.wikipedia.org/wiki/Monad_(functional_programming)) which covers a "computational aspect",
in this case error handling.
Proper use of Monads can result in very concise yet elegant
and readable code. Improper use can easily result in the contrary.
Watch [this video](https://www.youtube.com/watch?v=Mw_Jnn_Y5iA) to learn more.
## Common pitfalls that you should avoid
There are a few rules of thumbs for `Either`:
1. If you don't need it don't use it. Instead of
```scala
def add1(x: Int): Either[String, Int] = Right(x + 1)
```
better have
```scala
def add1(x: Int): Int = x + 1
```
(there is `Either.map` to apply such simple functions,
so you don't have to clutter them with `Either`).
2. Don't "unwrap" if you don't really need to.
Often there are built-in functions for your purpose. Indicators of premature
unwrapping are `isLeft/isRight` or pattern matching. For example, instead of
```scala
val x: Either[String, Int] = ...

if x.isRight then x.toOption.get + 1 else x.swap.toOption.get
// or
x match
  case Right(n) => n + 1
  case Left(s)  => s
```
better have
```scala
x.fold(identity, _ + 1)
```
3. Monads can be used inside a for-comprehension FTW.
This is advisable when you want to "compose" several `Either` instances. Instead of
```scala
val xo: Either[String, Int] = ...
val yo: Either[String, Int] = ...
val zo: Either[String, Int] = ...

xo.flatMap(x =>
  yo.flatMap(y =>
    zo.map(z =>
      x + y + z)))
```
better have
```scala
for
  x <- xo
  y <- yo
  z <- zo
yield x + y + z
```
