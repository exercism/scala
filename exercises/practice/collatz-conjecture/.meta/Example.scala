object CollatzConjecture:
  def steps(n: Int): Option[Int] =
    Option.when(n > 0)(sequence(n).takeWhile(_ != 1).size)

  private def sequence(n: Int): Iterator[Long] =
    Iterator.iterate(n.toLong)(k => if k % 2 == 0 then k / 2 else 3 * k + 1)
