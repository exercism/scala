import scala.annotation.tailrec

object BinarySearch {
  def find[T](seq: Seq[T], value: T)(implicit ord: T => Ordered[T]): Option[Int]
    = searchInternal(seq, value, 0, seq.size - 1)

  @tailrec
  def searchInternal[T](seq: Seq[T], value: T,
                        start: Int, end: Int)(implicit ord: T => Ordered[T]): Option[Int] = {
    if (end < start || start < 0)
      None
    else {
      val middle = Math.floor(0.5 * (start + end)).asInstanceOf[Int]
      val elem = seq(middle)
      if (elem == value)
        Some(middle)
      else if (value < elem)
        searchInternal(seq, value, start, middle - 1)
      else
        searchInternal(seq, value, middle + 1, end)
    }
  }
}
