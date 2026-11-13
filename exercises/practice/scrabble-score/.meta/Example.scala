object ScrabbleScore:

  private val LetterScores: Map[Char, Int] =
    List("AEIOULNRST" -> 1, "DG" -> 2, "BCMP" -> 3, "FHVWY" -> 4, "K" -> 5, "JX" -> 8, "QZ" -> 10)
      .flatMap((letters, score) => letters.map(_ -> score))
      .toMap

  def score(word: String): Int = word.map(letter => LetterScores.getOrElse(letter.toUpper, 0)).sum
