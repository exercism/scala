object Yacht {
  def yacht(dices: List[Int]): Int =
    if (dices.toSet.size == 1) 50 else 0

  def diceFrequencies(dices: List[Int]): Vector[Int] =
    (1 to 6).map(i => dices.count(_ == i)).toVector

  def appearances(dices: List[Int], diceValue: Int): Int =
    diceFrequencies(dices)(diceValue - 1)

  def getSumOfDices(dices: List[Int]): Int =
    dices.sum

  def fourOfKind(dices: List[Int]): Int = {
    val counterArray = diceFrequencies(dices)
    val scores = (0 to 5).flatMap(i =>
      if (counterArray(i) >= 4) Some((i + 1) * 4) else None
    )
    if (scores.isEmpty) 0 else scores.head
  }

  def littleStraight(dices: List[Int]): Int =
    if (dices.sorted == List(1, 2, 3, 4, 5)) 30 else 0

  def bigStraight(dices: List[Int]): Int =
    if (dices.sorted == List(2, 3, 4, 5, 6)) 30 else 0

  def fullHouse(dices: List[Int]): Int = {
    val counterArray = diceFrequencies(dices)
    if (counterArray.contains(2) && counterArray.contains(3))
      dices.sum
    else
      0
  }

  def score(dices: List[Int], category: String): Int = category match {
    case "yacht" => yacht(dices)
    case "ones" => appearances(dices, 1)
    case "twos" => appearances(dices, 2) * 2
    case "threes" => appearances(dices, 3) * 3
    case "fours" => appearances(dices, 4) * 4
    case "fives" => appearances(dices, 5) * 5
    case "sixes" => appearances(dices, 6) * 6
    case "full house" => fullHouse(dices)
    case "four of a kind" => fourOfKind(dices)
    case "little straight" => littleStraight(dices)
    case "big straight" => bigStraight(dices)
    case "choice" => getSumOfDices(dices)
    case _ => 0
  }
}
