# Hamming

Calculate the Hamming Distance between two DNA strands.

Your body is made up of cells that contain DNA. Those cells regularly wear out and need replacing, which they achieve by dividing into daughter cells. In fact, the average human body experiences about 10 quadrillion cell divisions in a lifetime!

When cells divide, their DNA replicates too. Sometimes during this process mistakes happen and single pieces of DNA get encoded with the incorrect information. If we compare two strands of DNA and count the differences between them we can see how many mistakes occurred. This is known as the "Hamming Distance".

We read DNA using the letters C,A,G and T. Two strands might look like this:

    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^

They have 7 differences, and therefore the Hamming Distance is 7.

The Hamming Distance is useful for lots of things in science, not just biology, so it's a nice phrase to be familiar with :)

# Implementation notes

The Hamming distance is only defined for sequences of equal length, so
an attempt to calculate it between sequences of different lengths should
not work. The general handling of this situation (e.g., raising an
exception vs returning a special value) may differ between languages.

## Hints
`Option` is used to indicate a computation that may possibly have no useful result
(for example due to an error or invalid input).
If you are unfamiliar with `Option` you may read [this tutorial](http://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html).
`Option` is a so-called [Monad](https://en.wikipedia.org/wiki/Monad_(functional_programming)) which covers a "computational aspect", in this case possible absence of a value.
Proper use of Monads can result in very concise yet elegant
and readable code. Improper use can easily result in the contrary.
Watch [this video](https://www.youtube.com/watch?v=Mw_Jnn_Y5iA) to learn more.
#### Common pitfalls that you should avoid
There are a few rules of thumbs for `Option`:
1. If you don't need it don't use it. Instead of
```scala
def add1(x: Int): Option[Int] = Some(x + 1)
```
better have
```scala
def add1(x: Int): Int = x + 1
```
(there is `Option.map` to apply such simple functions,
so you don't have to clutter them with `Option`).
2. Don't "unwrap" if you don't really need to.
Often there are built-in functions for your purpose. Indicators of premature
unwrapping are `isDefined/isEmpty` or pattern matching. Instead of
```scala
val x: Option[Int] = ...

if (x.isDefined) x.get + 1 else 0
// or
x match {
  case Some(n) => n + 1
  case None => 0
}
```
better have
```scala
x map (_ + 1) getOrElse 0
```
3. Monads can be used inside a for-comprehension FTW.
This is advisable when you want to "compose" several `Option` instances. Instead of
```scala
val xo: Option[Int] = ...
val yo: Option[Int] = ...
val zo: Option[Int] = ...

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

Please see the [learning](https://exercism.io/tracks/scala/learning) and 
[installation](https://exercism.io/tracks/scala/installation) pages if you need any help.


## Source

The Calculating Point Mutations problem at Rosalind [http://rosalind.info/problems/hamm/](http://rosalind.info/problems/hamm/)

## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
