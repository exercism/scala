object BookStore {
  private val bookPrice = 8
  private val discounts =
    Map((1, 0.0),
      (2, 5.0),
      (3, 10.0),
      (4, 20.0),
      (5, 25.0))

  def total(books: List[Int]): Double = total(books, 0)

  private def total(books: List[Int], acc: Double): Double = {
    if (books.isEmpty) {
      acc
    } else {
      val bookNums = books.distinct

      (1 to bookNums.size).foldLeft(Double.MaxValue)((minPrice, groupSize) => {
        val itemsToRemove = bookNums.take(groupSize)
        val remainingBooks = getRemainingBooks(books, itemsToRemove)
        val price = total(remainingBooks, acc + groupCost(groupSize))
        scala.math.min(price, minPrice)
      })
    }
  }

  private def groupCost(groupSize: Int): Double = {
    discounts get groupSize match {
      case Some(discount) => bookPrice * groupSize * (100.0 - discount) / 100.0
      case _ => bookPrice * groupSize
    }
  }

  private def getRemainingBooks(books: List[Int], itemsToRemove: List[Int]): List[Int] = {
    var remainingBooks = books

    for (item <- itemsToRemove) {
      remainingBooks = removeFirst(remainingBooks)(_ == item)
    }

    remainingBooks
  }

  // drops the first item in the passed in list that matches the predicate.
  private def removeFirst[T](list: List[T])(pred: (T) => Boolean): List[T] = {
    val (before, atAndAfter) = list span (x => !pred(x))
    before ::: atAndAfter.drop(1)
  }
}
