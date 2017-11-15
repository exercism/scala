object FlattenArray {
  def flatten(l: List[Any]): List[Any] = (l match {
    case Nil => Nil
    case (h:List[_])::tail => flatten(h):::flatten(tail)
    case h::tail => h::flatten(tail)
  }).filter(_ != null)
}
