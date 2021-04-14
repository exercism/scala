object Say {
  private val smallNums =
    Map(1L -> "one", 2 -> "two", 3 -> "three", 4 -> "four",
      5 -> "five", 6 -> "six", 7 -> "seven", 8 -> "eight", 9 -> "nine",
      10 -> "ten", 11 -> "eleven", 12 -> "twelve", 13 -> "thirteen",
      14 -> "fourteen", 15 -> "fifteen", 16 -> "sixteen", 17 -> "seventeen",
      18 -> "eighteen", 19 -> "nineteen")
  private val tens = IndexedSeq("", "", "twenty", "thirty", "forty", "fifty", "sixty",
    "seventy", "eighty", "ninety")
  private val scales = IndexedSeq("hundred", "thousand", "million",  "billion")

  def inEnglish(n: Long): Option[String] = {
    if (!valid(n)) return None
    if (n == 0) return Some("zero")

    val translatedChunks = longToChunks(n).map(n => translateChunk(n))

    val indicies = Range(0, translatedChunks.size).reverse
    val zipped = indicies.zip(translatedChunks).filter{case (_, s: String) => s.nonEmpty}
    val translated = zipped.map {case (i: Int, s: String) => if (i > 0) s + " " + scales(i) else s}
    Some(translated.mkString(" "))
  }

  private def translateChunk(n: Int): String = {
    val hundredsDigit = n / 100
    val remainder = n % 100

    val w1 = if (hundredsDigit > 0) smallNums(hundredsDigit) + " " + scales.head
    else ""

    val w2 = if (remainder > 19) tens(remainder / 10) + smallNums.get(remainder % 10).map("-" + _).getOrElse("")
    else smallNums.getOrElse(remainder, "")

    List(w1, w2).filter(_.nonEmpty).mkString(" ")
  }

  private def valid(n: Long): Boolean = {
    val lowerBound = 0
    val upperBound = 999999999999L
    n >= lowerBound && n <= upperBound
  }

  private def longToChunks(n: Long): List[Int] =
    (Stream.iterate(n)(_ / 1000)takeWhile(_ != 0)map(l => (l % 1000).asInstanceOf[Int])).toList.reverse
}
