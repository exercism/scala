object NthPrime:
  // The LazyList caches the primes it has computed. This is nice for performance,
  // but not so nice for memory utilization.
  private lazy val primes: LazyList[BigInt] =
    LazyList.iterate(BigInt(2))(p => BigInt(p.bigInteger.nextProbablePrime))

  def prime(n: Int): Option[Int] =
    if n < 1 then None
    else Some(primes(n - 1).toInt)
