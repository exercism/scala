# `LazyList`

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

The other approaches need the limit before they start, because they mark numbers up to it.
This one produces the primes one after another, without an end.
`primes()` takes the ones it needs: [`takeWhile()`][lazylist] stops at the first prime above the limit.

A [`LazyList`][lazylist] makes that possible.
Its elements are calculated only when they are asked for.
The `#::` operator puts an element in front of a `LazyList`, and what is written after it is not evaluated until the next element is needed.
So `sieve()` can call itself without running forever.

## Marking without a limit

With no limit, the multiples of a prime cannot all be marked in advance.
So for each prime only its next multiple is marked, and the mark is moved on when the sieve gets there.

The marks are kept in `composites`, a [`Map`][map] from a composite number that lies ahead to the primes whose next multiple it is.
`sieve()` looks at the number `n`, and [`get()`][map] gives an [`Option`][option]:

- `None` means that `n` has no mark, so it is a prime.
  It becomes the next element of the list.
  The rest of the list is the sieve from `n + 1` on, with the first multiple of `n` that needs a mark, `n * n`, added to the map.
- `Some(primes)` means that `n` is composite, and `primes` are the primes that reached it.
  The mark at `n` is removed with `composites - n`, and each of those primes `p` is moved to its next multiple, `n + p`.
  That is the addition by which the sieve finds its multiples.
  [`updatedWith()`][map] adds `p` to the list that is already at `n + p`, or to an empty list if there is none.
  The sieve then goes on from `n + 1`; nothing is added to the list, as `n` is not a prime.

For the primes up to `10` it works like this.
`2` is not in the map, so it is a prime, and `4` is marked with `2`.
`3` is a prime, and `9` is marked with `3`.
`4` is in the map, so it is composite, and its `2` moves on to `6`.
`5` is a prime, and `25` is marked.
`6` is composite, and its `2` moves on to `8`.
`7` is a prime, `8` is composite, and its `2` moves on to `10`.
`9` is composite, and its `3` moves on to `12`.
`10` is composite.

## Why `Long`?

The first multiple that is marked for a prime is its square.
The square of a prime above `46340` does not fit in an `Int`, and the list has no limit that would keep the primes below that.
So the sieve counts in `Long`, and `primes()` turns the numbers it takes back into `Int`s with `map(_.toInt)`.

[lazylist]: https://www.scala-lang.org/api/current/scala/collection/immutable/LazyList.html
[map]: https://www.scala-lang.org/api/current/scala/collection/immutable/Map.html
[option]: https://www.scala-lang.org/api/current/scala/Option.html
