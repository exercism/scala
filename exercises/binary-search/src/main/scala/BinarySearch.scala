import scala.annotation.tailrec

object BinarySearch {
  def find(list: List[Int], element: Int): Option[Int] = {
    @tailrec
    def go(array: Array[Int], element: Int, lowerBound: Int, upper: Int): Option[Int] = {
      val midpoint = lowerBound + ((upper - lowerBound) / 2)
      if(element == array(midpoint)) Some(midpoint)
      else if(element < array(midpoint) && midpoint > lowerBound) go(array, element, lowerBound, midpoint - 1)
      else if(element >= array(midpoint) && midpoint < upper) go(array, element, midpoint + 1, upper)
      else None
    }
    if(list.isEmpty) None
    else {
      val array = list.toArray
      go(array, element, 0, array.length - 1)
    }
  }
}
