object KillerSudokuHelper:
  // The digits are in increasing order, so the combinations come out sorted too.
  def combinations(sum: Int, size: Int, exclude: List[Int]): List[List[Int]] =
    (1 to 9).toList.filterNot(exclude.contains).combinations(size).filter(_.sum == sum).toList
