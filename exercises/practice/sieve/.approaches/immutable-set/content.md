# Immutable `Set` with `foldLeft()`

```scala
object Sieve:
  def primes(limit: Int): List[Int] =
    val candidates = 2 to limit
    val unmarked   = (2 to math.sqrt(limit).toInt).foldLeft(candidates.toSet): (numbers, n) =>
      if numbers(n) then numbers -- (n * n to limit by n) else numbers
    candidates.filter(unmarked).toList
```

This approach is the sieve without mutation.
In place of marks that are set, it has a [`Set`][set] of numbers from which the composite ones are removed.

`candidates` is the [`Range`][range] of the numbers from `2` through `limit`.
`candidates.toSet` is the set to start with: every candidate is in it, as none is known to be composite yet.

The [`foldLeft()`][range] method goes through the numbers from `2` up to the square root of the limit.
A larger prime has no multiples left to remove, because its square is already above the limit.
`foldLeft()` passes the set as it is so far and the next number to its [lambda][lambda], and the result of the lambda becomes the set for the next number.
The lambda is passed after a colon and on an indented line, which Scala 3 allows instead of [enclosing it in braces][optional-braces].

`numbers(n)` is a short way to write `numbers.contains(n)`.
If the number is still in the set, it is a prime, and its multiples are removed.
`n * n to limit by n` is the range of those multiples: it starts at `n * n`, because the smaller multiples of `n` have a smaller factor and are gone already, and `by n` makes it advance in steps of `n`.
The `--` operator gives a new set without the elements of the range; the set it is called on does not change.
If the number is not in the set, it is composite, and the set is passed on as it is.

The result of `foldLeft()` is the set of the primes.
A `Set` has no order, so the last line does not turn it into a list.
It goes through the candidates, which are in ascending order, and keeps the ones that are in the set.
A `Set` can be used as a function that tells whether a value is one of its elements, which is why it can be passed to [`filter()`][range].

[set]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
[lambda]: https://docs.scala-lang.org/scala3/book/fun-anonymous-functions.html
[optional-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
