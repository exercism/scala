object Alphametics:
  type Solution = Map[Char, Int]

  def solve(puzzle: String): Option[Solution] =
    puzzle.split("==") match
      case Array(left, right) => solve(words(left), words(right))
      case _                  => None

  private def words(side: String): List[String] = side.split('+').map(_.trim).toList

  // A letter's weight is what its digit adds to the left side minus what it adds to the right:
  // in "AB + B == BC", A is worth 10, B is worth 1 + 1 - 10 and C is worth -1. A solution makes
  // the weighted digits sum to zero.
  private def solve(left: List[String], right: List[String]): Option[Solution] =
    val weights = (left.flatMap(weightsOf(_, 1)) ++ right.flatMap(weightsOf(_, -1))).groupMapReduce(_._1)(_._2)(_ + _)
    val letters = weights.keys.toVector
    val leading = (left ++ right).filter(_.length > 1).map(_.head).toSet

    def isSolution(digits: IndexedSeq[Int]): Boolean =
      letters.indices.map(i => weights(letters(i)) * digits(i)).sum == 0 &&
        letters.indices.forall(i => digits(i) != 0 || !leading(letters(i)))

    (0 to 9).combinations(letters.size).flatMap(_.permutations).find(isSolution).map(letters.zip(_).toMap)

  private def weightsOf(word: String, sign: Long): Seq[(Char, Long)] =
    word.reverse.zip(Iterator.iterate(sign)(_ * 10))
