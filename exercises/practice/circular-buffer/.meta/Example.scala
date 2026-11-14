import scala.collection.mutable

class EmptyBufferException() extends Exception

class FullBufferException() extends Exception

class CircularBuffer(capacity: Int):
  private val items = mutable.Queue.empty[Int]

  def write(value: Int): Unit =
    if items.size == capacity then throw FullBufferException()
    items.enqueue(value)

  def read(): Int =
    if items.isEmpty then throw EmptyBufferException()
    items.dequeue()

  // On a full buffer the oldest item makes room for the new one.
  def overwrite(value: Int): Unit =
    if items.size == capacity then items.dequeue()
    items.enqueue(value)

  def clear(): Unit = items.clear()
