class Accumulate[A, B] {
  def accumulate(f: (A) => B, list : List[A]): List[B] =
    list match {
      case Nil => Nil
      case x::xs => f(x) :: accumulate(f, xs)
    }
}
