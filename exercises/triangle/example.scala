import TriangleType.TriangleType

class Triangle(a: Int, b: Int, c: Int) {
  def triangleType: TriangleType =
    if (List(a, b, c).count(_ <= 0) > 0 || !checkInequality) TriangleType.Illogical
    else if (a == b && b == c) TriangleType.Equilateral
    else if (a == b || a == c || b == c) TriangleType.Isosceles
    else TriangleType.Scalene

  private def checkInequality = a + b >= c  &&  a + c >= b && b + c >= a
}

object Triangle {
  def apply(a: Int, b: Int, c: Int) = new Triangle(a, b, c)
}

object TriangleType extends Enumeration {
  type TriangleType = Value

  val Equilateral = Value("Equilateral")
  val Isosceles = Value("Isosceles")
  val Scalene = Value("Scalene")
  val Illogical = Value("Illogical")
}
