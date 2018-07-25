object Diamond {
  def rows(c: Char): List[String] = {
    val top: List[String] = buildTop(c)
    val bottom: List[String] = top.reverse.drop(1)
    top ::: bottom
  }

  private def buildTop(letter: Char): List[String] = {
    var numLeadingAndTrailingSpaces = letter - 'A'
    var interiorSpaces = -1
    ('A' to letter)
      .map {c =>
        val leadingAndTrailingSpaces = " " * numLeadingAndTrailingSpaces
        val line = if (interiorSpaces <= 0)
          leadingAndTrailingSpaces + c + leadingAndTrailingSpaces
        else
          leadingAndTrailingSpaces + c + " " * Math.max(interiorSpaces, 0) + c + leadingAndTrailingSpaces

        numLeadingAndTrailingSpaces -= 1
        interiorSpaces += 2
        line
      }
      .toList
  }
}
