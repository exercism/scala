case class Matrix(matrix: String):

  private val rows: Vector[Vector[Int]] = matrix.linesIterator.map(_.split(' ').map(_.toInt).toVector).toVector

  private val columns: Vector[Vector[Int]] = rows.transpose

  def row(i: Int): Vector[Int] = rows(i)

  def column(i: Int): Vector[Int] = columns(i)
