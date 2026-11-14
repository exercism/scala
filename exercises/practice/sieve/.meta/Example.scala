object Sieve:
  def primes(limit: Int): List[Int] =
    val composite = Array.fill(limit + 1)(false)
    for
      n        <- 2 to math.sqrt(limit).toInt
      if !composite(n)
      multiple <- n * n to limit by n
    do composite(multiple) = true
    (2 to limit).filterNot(composite(_)).toList
