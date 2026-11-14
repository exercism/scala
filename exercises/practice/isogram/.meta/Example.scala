object Isogram:
  def isIsogram(s: String): Boolean =
    val letters = s.toLowerCase.filter(_.isLetter)
    letters.distinct.length == letters.length
