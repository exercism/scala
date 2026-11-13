object BookStore:

  private val GroupPrice = Map(1 -> 800, 2 -> 1520, 3 -> 2160, 4 -> 2560, 5 -> 3000)

  /** Two groups of four cost less than a group of five and a group of three. */
  private val FiveAndThreeToFourAndFour = GroupPrice(5) + GroupPrice(3) - 2 * GroupPrice(4)

  def total(books: List[Int]): Int =
    val copies = books.groupBy(identity).values.map(_.size).toList
    val groups = (0 until copies.maxOption.getOrElse(0)).map(i => copies.count(_ > i))
    val swaps  = groups.count(_ == 5) min groups.count(_ == 3)
    groups.map(GroupPrice).sum - swaps * FiveAndThreeToFourAndFour
