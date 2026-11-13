trait CustomSet[+A]

object CustomSet:
  def fromList[A](list: List[A]): CustomSet[A] = ???

  def empty[A](set: CustomSet[A]): Boolean = ???

  def member[A](set: CustomSet[A], element: A): Boolean = ???

  def isSubsetOf[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = ???

  def isDisjointFrom[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = ???

  def isEqual[A](set1: CustomSet[A], set2: CustomSet[A]): Boolean = ???

  def insert[A](set: CustomSet[A], element: A): CustomSet[A] = ???

  def intersection[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = ???

  def difference[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = ???

  def union[A](set1: CustomSet[A], set2: CustomSet[A]): CustomSet[A] = ???
