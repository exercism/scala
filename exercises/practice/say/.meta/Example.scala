object Say:
  private val smallNums = IndexedSeq(
    "",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen",
  )

  private val tens   = IndexedSeq("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
  private val scales = IndexedSeq("", "thousand", "million", "billion")

  def inEnglish(n: Long): Option[String] =
    if n < 0 || n > 999_999_999_999L then None
    else if n == 0 then Some("zero")
    else
      val words = chunks(n).zipWithIndex.reverse.collect:
        case (chunk, scale) if chunk > 0 => s"${translateChunk(chunk)} ${scales(scale)}".trim
      Some(words.mkString(" "))

  // The number in groups of three digits, least significant first.
  private def chunks(n: Long): List[Int] =
    Iterator.iterate(n)(_ / 1000).takeWhile(_ != 0).map(l => (l % 1000).toInt).toList

  private def translateChunk(n: Int): String =
    val hundreds  = if n >= 100 then s"${smallNums(n / 100)} hundred" else ""
    val remainder = n % 100

    val rest =
      if remainder < 20 then smallNums(remainder)
      else if remainder % 10 == 0 then tens(remainder / 10)
      else s"${tens(remainder / 10)}-${smallNums(remainder % 10)}"
    List(hundreds, rest).filter(_.nonEmpty).mkString(" ")
