object HighScores {
  def latest(scores: List[Int]): Int = scores.last

  def personalBest(scores: List[Int]): Int = scores.max

  def personalTop(scores: List[Int]): List[Int] =
    scores.sorted(Ordering[Int].reverse).take(3)

  def report(scores: List[Int]): String = {
    val highScore = personalBest(scores)
    val latestScore = latest(scores)
    if (highScore == latestScore)
      s"Your latest score was $latestScore. That's your personal best!"
    else
      s"Your latest score was $latestScore. That's ${highScore - latestScore} short of your personal best!"
  }
}