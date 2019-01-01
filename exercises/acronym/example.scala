object Acronym {
  def abbreviate(phrase: String): String = {
    "('\\w+)|(\\w+'\\w+)|(\\w+')|(\\w+)".r
      .findAllIn(phrase)
      .map(_.head.toUpper)
      .mkString
  }
}
