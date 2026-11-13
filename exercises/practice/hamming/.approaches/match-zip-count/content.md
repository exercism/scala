# `match` with `zip()` and `count()`

```scala
object Hamming:
  def distance(strand1: String, strand2: String): Option[Int] =
    strand1.length == strand2.length match
      case true  => Some(strand1.zip(strand2).count((a, b) => a != b))
      case false => None
```

This approach starts by using a [`match`][match] expression on whether the lengths of the strands are equal.
If the lengths are not equal, the `false` case gives [`None`][none].

Otherwise, the two strands are [`zip`][zip]ped together into [tuples][tuple] of a [`Char`][char] from each of the strands.
So the first tuple has the first `Char` from each of the strands, the second tuple has the second `Char` from each of the strands,
and so on.
Each tuple is passed to the [`count()`][count] method.
The [lambda][lambda] in the `count()` method compares the `Char`s in the tuple and adds to the count only when the two `Char`s are different from each other.
It takes the tuple's two elements as separate parameters `a` and `b`, which Scala 3 allows by [parameter untupling][untupling].

When the `zip()` method has iterated all of the tuples, the result of the count is wrapped in a [`Some`][some].
Since the `match` expression is the body of the method, the result from either case is returned without needing the `return` keyword.

[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[none]: https://www.scala-lang.org/api/current/scala/None$.html
[some]: https://www.scala-lang.org/api/current/scala/Some.html
[zip]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[count]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[untupling]: https://docs.scala-lang.org/scala3/reference/other-new-features/parameter-untupling.html
