object Acronym {
  def abbreviate(phrase: String): String = phrase.split("\\W").filter(_.nonEmpty).map(_.head.toUpper).mkString
}
