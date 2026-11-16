# Introduction

The [Sieve of Eratosthenes][sieve] finds the primes by crossing out the numbers that are not prime.
It never asks whether one number divides another.
It takes each prime in turn and marks its multiples, which it reaches by adding the prime again and again.
The numbers that are still unmarked at the end are the primes.

Two observations keep the work down, and all the approaches here use them:

- The multiples of a prime `p` can be marked starting from `p * p`.
  A smaller multiple, such as `2 * p` or `3 * p`, has a factor smaller than `p`, so it was marked earlier.
- Only the primes up to the square root of the limit have multiples left to mark, because for a larger prime `p * p` is already above the limit.

The approaches differ in where the marks are kept:
in a mutable `Array`, in an immutable `Set`, or in a `Map` that travels along an endless `LazyList` of primes.

## Approach: mutable `Array`

```scala
object Sieve:
  def primes(limit: Int): List[Int] =
    val composite = Array.fill(limit + 1)(false)
    for
      n        <- 2 to math.sqrt(limit).toInt
      if !composite(n)
      multiple <- n * n to limit by n
    do composite(multiple) = true
    (2 to limit).filterNot(composite(_)).toList
```

For more information, check the [mutable `Array` approach][approach-mutable-array].

## Approach: immutable `Set` with `foldLeft()`

```scala
object Sieve:
  def primes(limit: Int): List[Int] =
    val candidates = 2 to limit
    val unmarked   = (2 to math.sqrt(limit).toInt).foldLeft(candidates.toSet): (numbers, n) =>
      if numbers(n) then numbers -- (n * n to limit by n) else numbers
    candidates.filter(unmarked).toList
```

For more information, check the [immutable `Set` with `foldLeft()` approach][approach-immutable-set].

## Approach: `LazyList`

```scala
object Sieve:
  def primes(limit: Int): List[Int] =
    sieve(2, Map.empty).takeWhile(_ <= limit).map(_.toInt).toList

  private def sieve(n: Long, composites: Map[Long, List[Long]]): LazyList[Long] =
    composites.get(n) match
      case None         => n #:: sieve(n + 1, composites + (n * n -> List(n)))
      case Some(primes) =>
        val moved = primes.foldLeft(composites - n): (marked, p) =>
          marked.updatedWith(n + p)(found => Some(p :: found.getOrElse(Nil)))
        sieve(n + 1, moved)
```

For more information, check the [`LazyList` approach][approach-lazylist].

## A sieve that is not a sieve

There is a well-known short solution that is often presented as the Sieve of Eratosthenes:

```scala
def sieve(numbers: LazyList[Int]): LazyList[Int] =
  numbers.head #:: sieve(numbers.tail.filter(_ % numbers.head != 0))
```

Called with `LazyList.from(2)`, it gives the primes, and it passes the tests.
But it is not the sieve.
It takes the first number as a prime and filters the rest by testing each of them for divisibility with `%`.
So every number is divided by the primes below it, one after another, until one of them divides it or none is left: this is trial division.
The sieve does no division at all, and it touches a number at most once for each of its prime factors.

The difference is large, and it grows with the limit.
Melissa O'Neill describes it in the paper [The Genuine Sieve of Eratosthenes][oneill], which calls this solution the "unfaithful sieve".
The instructions of this exercise ask for the real algorithm, so it is not one of the approaches.

## Which approach to use?

Since benchmarking is currently outside the scope of this document,
the choice between the approaches can be made by perceived readability.

The mutable `Array` is the sieve as it is usually described: a row of numbers, and marks on some of them.
The array is created inside the method and never leaves it, so the method is still a pure function for its callers.
It is the approach to prefer when the limit is known.

The immutable `Set` is the same algorithm without any mutation.
It is shorter, but every step builds a new set, which is more work than setting a mark in an array.

The `LazyList` approach does not need a limit to produce primes, which is its reason to exist.
When the limit is given, as it is in this exercise, the other two approaches are simpler.

[sieve]: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
[oneill]: https://www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
[approach-mutable-array]: https://exercism.org/tracks/scala/exercises/sieve/approaches/mutable-array
[approach-immutable-set]: https://exercism.org/tracks/scala/exercises/sieve/approaches/immutable-set
[approach-lazylist]: https://exercism.org/tracks/scala/exercises/sieve/approaches/lazylist
