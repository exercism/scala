object Acronym:
  def abbreviate(phrase: String): String =
    phrase.split("[^\\p{L}']+").filter(_.nonEmpty).map(_.head.toUpper).mkString
