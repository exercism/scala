import scala.annotation.tailrec

// Naive implementation of a CustomSet using a LinkedList
sealed trait CustomSet[+A]
case object Nil extends CustomSet[Nothing]
case class Cons[+A](head: A, tail: CustomSet[A]) extends CustomSet[A]

object CustomSet {
  @tailrec
  def foldLeft[A,B](l: CustomSet[A], z: B)(f: (B, A) => B): B =
    l match {
      case Nil => z
      case Cons(h, t) => foldLeft(t, f(z, h))(f)
    }

  def fromList[A](xs: List[A]) =
    xs.foldLeft(Nil: CustomSet[A])((acc, a) => Cons(a, acc))

  def toList[A](set: CustomSet[A]): List[A] =
    foldLeft(set, List[A]())((acc, a) => a :: acc)

  def empty[A](set: CustomSet[A]) = set == Nil

  def singleton[A](set: CustomSet[A]) =
    set match {
      case Cons(a, Nil) => true
      case _ => false
    }

  def size[A](set: CustomSet[A]): Int = {
    @tailrec
    def internal(set: CustomSet[A], current: Int): Int =
      set match {
        case Nil => current
        case Cons(_, tail) => internal(tail, current + 1)
      }

    internal(set, 0)
  }

  @tailrec
  def member[A](set: CustomSet[A], item: A): Boolean =
    set match {
      case Nil => false
      case Cons(a, tail) => if (a == item) true else member(tail, item)
    }

  def delete[A](set: CustomSet[A], item: A): CustomSet[A] = {
    @tailrec
    def internal(set: CustomSet[A], acc: CustomSet[A]): CustomSet[A] =
     set match {
       case Nil => acc
       case Cons(a, tail) if a == item => internal(tail, acc)
       case Cons(a, tail) => internal(tail, Cons(a, acc))
     }

    internal(set, Nil)
  }

  def insert[A](set: CustomSet[A], item: A): CustomSet[A] =
    if (member(set, item)) set else Cons(item, set)

  def union[A](setA: CustomSet[A], setB: CustomSet[A]): CustomSet[A] =
    foldLeft(setA, setB)((acc, a) => insert(acc, a))

  def difference[A](setA: CustomSet[A], setB: CustomSet[A]): CustomSet[A] =  {
    foldLeft(setA, Nil: CustomSet[A])((acc, a) => if (member(setB, a)) acc else Cons(a, acc))
  }

  def intersection[A](setA: CustomSet[A], setB: CustomSet[A]): CustomSet[A] =
    foldLeft(setA, Nil: CustomSet[A])((acc, a) => if (member(setB, a)) Cons(a, acc) else acc)

  @tailrec
  def isSubsetOf[A](setA: CustomSet[A], setB: CustomSet[A]): Boolean =
    setA match {
      case Nil => true
      case Cons(a, tail) => member(setB, a) && isSubsetOf(tail, setB)
    }

  @tailrec
  def isDisjointFrom[A](setA: CustomSet[A], setB: CustomSet[A]): Boolean =
    setA match {
      case Nil => true
      case Cons(a, tail) => !member(setB, a) && isDisjointFrom(tail, setB)
    }

  def isEqual[A](setA: CustomSet[A], setB: CustomSet[A]): Boolean =
    empty(difference(setA, setB)) && empty(difference(setB, setA))
}

