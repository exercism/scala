case class Matrix(values: List[List[Int]]) {
  lazy val saddlePoints: Set[(Int, Int)] = {
    val columns = values.transpose
    if (values.isEmpty || columns.isEmpty) {
      Set()
    } else {
      val rowMaxes = values.map(_.max).toVector
      val colMins = columns.map(_.min).toVector
      (for {
        i <- values.indices
        j <- columns.indices
        if rowMaxes(i) == colMins(j)
      } yield (i, j)).toSet
    }
  }
}
