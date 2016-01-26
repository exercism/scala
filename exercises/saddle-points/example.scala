case class Matrix(values: List[List[Int]]) {
  lazy val saddlePoints: Set[(Int, Int)] = {
    val rowMaxes = values.map(_.max).toVector
    val columns = values.transpose
    val colMins = columns.map(_.min).toVector
    (for {
      i <- 0 until values.length
      j <- 0 until columns.length
      if rowMaxes(i) == colMins(j)
    } yield (i, j)).toSet
  }
}
