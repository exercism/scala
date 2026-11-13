object CryptoSquare:
  def ciphertext(text: String): String =
    val normalized = text.filter(_.isLetterOrDigit).toLowerCase
    if normalized.isEmpty then ""
    else
      val columns = math.ceil(math.sqrt(normalized.length)).toInt
      val rows    = (normalized.length + columns - 1) / columns
      normalized
        .padTo(rows * columns, ' ')
        .grouped(columns)
        .toList
        .transpose
        .map(_.mkString)
        .mkString(" ")
