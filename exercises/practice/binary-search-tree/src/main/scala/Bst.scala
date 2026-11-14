case class Bst[+T](value: T, left: Option[Bst[T]], right: Option[Bst[T]]):
  def insert[U >: T: Ordering](x: U): Bst[U] = ???

object Bst:
  def apply[T](x: T): Bst[T] = ???

  def fromList[T: Ordering](list: List[T]): Bst[T] = ???

  def toList[T](tree: Bst[T]): List[T] = ???
