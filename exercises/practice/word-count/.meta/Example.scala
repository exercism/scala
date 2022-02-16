case class WordCount(phrase: String) {
  def countWords = "\\w+('\\w+)*".r
    .findAllIn(phrase)
    .toSeq
    .map(_.toLowerCase)
    .groupBy(w => w)
    .mapValues(_.length)
    .toMap
}
