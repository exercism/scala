# Hamming

Calculate the Hamming difference between two DNA strands.

A mutation is simply a mistake that occurs during the creation or
copying of a nucleic acid, in particular DNA. Because nucleic acids are
vital to cellular functions, mutations tend to cause a ripple effect
throughout the cell. Although mutations are technically mistakes, a very
rare mutation may equip the cell with a beneficial attribute. In fact,
the macro effects of evolution are attributable by the accumulated
result of beneficial microscopic mutations over many generations.

The simplest and most common type of nucleic acid mutation is a point
mutation, which replaces one base with another at a single nucleotide.

By counting the number of differences between two homologous DNA strands
taken from different genomes with a common ancestor, we get a measure of
the minimum number of point mutations that could have occurred on the
evolutionary path between the two strands.

This is called the 'Hamming distance'.

It is found by comparing two DNA strands and counting how many of the
nucleotides are different from their equivalent in the other string.

    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^

The Hamming distance between these two DNA strands is 7.

# Implementation notes

The Hamming distance is only defined for sequences of equal length. This means
that based on the definition, each language could deal with getting sequences
of equal length differently.

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

For more detailed info about the Scala track see the [help
page](http://exercism.io/languages/scala).


## Source

The Calculating Point Mutations problem at Rosalind [http://rosalind.info/problems/hamm/](http://rosalind.info/problems/hamm/)

## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
