class Node[+T]
case object Empty extends Node[Nothing]
case class NonEmpty[T](value: T, var previous: Node[T], var next: Node[T]) extends Node[T]

class Deque[T] {
  private var first: Node[T] = Empty
  private var last: Node[T] = Empty

  def push(item: T): Unit = synchronized {
    last match {
      case Empty =>
        val newNode = NonEmpty[T](item, Empty, Empty)
        first = newNode
        last = newNode
      case node: NonEmpty[T] =>
        val newLast = NonEmpty(item, node, Empty)
        node.next = newLast
        last = newLast
    }
  }

  def pop: Option[T] = synchronized {
    last match {
      case Empty => None
      case node: NonEmpty[T] =>
        val prev = node.previous
        last = prev
        last match {
          case Empty =>
            first = Empty
          case node: NonEmpty[T] =>
            node.next = Empty
        }
        Some(node.value)
    }
  }

  def unshift(item: T): Unit = synchronized {
    first match {
      case Empty =>
        val newNode = NonEmpty[T](item, Empty, Empty)
        first = newNode
        last = newNode
      case node: NonEmpty[T] =>
        val newFirst = NonEmpty[T](item, Empty, node)
        node.previous = newFirst
        first = newFirst
    }
  }

  def shift: Option[T] = synchronized {
    first match {
      case Empty => None
      case node: NonEmpty[T] =>
        val nxt = node.next
        first = nxt
        first match {
          case Empty =>
            last = Empty
          case node: NonEmpty[T] =>
            node.previous = Empty
        }
        Some(node.value)
    }
  }
}

object Deque {
  def apply[T]() = new Deque[T]
}
