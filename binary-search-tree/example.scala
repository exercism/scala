case class Bst[+T](value: T, left: Option[Bst[T]], right: Option[Bst[T]]) {
  def insert[U >: T <% Ordered[U]](x: U): Bst[U] = {
    def insert(x: U, node: Option[Bst[U]]): Option[Bst[U]] =
      node match {
        case Some(n) => Option(n.insert(x))
        case _ => Option(new Bst(x, None, None))
      }

    if (x <= value) new Bst(value, insert(x, left), right)
    else Bst(value, left, insert(x, right))
  }
}

object Bst {
  def fromList[T <% Ordered[T]](l: List[T]): Bst[T] = l match {
    case x::xs => xs.foldLeft(Bst(x, None, None))((r, e) => r.insert(e))
    case x::Nil => Bst(x, None, None)
    case Nil => throw new IllegalArgumentException("Tree must not be empty")
  }

  def toList[T](tree: Bst[T]): List[T] = toList(Some(tree))

  private def toList[T](tree: Option[Bst[T]]): List[T] = tree match {
    case Some(b) => toList(b.left) ++ List(b.value) ++ toList(b.right)
    case None => List.empty
  }

  def apply[T](x: T): Bst[T] = Bst(x, None, None)
}