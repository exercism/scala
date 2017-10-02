case class WordCount(phrase: String) {
  def countwords = "\\w+('\\w+)*".r
    .findAllIn(phrase)
    .toSeq
    .map(_.toLowerCase)
    .groupBy(w => w)
    .mapValues(_.length)
}
