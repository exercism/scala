# `iterate()` with `takeWhile()` and `last`

```scala
object RomanNumerals:

  private val ArabicNum = Vector(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
  private val RomanNum  = Vector("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

  def roman(num: Int): String =
    val (_, _, numerals) = Iterator
      .iterate((num, 0, List.empty[String])):
        case (num, idx, digits) if num >= ArabicNum(idx) => (num - ArabicNum(idx), idx, RomanNum(idx) :: digits)
        case (num, idx, digits)                          => (num, idx + 1, digits)
      .takeWhile((_, idx, _) => idx < 13)
      .to(Seq)
      .last
    numerals.reverse.mkString
```

This approach starts by defining two private [Vector][vector]s, one which contains the Arabic numbers and the other their corresponding
Roman numerals.

The [`iterate()`][iterate] method is initialized with the input Arabic number, the value `0` for the index, and a new
empty [`List`][list], all wrapped in a [tuple][tuple].
Each tuple is passed to the [lambda][lambda], which follows a [colon instead of being wrapped in braces][fewer-braces].
The lambda is made only of `case` clauses, so it destructures the tuple with [pattern matching][pattern-matching].

The first `case` checks if the number is greater than or equal to the Arabic number in the `Vector` at the index.
If so, a new tuple is created with the number subtracted by the Arabic number, the index as is, and the `List` prepended by the Roman numeral
in the `Vector` at that index.

Otherwise, if the number is less than the Arabic number at the index, then a new tuple is created with the number as is, the index added to by `1`,
and the `List` as is.

The [`takeWhile()`][takeWhile] will keep taking tuples from the iterator while the index is less than `13`.
Its lambda takes the three elements of the tuple as separate [parameters][parameter-untupling] and ignores the first and last with `_`.

After all of the values in the Arabic `Vector` have been checked against the number, the tuples are collected into a [`Seq`][seq]
and the [`last`][last] tuple is destructured into `numerals`.
That `List` is [reverse][reverse]d and converted to a `String` with [`mkString`][mkstring], which is returned from the `roman()` method.

Note that the operations on the values create new values instead of mutating them, thus supporting [immutability][immutability].

[iterate]: https://www.scala-lang.org/api/current/scala/collection/Iterator$.html
[takeWhile]: https://www.scala-lang.org/api/current/scala/collection/Iterator.html
[last]: https://www.scala-lang.org/api/current/scala/collection/Seq.html
[seq]: https://www.scala-lang.org/api/current/scala/collection/Seq.html
[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[parameter-untupling]: https://docs.scala-lang.org/scala3/reference/other-new-features/parameter-untupling.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[reverse]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
