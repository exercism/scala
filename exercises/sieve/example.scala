import scala.collection.mutable

object Sieve {
  def primes(upperBound: Int): List[Int] = {
    // Start with set of all "possible" values that could be prime
    val primes = mutable.HashSet.empty ++ (2 to upperBound)

    // Remove multiples of a possiblePrime from the primes set.
    def checkPrime(possiblePrime: Int) {
      if (primes contains possiblePrime) {
        // remove multiples of possiblePrime from set
        val possibleSquared = possiblePrime * possiblePrime
        for (i <- possibleSquared to upperBound by possiblePrime) {
          primes.remove(i)
        }
      }
    }

    val sqrtUpper = math.sqrt(upperBound).toInt
    for (possible <- 2 to sqrtUpper) {
      checkPrime(possible)
    }

    // Return the remaining items in the set as a sorted list - these are the primes
    primes.toList.sorted
  }
}
