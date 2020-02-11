object NthPrime {
  // The stream will cache calculated primes. This is nice for performance,
  // but not so nice for memory utilization.
  private lazy val primes: Stream[BigInt] =
    Stream.cons(BigInt(2), primes.map(b => new BigInt(b.bigInteger.nextProbablePrime)))

  def prime(n: Int): Option[Int] =
    if (n < 1) None
    else Some(primes.drop(n - 1).head.toInt)
}
