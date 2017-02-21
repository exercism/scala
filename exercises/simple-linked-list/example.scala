import scala.reflect.ClassTag

class Node[+T]
case object Empty extends Node[Nothing]
case class NonEmpty[T](value: T, var next: Node[T]) extends Node[T]

trait SimpleLinkedList[T] {
  def isEmpty: Boolean
  def value: T
  def add(item: T): SimpleLinkedList[T]
  def next: SimpleLinkedList[T]
  def reverse: SimpleLinkedList[T]
  def toArray[U >: T](implicit m: ClassTag[U]): Array[U]
}

class SimpleLinkedListImpl[T](first: Node[T], last: Node[T]) extends SimpleLinkedList[T] {

  def isEmpty: Boolean = first == Empty

  def value: T = first match {
    case Empty => throw new NoSuchElementException
    case node: NonEmpty[T] => node.value
  }

  def add(item: T): SimpleLinkedList[T] = synchronized {
    val newLast = NonEmpty[T](item, Empty)

    last match {
      case Empty =>
        new SimpleLinkedListImpl[T](newLast, newLast)
      case node: NonEmpty[T] =>
        node.next = newLast
        new SimpleLinkedListImpl[T](first, newLast)
    }
  }

  def next: SimpleLinkedList[T] = first match {
    case Empty => this
    case node: NonEmpty[T] => new SimpleLinkedListImpl[T](first.asInstanceOf[NonEmpty[T]].next, last)
  }

  def reverse: SimpleLinkedList[T] = synchronized {
    var newLast: Node[T] = Empty
    var prev: Node[T] = Empty
    var current = first
    var next: Node[T] = Empty
    while (current != Empty) {
      val newNode = NonEmpty[T](current.asInstanceOf[NonEmpty[T]].value, prev)

      if (newLast == Empty) {
        newLast = newNode
      }

      next = current.asInstanceOf[NonEmpty[T]].next
      prev = newNode
      current = next
    }

    new SimpleLinkedListImpl[T](prev, newLast)
  }

  def length: Int = {
    var len = 0

    var current = first
    while (current != Empty) {
      len = len + 1
      current = current.asInstanceOf[NonEmpty[T]].next
    }

    len
  }

  def toArray[U >: T](implicit m: ClassTag[U]): Array[U] = {
    val arr = new Array[U](length)

    var current = first
    var i = 0
    while (current != Empty) {
      arr(i) = current.asInstanceOf[NonEmpty[T]].value
      current = current.asInstanceOf[NonEmpty[T]].next
      i = i + 1
    }

    arr
  }
}

object SimpleLinkedList {
  def apply[T](): SimpleLinkedList[T] = new SimpleLinkedListImpl[T](Empty, Empty)

  def apply[T](arr: Array[T]): SimpleLinkedList[T] =
    arr.foldLeft(SimpleLinkedList[T]())((acc, t) => acc.add(t))
}
