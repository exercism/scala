# Nucleotide Count

Given a single stranded DNA string, compute how many times each nucleotide occurs in the string.

The genetic language of every living thing on the planet is DNA.
DNA is a large molecule that is built from an extremely long sequence of individual elements called nucleotides.
4 types exist in DNA and these differ only slightly and can be represented as the following symbols: 'A' for adenine, 'C' for cytosine, 'G' for guanine, and 'T' thymine.

Here is an analogy:
- twigs are to birds nests as
- nucleotides are to DNA as
- legos are to lego houses as
- words are to sentences as...

## Hints
A common use of `Either` is to indicate a computation that may possibly result in an error
(if the actual error is of no interest then the simpler `Option` type might be a better choice).
In the absence of an error the result is usually a `Right` (mnemonic: the "right" value)
whereas an error is a `Left`, for example a `Left[String]` containing an error message.
Note that in Scala 2.12 `Either` is right-biased by default, so it works as expected for operations like `filter`, `map`, `flatMap` or in a for-comprehension.
If you are unfamiliar with `Either` you may read [this tutorial](http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html). But be aware that this tutorial is about Scala versions prior to 2.12. For Scala 2.12 you can safely ignore `RightProjection` and omit `.right`.
`Either` is a so-called [Monad](https://en.wikipedia.org/wiki/Monad_(functional_programming)) which covers a "computational aspect",
in this case error handling.
Proper use of Monads can result in very concise yet elegant
and readable code. Improper use can easily result in the contrary.
Watch [this video](https://www.youtube.com/watch?v=Mw_Jnn_Y5iA) to learn more.
#### Common pitfalls that you should avoid
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

if (x.isRight) x.right.get + 1 else x.left.get
// or
x match {
  case Right(n) => n + 1
  case Left(s) => s
}
```
better have
```scala
x fold (identity, _ + 1)
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
for {
  x <- xo
  y <- yo
  z <- zo
} yield x + y + z
```


The Scala exercises assume an SBT project scheme. The exercise solution source
should be placed within the exercise directory/src/main/scala. The exercise
unit tests can be found within the exercise directory/src/test/scala.

To run the tests simply run the command `sbt test` in the exercise directory.

For more detailed info about the Scala track see the [help
page](http://exercism.io/languages/scala).


## Source

The Calculating DNA Nucleotides_problem at Rosalind [http://rosalind.info/problems/dna/](http://rosalind.info/problems/dna/)

## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
