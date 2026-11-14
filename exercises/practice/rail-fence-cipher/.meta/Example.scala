object RailFenceCipher:
  def encode(text: String, rails: Int): String = fence(text.length, rails).map(text).mkString

  def decode(text: String, rails: Int): String =
    fence(text.length, rails).zip(text).sortBy(_._1).map(_._2).mkString

  // The positions of a text read off the fence: the top rail first, left to right, then the next rail down.
  private def fence(length: Int, rails: Int): Seq[Int] =
    val zigzag = (0 until rails) ++ (rails - 2 until 0 by -1)
    (0 until length).sortBy(i => zigzag(i % zigzag.length))
