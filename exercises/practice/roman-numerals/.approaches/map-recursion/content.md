# Recursion with a `Map`

```scala
object RomanNumerals {
  val ArabicToRoman = Map(
    1000 -> "M",
    900 -> "CM",
    500 -> "D",
    400 -> "CD",
    100 -> "C",
    90 -> "XC",
    50 -> "L",
    40 -> "XL",
    10 -> "X",
    9 -> "IX",
    5 -> "V",
    4 -> "IV",
    1 -> "I"
  )

  def roman(num: Int): String = romanRecur(num, List[String]())

  @scala.annotation.tailrec
  def romanRecur(num: Int, digits: List[String]): String =
    (num, digits) match {
      case (0, digits) => digits.reverse.mkString
      case (num, digits) => {
        val digit = ArabicToRoman.keys.filter(_ <= num).max
        romanRecur(num - digit, ArabicToRoman(digit) :: digits)
      }
    }
}
```

This approach starts by defining a [`Map`][map] which relates the Arabic numbers to their corresponding
Roman numerals.

The `roman()` method returns the result of calling the recursive method, to which it passes the input Arabic number
and a new empty `List`.

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

The [`match`][match] is used to check the number.

The [pattern matching][pattern-matching] checks if the number has calculated down to `0`.
If so, the `List` is [reverse][reverse]d and converted to a `String` with [`mkString`][mkstring], which is returned from the recursive method.

Otherwise, the [max][max] Arabic number which is less than or equal to the number is [filter][filter]ed from the `Map`.
The recursive method calls itself, passing in the number subtracted by the filtered number, and the `List` prepended by the
Roman numeral associated with the Arabic number in the `Map`.

Note that the operations on the values create new values instead of mutating them, thus supporting [immutability][immutability].

[tuple]: https://docs.scala-lang.org/tour/tuples.html
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[list]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/List.html
[reverse]: https://www.scala-lang.org/api/2.13.10/scala/collection/Seq.html
[mkstring]: https://www.scala-lang.org/api/2.13.10/scala/collection/Seq.html
[max]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#filter(pred:A=%3EBoolean):C
[filter]: https://www.scala-lang.org/api/2.13.10/scala/collection/Iterable.html#filter(pred:A=%3EBoolean):C
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[underscore]: https://www.baeldung.com/scala/underscore
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[head]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#head:A
[tail]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#tail:Repr
