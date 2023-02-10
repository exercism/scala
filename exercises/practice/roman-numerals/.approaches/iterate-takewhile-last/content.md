# `iterate()` with `takeWhile()` and `last`

```scala
object RomanNumerals {

  val ArabicNum = Vector(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
  val RomanNum = Vector(
    "M",
    "CM",
    "D",
    "CD",
    "C",
    "XC",
    "L",
    "XL",
    "X",
    "IX",
    "V",
    "IV",
    "I"
  )

  def roman(num: Int): String = {
    Iterator
      .iterate((num, 0, List[String]()))(tup =>
        tup match {
          case (num, idx, digits) if num >= ArabicNum(idx) =>
            (num - ArabicNum(idx), idx, RomanNum(idx) :: digits)
          case (num, idx, digits) =>
            (num, idx + 1, digits)
        }
      )
      .takeWhile(tup => tup._2 < 13)
      .to(Seq)
      .last match {
      case (_, _, digits) => digits.reverse.mkString
    }
  }
}
```

This approach starts by defining two [Vector][vector]s, one which contains the Arabic numbers and the other their corresponding
Roman numerals.

The [`iterate()`][iterate] method is initialized with the input Arabic number, the value `0` for the index, and a new
empty [`List`][list], all wrapped in a [tuple][tuple].
The tuple is passed to the [lambda][lambda] which uses a [`match`][match] to destructure the lambda.

The [pattern matching][pattern-matching] is used to check if the number is greater than or equal to the Arabic number in the `Vector` 
at the index.
If so, a new tuple is created with the number subtracted by the Arabic number, the index as is, and the `List` prepended by the Roman numeral
in the `Vector` at that index.

Otherwise, if the number is less than the the Arabic number at the index, then a new tuple is created with the number as is, the index added to by `1`,
and the `List` as is.

The [`takewhile()`][takeWhile] will keep calling the iterator while the index is less than `13`.

After all of the values in the Arabic `Vector` have been checked against the number, the tuples are collected into a [`Seq`][seq]
and the [`last`][last] tuple is destructured by a `match` that [reverse][reverse]s the `List` and converts it to a `String`
with [`mkString`][mkstring], which is returned from the `roman()` method.

Note that the operations on the values create new values instead of mutating them, thus supporting [immutability][immutability].

[iterate]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator$.html#iterate[T](start:T)(f:T=%3ET):Iterator[T]
[list]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/List.html
[takeWhile]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterator.html#takeWhile(p:A=%3EBoolean):Iterator[A]
[last]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#last:A
[vector]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Vector.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[seq]: https://www.scala-lang.org/api/2.13.10/scala/collection/Seq.html
[reverse]: https://www.scala-lang.org/api/2.13.10/scala/collection/Seq.html
[mkstring]: https://www.scala-lang.org/api/2.13.10/scala/collection/Seq.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
