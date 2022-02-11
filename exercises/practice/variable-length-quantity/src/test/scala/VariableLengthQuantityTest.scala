import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class VariableLengthQuantityTest extends AnyFunSuite with Matchers {

  test("zero") {
    VariableLengthQuantity.encode(List(0x0)) should be(List(0x0))
  }

  test("arbitrary single byte") {
    pending
    VariableLengthQuantity.encode(List(0x40)) should be(List(0x40))
  }

  test("largest single byte") {
    pending
    VariableLengthQuantity.encode(List(0x7F)) should be(List(0x7F))
  }

  test("smallest double byte") {
    pending
    VariableLengthQuantity.encode(List(0x80)) should be(List(0x81, 0x0))
  }

  test("arbitrary double byte") {
    pending
    VariableLengthQuantity.encode(List(0x2000)) should be(List(0xC0, 0x0))
  }

  test("largest double byte") {
    pending
    VariableLengthQuantity.encode(List(0x3FFF)) should be(List(0xFF, 0x7F))
  }

  test("smallest triple byte") {
    pending
    VariableLengthQuantity.encode(List(0x4000)) should be(List(0x81, 0x80, 0x0))
  }

  test("arbitrary triple byte") {
    pending
    VariableLengthQuantity.encode(List(0x100000)) should be(
      List(0xC0, 0x80, 0x0))
  }

  test("largest triple byte") {
    pending
    VariableLengthQuantity.encode(List(0x1FFFFF)) should be(
      List(0xFF, 0xFF, 0x7F))
  }

  test("smallest quadruple byte") {
    pending
    VariableLengthQuantity.encode(List(0x200000)) should be(
      List(0x81, 0x80, 0x80, 0x0))
  }

  test("arbitrary quadruple byte") {
    pending
    VariableLengthQuantity.encode(List(0x8000000)) should be(
      List(0xC0, 0x80, 0x80, 0x0))
  }

  test("largest quadruple byte") {
    pending
    VariableLengthQuantity.encode(List(0xFFFFFFF)) should be(
      List(0xFF, 0xFF, 0xFF, 0x7F))
  }

  test("smallest quintuple byte") {
    pending
    VariableLengthQuantity.encode(List(0x10000000)) should be(
      List(0x81, 0x80, 0x80, 0x80, 0x0))
  }

  test("arbitrary quintuple byte") {
    pending
    VariableLengthQuantity.encode(List(0xFF000000)) should be(
      List(0x8F, 0xF8, 0x80, 0x80, 0x0))
  }

  test("maximum 32-bit integer input") {
    pending
    VariableLengthQuantity.encode(List(0xFFFFFFFF)) should be(
      List(0x8F, 0xFF, 0xFF, 0xFF, 0x7F))
  }

  test("two single-byte values") {
    pending
    VariableLengthQuantity.encode(List(0x40, 0x7F)) should be(List(0x40, 0x7F))
  }

  test("two multi-byte values") {
    pending
    VariableLengthQuantity.encode(List(0x4000, 0x123456)) should be(
      List(0x81, 0x80, 0x0, 0xC8, 0xE8, 0x56))
  }

  test("many multi-byte values") {
    pending
    VariableLengthQuantity.encode(
      List(0x2000, 0x123456, 0xFFFFFFF, 0x0, 0x3FFF, 0x4000)) should be(
      List(0xC0, 0x0, 0xC8, 0xE8, 0x56, 0xFF, 0xFF, 0xFF, 0x7F, 0x0, 0xFF, 0x7F,
        0x81, 0x80, 0x0))
  }

  test("one byte") {
    pending
    VariableLengthQuantity.decode(List(0x7F)) should be(Right(List(0x7F)))
  }

  test("two bytes") {
    pending
    VariableLengthQuantity.decode(List(0xC0, 0x0)) should be(
      Right(List(0x2000)))
  }

  test("three bytes") {
    pending
    VariableLengthQuantity.decode(List(0xFF, 0xFF, 0x7F)) should be(
      Right(List(0x1FFFFF)))
  }

  test("four bytes") {
    pending
    VariableLengthQuantity.decode(List(0x81, 0x80, 0x80, 0x0)) should be(
      Right(List(0x200000)))
  }

  test("maximum 32-bit integer") {
    pending
    VariableLengthQuantity.decode(List(0x8F, 0xFF, 0xFF, 0xFF, 0x7F)) should be(
      Right(List(0xFFFFFFFF)))
  }

  test("incomplete sequence causes error") {
    pending
    VariableLengthQuantity.decode(List(0xFF)).isLeft should be(true)
  }

  test("incomplete sequence causes error, even if value is zero") {
    pending
    VariableLengthQuantity.decode(List(0x80)).isLeft should be(true)
  }

  test("multiple values") {
    pending
    VariableLengthQuantity.decode(
      List(0xC0, 0x0, 0xC8, 0xE8, 0x56, 0xFF, 0xFF, 0xFF, 0x7F, 0x0, 0xFF, 0x7F,
        0x81, 0x80, 0x0)) should be(
      Right(List(0x2000, 0x123456, 0xFFFFFFF, 0x0, 0x3FFF, 0x4000)))
  }
}
