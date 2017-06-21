case class Triangle(a: Double, b: Double, c: Double) {
  private def checkInequality = a + b >= c  &&  a + c >= b && b + c >= a

  val illogical: Boolean = List(a, b, c).count(_ <= 0) > 0 || !checkInequality
  val isosceles: Boolean = !illogical && (a == b || a == c || b == c)
  val equilateral: Boolean = !illogical && ( a == b && b == c)
  val scalene: Boolean = !illogical && !isosceles && !equilateral
}