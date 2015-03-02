
case class PalindromeProducts(minFactor: Int, maxFactor: Int) {

  lazy val (smallest: (Int, Set[(Int, Int)]),
            largest: (Int, Set[(Int, Int)])) = {
    val palindromes = for (a <- Range(minFactor, maxFactor + 1);
         b <- Range(a, maxFactor + 1) if isPalindrome(a * b)) yield (a * b, (a, b))
    val mapped = palindromes.groupBy(_._1).map{case(k, v) => (k, v.map(_._2).toSet)}
    (mapped.minBy(_._1), mapped.maxBy(_._1))
  }

  private def isPalindrome(i: Int) = i.toString == i.toString.reverse
}
