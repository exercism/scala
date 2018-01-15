object PascalsTriangle {
  val firstRow = Stream(1)

  def infiniteTail(previousRow: Stream[Int]): Stream[Stream[Int]] = {
    val nextRow = 1 #:: previousRow.zip(previousRow.tail).map { case (i, j) => i + j }.append(Stream(1))
    nextRow #:: infiniteTail(nextRow)
  }

  def pascalTriangle: Stream[Stream[Int]] = Stream(firstRow).append(infiniteTail(firstRow))

  def rows(i: Int): List[List[Int]] = pascalTriangle.take(i).toList.map(_.toList)

}
