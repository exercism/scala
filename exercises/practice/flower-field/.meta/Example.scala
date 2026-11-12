object FlowerField:
  private val Flower = '*'

  def annotate(garden: List[String]): List[String] =
    def isFlower(row: Int, column: Int): Boolean =
      garden.lift(row).flatMap(_.lift(column)).contains(Flower)

    def flowersAround(row: Int, column: Int): Int =
      val neighbours =
        for
          r <- row - 1 to row + 1
          c <- column - 1 to column + 1
          if isFlower(r, c)
        yield ()
      neighbours.size

    garden.zipWithIndex.map: (line, row) =>
      line.indices.map { column =>
        if isFlower(row, column) then Flower
        else
          flowersAround(row, column) match
            case 0     => ' '
            case count => (count + '0').toChar
      }.mkString
