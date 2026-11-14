object PascalsTriangle:
  // Each row is the previous one added to itself shifted by one place.
  def rows(n: Int): List[List[Int]] =
    List.iterate(List(1), n)(row => (0 :: row).lazyZip(row :+ 0).map(_ + _))
