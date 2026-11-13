import scala.annotation.tailrec

/** A set kept as a linked list without duplicates. */
enum CustomSet[+A]:
  case Empty
  case Cons(head: A, tail: CustomSet[A])

object CustomSet:

  def fromList[A](list: List[A]): CustomSet[A] = list.foldLeft(Empty: CustomSet[A])(insert)

  def empty[A](set: CustomSet[A]): Boolean = set == Empty

  @tailrec
  def member[A](set: CustomSet[A], element: A): Boolean = set match
    case Empty            => false
    case Cons(head, tail) => head == element || member(tail, element)

  def isSubsetOf[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = all(set1)(member(set2, _))

  def isDisjointFrom[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = all(set1)(!member(set2, _))

  def isEqual[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = isSubsetOf(set1, set2) && isSubsetOf(set2, set1)

  def insert[A](set: CustomSet[A], element: A): CustomSet[A] = if member(set, element) then set else Cons(element, set)

  def intersection[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = filter(set1)(member(set2, _))

  def difference[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = filter(set1)(!member(set2, _))

  def union[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = foldLeft(set1, set2)(insert)

  @tailrec
  private def foldLeft[A, B](set: CustomSet[A], z: B)(f: (B, A) => B): B = set match
    case Empty            => z
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)

  private def all[A](set: CustomSet[A])(p: A => Boolean): Boolean = foldLeft(set, true)(_ && p(_))

  private def filter[A](set: CustomSet[A])(p: A => Boolean): CustomSet[A] =
    foldLeft(set, Empty: CustomSet[A])((acc, a) => if p(a) then insert(acc, a) else acc)
