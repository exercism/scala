object BottleSong:
  private val numbers = List("no", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")

  private def suffix(bottles: Int) = if bottles == 1 then "" else "s"

  private def hanging(bottles: Int) =
    s"${numbers(bottles)} green bottle${suffix(bottles)} hanging on the wall"

  private def firstLine(bottles: Int) = hanging(bottles) + ",\n"

  private def lastLine(bottles: Int) = "There'll be " + hanging(bottles).toLowerCase + ".\n"

  private def verse(bottles: Int) = firstLine(bottles).repeat(2) +
    "And if one green bottle should accidentally fall,\n" + lastLine(bottles - 1)

  def recite(startBottles: Int, takeDown: Int): String =
    startBottles until startBottles - takeDown by -1 map verse mkString "\n"
