case class Matrix(matrix: String) {
  lazy val rows: Vector[Vector[Int]] = matrix.lines.map(_.split(' ').map(_.toInt).toVector).toVector

  lazy val cols: Vector[Vector[Int]] = rows.transpose
}
