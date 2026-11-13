case class Triangle(a: Double, b: Double, c: Double):
  private val sides    = List(a, b, c).sorted
  private val valid    = sides.head > 0 && sides(0) + sides(1) >= sides(2)
  private val distinct = sides.distinct.size

  val equilateral: Boolean = valid && distinct == 1
  val isosceles: Boolean   = valid && distinct <= 2
  val scalene: Boolean     = valid && distinct == 3
