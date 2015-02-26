object Prime {
  // The stream will cache calculated primes. This is nice for performance,
  // but not so nice for memory utilization.
  private lazy val primes: Stream[BigInt] =
    Stream.cons(BigInt(2), primes.map(b => new BigInt(b.bigInteger.nextProbablePrime)))

  def nth(n: Int): Int = primes.drop(n - 1).head.toInt
}
