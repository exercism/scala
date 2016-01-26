case class Acronym(phrase: String) {
  def abbreviate: String = {
    "[A-Z]+[a-z]*|[a-z]+".r.findAllIn(phrase).map(s => s.head.toUpper).mkString
  }
}
