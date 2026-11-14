import scala.annotation.tailrec
import scala.math.Ordering.Implicits.infixOrderingOps

object BinarySearch:
  def find[T: Ordering](seq: Seq[T], value: T): Option[Int] =
    val items = seq.toIndexedSeq

    @tailrec
    def search(start: Int, end: Int): Option[Int] =
      if end < start then None
      else
        val middle = (start + end) / 2
        val elem   = items(middle)
        if elem == value then Some(middle)
        else if value < elem then search(start, middle - 1)
        else search(middle + 1, end)

    search(0, items.size - 1)
