# `foldLeft()`

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    input
      .foldLeft((List[Char](), 0))((tup, head) =>
        tup._2 match {
          case index if (List(0, 3).contains(index)) => {
            head.isDigit match {
              case true if (head > '1') => (head :: tup._1, index + 1)
              case true if (index == 0 && head == '1') =>
                tup
              case false
                  if (index == 0 && List('+', '(', ' ').contains(head)) =>
                tup
              case false
                  if (index == 3 && List(')', ' ', '.').contains(head)) =>
                tup
              case _ => (Nil, -100)
            }
          }
          case index => {
            head.isDigit match {
              case true => (head :: tup._1, index + 1)
              case false
                  if (index == 6 && List(' ', '-', '.').contains(head)) =>
                tup
              case false if (head == ' ') => tup
              case _                      => (Nil, -100)
            }
          }
        }
      ) match {
      case (output, index) =>
        if (index == 10) Some(output.reverse.mkString) else None
    }
  }
}
```

This approach starts by calling the [`foldLeft()`][foldLeft] method in the input `String`.
It is initialized  with an empty`List` for building the output `String` and `0` for the index, both wrapped in a [tuple][tuple].
The tuple and each character in the input `String` are passed to the [lambda][lambda], where the tuple is passed to the [match][match].
The [pattern matching][pattern-matching] checks if the index is `0` or `3`.

If so, and the `head` character is a digit greater than `1`, then a new tuple is made from the the `head` prepended to the output `List`
with the cons operator (`::`), and with the index added to by `1`.
If the `head` is some other legal character for its index, then the tuple is passed as is.
If the `head` is not a legal character for its index, then a new tuple is made from a `Nil` output `List`
and with the index given a value such that it will reasonably never be valid.

If the `index` is neither `0` or `3`, then the `head` is checked for being a digit.
If it is a digit, then a new tuple is made from the the `head` prepended to the output `List`
with the cons operator (`::`), and with the index added to by `1`.
If the `head` is not a digit but is some other legal character, then the tuple is passed as is.
If the `head` is not a legal character, then a new tuple is made from a `Nil` output `List`
and with the index given a value such that it will reasonably never be valid.

The result of `foldLeft()` is passed to the `match`.
If the index is `10`, then the [`reverse`][reverse] and [`mkString`][mkstring] methods are used on the output `List` to return a `Some` value
from the recursive method.
If the index is not `10`, then `None` is returned from the recursive method.

[foldLeft]: https://www.scala-lang.org/api/2.13.4/scala/collection/StringOps.html#foldLeft[B](z:B)(op:(B,Char)=%3EB):B
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[reverse]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#reverse:List[A]
[mkstring]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#mkString:String
