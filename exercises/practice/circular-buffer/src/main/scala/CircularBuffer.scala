class EmptyBufferException() extends Exception

class FullBufferException() extends Exception

class CircularBuffer(capacity: Int):
  def write(value: Int): Unit = ???

  def read(): Int = ???

  def overwrite(value: Int): Unit = ???

  def clear(): Unit = ???
