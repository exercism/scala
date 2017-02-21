object Isogram {
  def isIsogram(s: String): Boolean = {
    !s.filter(_.isLetter)
      .map(_.toLower)
      .groupBy(c => c)
      .values.exists(xs => xs.length > 1)
  }
}