object Change:
  def findFewestCoins(target: Int, coins: List[Int]): Option[List[Int]] =
    if target < 0 then None
    else
      val fewest = (1 to target).foldLeft(Vector(Option(List.empty[Int]))): (fewest, amount) =>
        val candidates =
          for
            coin   <- coins if coin <= amount
            change <- fewest(amount - coin)
          yield coin :: change
        fewest :+ candidates.minByOption(_.length)
      fewest(target).map(_.sorted)
