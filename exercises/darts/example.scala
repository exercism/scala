object Darts {
  def score(x: Double, y: Double): Int = {
    val distance = math.sqrt(x * x + y * y)
    distance match  {
      case _ if distance <= 1.0 => 10
      case _ if distance <= 5.0 => 5
      case _ if distance <= 10.0 => 1
      case _ => 0
    }
  }
}
