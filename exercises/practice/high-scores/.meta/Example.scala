object HighScores:
  def latest(scores: List[Int]): Int = scores.last

  def personalBest(scores: List[Int]): Int = scores.max

  def personalTop(scores: List[Int]): List[Int] = scores.sorted(using Ordering[Int].reverse).take(3)
