import org.scalatest.{FunSuite, Matchers}

class VariableLengthQuantityTest extends FunSuite with Matchers {

  // Encode a series of integers, producing a series of bytes.
  test("encode: zero") {
    val encoded = VariableLengthQuantity.encode(List(0x0))
    encoded should be (List(0x0))
  }

  test("encode: arbitrary single byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x40))
    encoded should be (List(0x40))
  }

  test("encode: largest single byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x7f))
    encoded should be (List(0x7f))
  }

  test("encode: smallest double byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x80))
    encoded should be (List(0x81, 0x0))
  }

  test("encode: arbitrary double byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x2000))
    encoded should be (List(0xc0, 0x0))
  }

  test("encode: largest double byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x3fff))
    encoded should be (List(0xff, 0x7f))
  }

  test("encode: smallest triple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x4000))
    encoded should be (List(0x81, 0x80, 0x0))
  }

  test("encode: arbitrary triple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x100000))
    encoded should be (List(0xc0, 0x80, 0x0))
  }

  test("encode: largest triple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x1fffff))
    encoded should be (List(0xff, 0xff, 0x7f))
  }

  test("encode: smallest quadruple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x200000))
    encoded should be (List(0x81, 0x80, 0x80, 0x0))
  }

  test("encode: arbitrary quadruple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x8000000))
    encoded should be (List(0xc0, 0x80, 0x80, 0x0))
  }

  test("encode: largest quadruple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0xfffffff))
    encoded should be (List(0xff, 0xff, 0xff, 0x7f))
  }

  test("encode: smallest quintuple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x10000000))
    encoded should be (List(0x81, 0x80, 0x80, 0x80, 0x0))
  }

  test("encode: arbitrary quintuple byte") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0xff000000))
    encoded should be (List(0x8f, 0xf8, 0x80, 0x80, 0x0))
  }

  test("encode: maximum 32-bit integer input") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0xffffffff))
    encoded should be (List(0x8f, 0xff, 0xff, 0xff, 0x7f))
  }

  test("encode: two single-byte values") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x40, 0x7f))
    encoded should be (List(0x40, 0x7f))
  }

  test("encode: two multi-byte values") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x4000, 0x123456))
    encoded should be (List(0x81, 0x80, 0x0, 0xc8, 0xe8, 0x56))
  }

  test("encode: many multi-byte values") {
    pending
    val encoded = VariableLengthQuantity.encode(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000))
    encoded should be (List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0))
  }

  // Decode a series of bytes, producing a series of integers.
  test("decode: one byte") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0x7f))
    decoded should be (Right(List(0x7f)))
  }

  test("decode: two bytes") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0xc0, 0x0))
    decoded should be (Right(List(0x2000)))
  }

  test("decode: three bytes") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0xff, 0xff, 0x7f))
    decoded should be (Right(List(0x1fffff)))
  }

  test("decode: four bytes") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0x81, 0x80, 0x80, 0x0))
    decoded should be (Right(List(0x200000)))
  }

  test("decode: maximum 32-bit integer") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0x8f, 0xff, 0xff, 0xff, 0x7f))
    decoded should be (Right(List(0xffffffff)))
  }

  test("decode: incomplete sequence causes error") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0xff))
    decoded.isLeft should be (true)
  }

  test("decode: incomplete sequence causes error, even if value is zero") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0x80))
    decoded.isLeft should be (true)
  }

  test("decode: multiple values") {
    pending
    val decoded = VariableLengthQuantity.decode(List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0))
    decoded should be (Right(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000)))
  }

}
