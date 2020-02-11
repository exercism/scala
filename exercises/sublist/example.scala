object Sublist extends Enumeration {
  def sublist[T](l1: List[T], l2: List[T]): Value = {
    if (equal(l1, l2)) Equal
    else if (isSublist(l1, l2)) Sublist
    else if (isSuperlist(l1, l2)) Superlist
    else Unequal
  }

  private def equal[T](l1: List[T], l2: List[T]) = l1.equals(l2)

  private def isSublist[T](l1: List[T], l2: List[T]) = l2.containsSlice(l1)

  private def isSuperlist[T](l1: List[T], l2: List[T]) = l1.containsSlice(l2)

  type SublistType = Value
  val Equal, Sublist, Superlist, Unequal = Value
}
