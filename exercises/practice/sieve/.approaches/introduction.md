# Introduction

The [Sieve of Eratosthenes][sieve] finds the primes by crossing out the numbers that are not prime.
It never asks whether one number divides another.
It takes each prime in turn and marks its multiples, which it reaches by adding the prime again and again.
The numbers that are still unmarked at the end are the primes.

All three approaches start marking the multiples of a prime `p` at `p * p`.
Any composite multiple below `p * p` has a smaller prime factor and was marked earlier.

The `Array` and `Set` approaches know the limit in advance.
They only need to mark multiples of primes up to its square root, because larger primes have squares above the limit.
The `LazyList` generates primes on demand and keeps track of upcoming multiples in a `Map`.
For example, when asked for primes up to `10`, it schedules `25` as the first multiple of `5` to mark.

## Approach: mutable `Array`

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
When `n` is prime, the fold removes its multiples from the set.
When `n` is composite, it reuses the same set.

The `LazyList` approach does not need a limit to produce primes, which is its reason to exist.
When the limit is given, as it is in this exercise, the other two approaches are simpler.

[sieve]: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
[oneill]: https://www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
[approach-mutable-array]: https://exercism.org/tracks/scala/exercises/sieve/approaches/mutable-array
[approach-immutable-set]: https://exercism.org/tracks/scala/exercises/sieve/approaches/immutable-set
[approach-lazylist]: https://exercism.org/tracks/scala/exercises/sieve/approaches/lazylist
