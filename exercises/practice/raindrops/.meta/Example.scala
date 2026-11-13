object Raindrops:

  private val Sounds = List(3 -> "Pling", 5 -> "Plang", 7 -> "Plong")

  def convert(n: Int): String =
    Sounds.collect { case (factor, sound) if n % factor == 0 => sound }.mkString match
      case ""     => n.toString
      case sounds => sounds
