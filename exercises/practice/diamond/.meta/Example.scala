object Diamond:
  def rows(letter: Char): List[String] =
    val size = letter - 'A'
    val top  = (0 to size).toList.map(row(size))
    top ++ top.reverse.tail

  // Row i holds its letter i columns either side of the middle.
  private def row(size: Int)(i: Int): String =
    (-size to size).map(column => if column.abs == i then ('A' + i).toChar else ' ').mkString
