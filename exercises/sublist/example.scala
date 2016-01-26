
class Sublist[T] {
  def sublist(l1: List[T], l2: List[T]): Sublist.Value = {
    if (equal(l1, l2)) Sublist.Equal
    else if (isSublist(l1, l2)) Sublist.Sublist
    else if (isSuperlist(l1, l2)) Sublist.Superlist
    else Sublist.Unequal
  }

  private def equal(l1: List[T], l2: List[T]) = l1.equals(l2)

  private def isSublist(l1: List[T], l2: List[T]) = l2.containsSlice(l1)

  private def isSuperlist(l1: List[T], l2: List[T]) = l1.containsSlice(l2)
}

object Sublist extends Enumeration {
  type SublistType = Value
  val Equal, Sublist, Superlist, Unequal = Value
}
