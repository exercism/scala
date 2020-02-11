object Change {
  type Coin = Int
  type Coins = List[Coin]
  type Amount = Int

  def findFewestCoins(target: Amount, coins: Coins): Option[Coins] = {
    def minChange(target: Amount, coins: Coins, candidate: Coins,
          bestResult: Option[Coins]): Option[Coins] =
    {
      def isWorseResult: Boolean =
        bestResult map (_.length <= candidate.length) getOrElse false

      if (target < 0 || isWorseResult) bestResult
      else if (target == 0) Some(candidate)
      else {
        val newBestResult = addCoin(target, coins, candidate, bestResult)
        dropCoin(target, coins, candidate, newBestResult)
      }
    }

    def addCoin(target: Amount, coins: Coins, candidate: Coins,
          bestResult: Option[Coins]): Option[Coins] =
      coins match {
        case coin :: _ if target - coin >= 0 =>
          minChange(target - coin, coins, coin :: candidate, bestResult)
        case _ => bestResult
      }

    def dropCoin(target: Amount, coins: Coins, candidate: Coins,
          bestResult: Option[Coins]): Option[Coins] =
      coins match {
        case _ :: restCoins =>
          minChange(target, restCoins, candidate, bestResult)
        case _ => bestResult
      }

    minChange(target, coins.sorted(Ordering.Int.reverse), List(), None)
  }
}

