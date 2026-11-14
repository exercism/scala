import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class CircularBufferTest extends AnyFunSuite with Matchers:

  test("reading empty buffer should fail"):
    val buffer = new CircularBuffer(1)
    assertThrows[EmptyBufferException](buffer.read())

  test("can read an item just written"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    buffer.read() should be(1)

  test("each item may only be read once"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    buffer.read() should be(1)
    assertThrows[EmptyBufferException](buffer.read())

  test("items are read in the order they are written"):
    pending
    val buffer = new CircularBuffer(2)
    buffer.write(1)
    buffer.write(2)
    buffer.read() should be(1)
    buffer.read() should be(2)

  test("full buffer can't be written to"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    assertThrows[FullBufferException](buffer.write(2))

  test("a read frees up capacity for another write"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    buffer.read() should be(1)
    buffer.write(2)
    buffer.read() should be(2)

  test("read position is maintained even across multiple writes"):
    pending
    val buffer = new CircularBuffer(3)
    buffer.write(1)
    buffer.write(2)
    buffer.read() should be(1)
    buffer.write(3)
    buffer.read() should be(2)
    buffer.read() should be(3)

  test("items cleared out of buffer can't be read"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    buffer.clear()
    assertThrows[EmptyBufferException](buffer.read())

  test("clear frees up capacity for another write"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.write(1)
    buffer.clear()
    buffer.write(2)
    buffer.read() should be(2)

  test("clear does nothing on empty buffer"):
    pending
    val buffer = new CircularBuffer(1)
    buffer.clear()
    buffer.write(1)
    buffer.read() should be(1)

  test("overwrite acts like write on non-full buffer"):
    pending
    val buffer = new CircularBuffer(2)
    buffer.write(1)
    buffer.overwrite(2)
    buffer.read() should be(1)
    buffer.read() should be(2)

  test("overwrite replaces the oldest item on full buffer"):
    pending
    val buffer = new CircularBuffer(2)
    buffer.write(1)
    buffer.write(2)
    buffer.overwrite(3)
    buffer.read() should be(2)
    buffer.read() should be(3)

  test("overwrite replaces the oldest item remaining in buffer following a read"):
    pending
    val buffer = new CircularBuffer(3)
    buffer.write(1)
    buffer.write(2)
    buffer.write(3)
    buffer.read() should be(1)
    buffer.write(4)
    buffer.overwrite(5)
    buffer.read() should be(3)
    buffer.read() should be(4)
    buffer.read() should be(5)

  test("initial clear does not affect wrapping around"):
    pending
    val buffer = new CircularBuffer(2)
    buffer.clear()
    buffer.write(1)
    buffer.write(2)
    buffer.overwrite(3)
    buffer.overwrite(4)
    buffer.read() should be(3)
    buffer.read() should be(4)
    assertThrows[EmptyBufferException](buffer.read())
