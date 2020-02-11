object Raindrops {
  private val sounds = List((3, "Pling"), (5, "Plang"), (7, "Plong"))

  def convert(n: Int): String =
    sounds.filter{case (m, _) => n % m == 0}.map{case (_, s) => s}.mkString match {
      case "" => n.toString
      case s => s
    }
}
