object SpiralMatrix:

  type Matrix = Seq[Seq[Int]]
  type Point  = (Int, Int)

  def spiralMatrix(dim: Int): Matrix =
    val spiralPoints =
      (for
        start     <- startPoints(dim)
        squareSize = dim - start._1 * 2
      yield squarePoints(squareSize, start)).flatten.distinct

    toMatrix(dim, spiralPoints.zip(LazyList.from(1)))

  private def startPoints(dim: Int): Seq[Point] =
    (0 to math.ceil(dim / 2d).toInt).map(x => (x, x))

  private def squarePoints(squareSize: Int, start: Point): Seq[Point] =
    def translate(p: Point): Point = (p._1 + start._1, p._2 + start._2)

    def side(f: Int => Point, reversed: Boolean = false): Seq[Point] =
      val result = (0 until squareSize).map(x => translate(f(x)))
      if reversed then result.reverse else result

    if squareSize <= 0 then List()
    else if squareSize == 1 then List(start)
    else
      val maxCoord = squareSize - 1

      val top    = side((_, 0))
      val right  = side((maxCoord, _))
      val bottom = side((_, maxCoord), reversed = true)
      val left   = side((0, _), reversed = true)

      (top ++ right ++ bottom ++ left).distinct

  private def toMatrix(dim: Int, data: Seq[(Point, Int)]): Matrix =
    val matrix = Array.ofDim[Int](dim, dim)
    data.foreach { case ((x, y), n) => matrix(y)(x) = n }
    matrix.map(_.toSeq).toSeq
