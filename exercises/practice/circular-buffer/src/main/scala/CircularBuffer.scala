class EmptyBufferException() extends Exception {}

class FullBufferException() extends Exception {}

class CircularBuffer(var capacity: Int) {

  def write(value: Int) = ???

  def read(): Int = ???

  def overwrite(value: Int) = ???

  def clear() = ???
}