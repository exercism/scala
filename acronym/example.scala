case class Acroynm(phrase: String) {
  def abberviate: String = {
    "[A-Z]+[a-z]*|[a-z]+".r.findAllIn(phrase).map(s => s.head.toUpper).mkString
  }
}
