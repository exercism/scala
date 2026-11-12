enum Sublist:
  case Equal, Sublist, Superlist, Unequal

object Sublist:
  def sublist[T](first: List[T], second: List[T]): Sublist =
    if first == second then Equal
    else if second.containsSlice(first) then Sublist
    else if first.containsSlice(second) then Superlist
    else Unequal
