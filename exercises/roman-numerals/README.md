# Roman Numerals

Write a function to convert from normal numbers to Roman Numerals.

The Romans were a clever bunch. They conquered most of Europe and ruled
it for hundreds of years. They invented concrete and straight roads and
even bikinis. One thing they never discovered though was the number
zero. This made writing and dating extensive histories of their exploits
slightly more challenging, but the system of numbers they came up with
is still in use today. For example the BBC uses Roman numerals to date
their programmes.

The Romans wrote numbers using letters - I, V, X, L, C, D, M. (notice
these letters have lots of straight lines and are hence easy to hack
into stone tablets).

```text
 1  => I
10  => X
 7  => VII
```

There is no need to be able to convert numbers larger than about 3000.
(The Romans themselves didn't tend to go any higher)

Wikipedia says: Modern Roman numerals ... are written by expressing each
digit separately starting with the left most digit and skipping any
digit with a value of zero.

To see this in practice, consider the example of 1990.

In Roman numerals 1990 is MCMXC:

1000=M
900=CM
90=XC

2008 is written as MMVIII:

2000=MM
8=VIII

See also: http://www.novaroma.org/via_romana/numbers.html

## Hints
For something a little different you might also try a solution with an `unfold` function.
You are probably already familiar with `foldLeft/Right`: "map" a whole collection into something else (usually a non-collection).
`unfoldLeft/Right` are the "inverse" operations: "map" something (usually a non-collection) into a collection.
So `unfold`ing is a logical addition to and part of the FP standard repertoire.

This exercise can be seen as a case for `unfold`ing: "map" an `Int` into a `String` (which is of course implicitly a `Seq[Char]`).

Unfortunately `unfoldLeft/Right` is not included in Scala's collection library.
But you can take the implementation from [here](http://daily-scala.blogspot.de/2009/09/unfoldleft-and-right.html).


The Scala exercises assume an SBT project scheme. The exercise solution source
should be placed within the exercise directory/src/main/scala. The exercise
unit tests can be found within the exercise directory/src/test/scala.

To run the tests simply run the command `sbt test` in the exercise directory.

For more detailed info about the Scala track see the [help
page](http://exercism.io/languages/scala).


## Source

The Roman Numeral Kata [http://codingdojo.org/cgi-bin/index.pl?KataRomanNumerals](http://codingdojo.org/cgi-bin/index.pl?KataRomanNumerals)

## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
