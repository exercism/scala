object Yacht:
  def score(dice: List[Int], category: String): Int =
    val counts = dice.groupMapReduce(identity)(_ => 1)(_ + _)

    def face(n: Int) = n * counts.getOrElse(n, 0)

    category match
      case "ones"            => face(1)
      case "twos"            => face(2)
      case "threes"          => face(3)
      case "fours"           => face(4)
      case "fives"           => face(5)
      case "sixes"           => face(6)
      case "full house"      => if counts.values.toList.sorted == List(2, 3) then dice.sum else 0
      case "four of a kind"  => counts.collectFirst { case (n, count) if count >= 4 => 4 * n }.getOrElse(0)
      case "little straight" => if dice.sorted == List(1, 2, 3, 4, 5) then 30 else 0
      case "big straight"    => if dice.sorted == List(2, 3, 4, 5, 6) then 30 else 0
      case "choice"          => dice.sum
      case "yacht"           => if counts.size == 1 then 50 else 0
      case _                 => 0
