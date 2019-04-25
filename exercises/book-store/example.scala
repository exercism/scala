object BookStore {
  private val price = 8
  private val discounts =
    Map(0 -> 0, 1 -> 100, 2 -> 95, 3 -> 90, 4 -> 80, 5 -> 75)
  private val cache = scala.collection.mutable.HashMap[List[Int], Int]()

  private def getAllCombinations(books: List[Int]): List[List[Int]] =
    (1 to books.size).flatMap(books.combinations).toList

  private def totalForCombination(books: List[Int]): Int =
    price * books.length * discounts(books.length)

  private def getTotal(books: List[Int]): Int = books match {
    case Nil => 0
    case _ =>
      getAllCombinations(books.distinct)
        .map((combo: List[Int]) =>
          totalForCombination(combo) + total(books.diff(combo)))
        .min
  }

  def total(books: List[Int]): Int =
    cache.getOrElseUpdate(books, getTotal(books))
}