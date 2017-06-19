object ScrabbleScore {

  def score(word: String): Int = word.foldLeft(0)((score, c) => score + scoreLetter(c))

  def scoreLetter(c: Char): Int = letterScores.getOrElse(c.toUpper, 0)

  private val scores = List(("AEIOULNRST", 1), ("DG", 2), ("BCMP", 3), ("FHVWY", 4),
    ("K", 5), ("JX", 8), ("QZ", 10))

  private val letterScores: Map[Char, Int] =
    scores.flatMap({case (letters, score) => for {c <- letters} yield (c, score)}).toMap
}

