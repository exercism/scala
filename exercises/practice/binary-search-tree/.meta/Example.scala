import scala.math.Ordering.Implicits.infixOrderingOps

case class Bst[+T](value: T, left: Option[Bst[T]], right: Option[Bst[T]]):
  // Equal values go to the left.
  def insert[U >: T: Ordering](x: U): Bst[U] =
    if x <= value then copy(left = Some(left.fold(Bst(x))(_.insert(x))))
    else copy(right = Some(right.fold(Bst(x))(_.insert(x))))

object Bst:
  def apply[T](x: T): Bst[T] = Bst(x, None, None)

  def fromList[T: Ordering](list: List[T]): Bst[T] = list match
    case head :: tail => tail.foldLeft(Bst(head))(_.insert(_))
    case Nil          => throw IllegalArgumentException("Tree must not be empty")

  def toList[T](tree: Bst[T]): List[T] =
    tree.left.toList.flatMap(toList) ++ (tree.value :: tree.right.toList.flatMap(toList))
