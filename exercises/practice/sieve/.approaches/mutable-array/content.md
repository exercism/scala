# Mutable `Array`

```scala
object Sieve:
  def primes(limit: Int): List[Int] =
    require(limit < Int.MaxValue, "limit + 1 must fit in an Int")
    val composite = Array.fill(limit + 1)(false)
    for
      n        <- 2 to math.sqrt(limit).toInt
      if !composite(n)
      multiple <- n * n to limit by n
    do composite(multiple) = true
    (2 to limit).filterNot(composite(_)).toList
```

This approach keeps a mark for every number in an [`Array`][array] of `Boolean`s.

[`Array.fill()`][array-companion] creates an array of the given size with the same value in every position.
The size is `limit + 1`, so that the number `n` has its mark at the index `n`, and the last index is `limit`.
At `Int.MaxValue`, adding one would overflow, so `require` rejects that limit before the array is allocated.
Every mark starts as `false`: no number is known to be composite yet.

The marking is done by a [`for` loop][for] with two generators and a guard:

- `n <- 2 to math.sqrt(limit).toInt` goes through the numbers from `2` up to the square root of the limit.
  A larger prime has no multiples left to mark, because its square is already above the limit.
- `if !composite(n)` lets only the unmarked numbers through.
  A number that is still unmarked when the loop reaches it is a prime.
- `multiple <- n * n to limit by n` goes through the multiples of that prime.
  `by n` makes the range advance in steps of `n`, so the multiples are found by addition and nothing is divided.
  The range starts at `n * n`, because the smaller multiples of `n` have a smaller factor and are marked already.

The body of the loop follows `do`.
It sets the mark of the multiple to `true`; `composite(multiple) = true` is how an element of an array is assigned.

When the loop has finished, the unmarked numbers from `2` through `limit` are the primes.
The [`filterNot()`][range] method keeps the numbers for which `composite(_)` is `false`, and `toList` turns the result into a `List`.

For a limit below `2` both ranges are empty, so the result is an empty list.

~~~~exercism/note
An `Array` is mutable, which Scala code usually avoids.
Here the array is created inside the method and is not visible outside it.
A caller cannot tell that anything was mutated: the same limit always gives the same list.
~~~~

[array]: https://www.scala-lang.org/api/current/scala/Array.html
[array-companion]: https://www.scala-lang.org/api/current/scala/Array$.html
[for]: https://docs.scala-lang.org/scala3/book/control-structures.html#for-loops
[range]: https://www.scala-lang.org/api/current/scala/collection/immutable/Range.html
