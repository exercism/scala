case class PalindromeProducts(minFactor: Int, maxFactor: Int):
  type Palindrome = (Int, Set[(Int, Int)])

  lazy val smallest: Option[Palindrome] = palindromes.minByOption(_._1)
  lazy val largest: Option[Palindrome]  = palindromes.maxByOption(_._1)

  private lazy val palindromes: Map[Int, Set[(Int, Int)]] =
    val products =
      for
        a      <- minFactor to maxFactor
        b      <- a to maxFactor
        product = a * b
        if isPalindrome(product)
      yield product -> (a, b)
    products.groupMap(_._1)(_._2).map((product, factors) => product -> factors.toSet)

  private def isPalindrome(n: Int): Boolean =
    val digits = n.toString
    digits == digits.reverse
