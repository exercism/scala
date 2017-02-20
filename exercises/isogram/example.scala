import scala.collection.mutable

object Isogram {
  def isIsogram(s: String): Boolean = {
    val seen = mutable.HashSet[Char]()

    s.filter(_.isLetter)
      .map(_.toLower)
      .foreach { c =>
        if (seen.contains(c)) {
          return false
        } else {
          seen.add(c)
        }
      }

    true
  }
}
