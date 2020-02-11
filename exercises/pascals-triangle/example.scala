object PascalsTriangle {
  def rows(n: Int): List[List[Int]] =
    (for (row <- 1 to n) yield triRow(row)).toList

  private def triRow(row: Int): List[Int] = {
    var m = 1
    List(1) ::: (for (j <- 1 until row) yield {
      m = m * (row - j) / j
      m
    }).toList
  }
}
