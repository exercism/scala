object Isogram {
  def isIsogram(s: String): Boolean =
    !s.filter(_.isLetter)
      .map(_.toLower)
      .groupBy(identity)
      .values
      .exists(_.length > 1)
}