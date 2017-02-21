case class RailFenceCipher(rails: Int) {
  private val size = rails * 2 - 2

  def encode(clearText: String): String =
    clearText
      .zipWithIndex
      .map { case (c, i) => (track(i), c) }
      .groupBy {_._1}
      .toSeq
      .sortBy { case (t, _) => t }
      .flatMap { case (t, v) => v.map { case (_, c) => c }}
      .mkString

  def decode (encodedText: String): String =
    Range.apply(0, encodedText.length)
      .groupBy(i => track(i))
      .toSeq
      .sortBy{ case (i, _) => i}
      .flatMap { case (_, v) => v }
      .zip(encodedText)
      .sortBy { case (i, _) => i }
      .map { case (_, c) => c }
      .mkString

  private def track(i: Int): Int = {
    if (isCorrectRail(i))
      0
    else if (isCorrectRail(i - rails + 1)) {
      rails - 1
    } else
      Range.inclusive(1, rails - 1)
        .filter{j => isCorrectRail(i - j) || isCorrectRail(i - size + j)}
        .head
  }

  private def isCorrectRail(i: Int) = i % size == 0
}

object RailFenceCipher {

  def encode(clearText: String, rails: Int): String =
    RailFenceCipher(rails).encode(clearText)

  def decode(encodedText: String, rails: Int): String =
    RailFenceCipher(rails).decode(encodedText)
}
