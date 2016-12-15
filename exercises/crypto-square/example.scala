object CryptoSquare {
  def normalizePlaintext(text: String): String =
    text.filter(c => c.isLetterOrDigit).toLowerCase

  def squareSize(text: String): Int = Math.ceil(Math.sqrt(text.length)).asInstanceOf[Int]

  def plaintextSegments(text: String): List[String] = {
    val normalized = normalizePlaintext(text)
    val size = squareSize(normalized)
    size match {
      case 0 => List.empty
      case _ => normalized.grouped(size).toList
    }
  }

  def ciphertext(text: String): String = {
    val plainSegs = plaintextSegments(text)

    transpose(plainSegs).mkString
  }

  def normalizedCiphertext(text: String): String = {
    val plainSegs = plaintextSegments(text)

    transpose(plainSegs).mkString(" ")
  }

  private def transpose(texts: List[String]): List[String] = {
    if (texts.isEmpty || texts.head.isEmpty) {
      List.empty
    } else {
      val size = texts.head.length
      // Scala's transpose must work on square Lists. So pad the List. Then
      // strip off the padding before return.
      val padded = texts.map(s => s.padTo(size, " "))
      padded.transpose.map(item => item.mkString.replaceAll(" ", ""))
    }
  }
}
