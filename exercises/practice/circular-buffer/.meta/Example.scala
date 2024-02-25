class EmptyBufferException() extends Exception {}

class FullBufferException() extends Exception {}

class CircularBuffer(var capacity: Int) {
  private var data = List[Int]()

  def write(value: Int): Unit = {
    if (isFull()) {
      throw new FullBufferException
    }
    data = value :: data
  }

  def read(): Int = {
    if (data.isEmpty) {
      throw new EmptyBufferException
    }

    val last = data.last
    data = data.init
    last
  }

  def overwrite(value: Int): Unit = {
    if (isFull()) {
      read()
    }
    
    write(value)
  }

  def clear(): Unit = {
    data = List[Int]()
  }

  def isFull() = data.size == capacity
}