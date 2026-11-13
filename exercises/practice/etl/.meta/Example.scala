object Etl:
  def transform(scoreMap: Map[Int, Seq[String]]): Map[String, Int] =
    for
      (score, letters) <- scoreMap
      letter           <- letters
    yield letter.toLowerCase -> score
