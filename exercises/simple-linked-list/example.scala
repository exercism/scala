import scala.reflect.ClassTag

class Node[+T]
case object Empty extends Node[Nothing]
case class NonEmpty[T](value: T, next: Node[T]) extends Node[T]

trait SimpleLinkedList[T] {
  def isEmpty: Boolean
  def value: T
  def add(item: T): SimpleLinkedList[T]
  def next: SimpleLinkedList[T]
  def reverse: SimpleLinkedList[T]
  def toArray(implicit m: ClassTag[T]): Array[T]
}

class SimpleLinkedListImpl[T](first: Node[T]) extends SimpleLinkedList[T] {

  def isEmpty: Boolean = first == Empty

  def value: T = first match {
    case Empty => throw new NoSuchElementException
    case node: NonEmpty[T] => node.value
  }

  def add(item: T): SimpleLinkedList[T] = {
    var reversed = reverse
    var current = NonEmpty[T](item, Empty)

    while (!reversed.isEmpty) {
      val value = reversed.value
      val newNode = NonEmpty[T](value, current)

      current = newNode
      reversed = reversed.next
    }

    new SimpleLinkedListImpl[T](current)
  }

  def next: SimpleLinkedList[T] = first match {
    case Empty => this
    case node: NonEmpty[T] => new SimpleLinkedListImpl[T](first.asInstanceOf[NonEmpty[T]].next)
  }

  def reverse: SimpleLinkedList[T] = {
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

    new SimpleLinkedListImpl[T](prev)
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

  def toArray(implicit m: ClassTag[T]): Array[T] = {
    val arr = new Array[T](length)

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
  def apply[T](): SimpleLinkedList[T] = new SimpleLinkedListImpl[T](Empty)

  def apply[T](arr: Array[T]): SimpleLinkedList[T] =
    arr.foldLeft(SimpleLinkedList[T]())((acc, t) => acc.add(t))
}
