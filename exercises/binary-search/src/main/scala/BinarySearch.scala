object BinarySearch {
  def find(list: List[Int], element: Int): Option[Int] = {
    def go(array: Array[Int], element: Int, lowerBound: Int, upper: Int): Option[Int] = {
      val midpoint = lowerBound + ((upper - lowerBound) / 2)
      if(element == array(midpoint)) Some(midpoint)
      else if(element < array(midpoint)) go(array, element, lowerBound, midpoint - 1)
      else go(array, element, midpoint + 1, upper)
    }
    val array = list.toArray
    go(array, element, 0, array.length)
  }
}
