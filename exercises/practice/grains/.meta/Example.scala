object Grains:

  private val Squares = 1 to 64

  def square(n: Int): Option[BigInt] = Option.when(Squares.contains(n))(BigInt(2).pow(n - 1))

  def total: BigInt = BigInt(2).pow(Squares.size) - 1
