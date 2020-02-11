case class Matrix(matrix: String) {
  lazy val rows: Vector[Vector[Int]] = matrix.lines.map(_.split(' ').map(_.toInt).toVector).toVector

  lazy val columns: Vector[Vector[Int]] = rows.transpose

  def row(i: Int) = rows(i)

  def column(i: Int) = columns(i)
}
