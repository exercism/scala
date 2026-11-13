import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class VariableLengthQuantityTest extends AnyFunSuite with Matchers:

  test("Encode a series of integers, producing a series of bytes. - zero"):
    VariableLengthQuantity.encode(List(0x0)) should be(List(0x0))

  test("Encode a series of integers, producing a series of bytes. - arbitrary single byte"):
    pending
    VariableLengthQuantity.encode(List(0x40)) should be(List(0x40))

  test("Encode a series of integers, producing a series of bytes. - asymmetric single byte"):
    pending
    VariableLengthQuantity.encode(List(0x53)) should be(List(0x53))

  test("Encode a series of integers, producing a series of bytes. - largest single byte"):
    pending
    VariableLengthQuantity.encode(List(0x7f)) should be(List(0x7f))

  test("Encode a series of integers, producing a series of bytes. - smallest double byte"):
    pending
    VariableLengthQuantity.encode(List(0x80)) should be(List(0x81, 0x0))

  test("Encode a series of integers, producing a series of bytes. - arbitrary double byte"):
    pending
    VariableLengthQuantity.encode(List(0x2000)) should be(List(0xc0, 0x0))

  test("Encode a series of integers, producing a series of bytes. - asymmetric double byte"):
    pending
    VariableLengthQuantity.encode(List(0xad)) should be(List(0x81, 0x2d))

  test("Encode a series of integers, producing a series of bytes. - largest double byte"):
    pending
    VariableLengthQuantity.encode(List(0x3fff)) should be(List(0xff, 0x7f))

  test("Encode a series of integers, producing a series of bytes. - smallest triple byte"):
    pending
    VariableLengthQuantity.encode(List(0x4000)) should be(List(0x81, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - arbitrary triple byte"):
    pending
    VariableLengthQuantity.encode(List(0x100000)) should be(List(0xc0, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - asymmetric triple byte"):
    pending
    VariableLengthQuantity.encode(List(0x1d59c)) should be(List(0x87, 0xab, 0x1c))

  test("Encode a series of integers, producing a series of bytes. - largest triple byte"):
    pending
    VariableLengthQuantity.encode(List(0x1fffff)) should be(List(0xff, 0xff, 0x7f))

  test("Encode a series of integers, producing a series of bytes. - smallest quadruple byte"):
    pending
    VariableLengthQuantity.encode(List(0x200000)) should be(List(0x81, 0x80, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - arbitrary quadruple byte"):
    pending
    VariableLengthQuantity.encode(List(0x8000000)) should be(List(0xc0, 0x80, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - asymmetric quadruple byte"):
    pending
    VariableLengthQuantity.encode(List(0x357704)) should be(List(0x81, 0xd5, 0xee, 0x4))

  test("Encode a series of integers, producing a series of bytes. - largest quadruple byte"):
    pending
    VariableLengthQuantity.encode(List(0xfffffff)) should be(List(0xff, 0xff, 0xff, 0x7f))

  test("Encode a series of integers, producing a series of bytes. - smallest quintuple byte"):
    pending
    VariableLengthQuantity.encode(List(0x10000000)) should be(List(0x81, 0x80, 0x80, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - arbitrary quintuple byte"):
    pending
    VariableLengthQuantity.encode(List(0xff000000)) should be(List(0x8f, 0xf8, 0x80, 0x80, 0x0))

  test("Encode a series of integers, producing a series of bytes. - asymmetric quintuple byte"):
    pending
    VariableLengthQuantity.encode(List(0x86656105)) should be(List(0x88, 0xb3, 0x95, 0xc2, 0x5))

  test("Encode a series of integers, producing a series of bytes. - maximum 32-bit integer input"):
    pending
    VariableLengthQuantity.encode(List(0xffffffff)) should be(List(0x8f, 0xff, 0xff, 0xff, 0x7f))

  test("Encode a series of integers, producing a series of bytes. - two single-byte values"):
    pending
    VariableLengthQuantity.encode(List(0x40, 0x7f)) should be(List(0x40, 0x7f))

  test("Encode a series of integers, producing a series of bytes. - two multi-byte values"):
    pending
    VariableLengthQuantity.encode(List(0x4000, 0x123456)) should be(List(0x81, 0x80, 0x0, 0xc8, 0xe8, 0x56))

  test("Encode a series of integers, producing a series of bytes. - many multi-byte values"):
    pending
    VariableLengthQuantity.encode(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000)) should be(
      List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0),
    )

  test("Decode a series of bytes, producing a series of integers. - one byte"):
    pending
    VariableLengthQuantity.decode(List(0x7f)) should be(Right(List(0x7f)))

  test("Decode a series of bytes, producing a series of integers. - two bytes"):
    pending
    VariableLengthQuantity.decode(List(0xc0, 0x0)) should be(Right(List(0x2000)))

  test("Decode a series of bytes, producing a series of integers. - three bytes"):
    pending
    VariableLengthQuantity.decode(List(0xff, 0xff, 0x7f)) should be(Right(List(0x1fffff)))

  test("Decode a series of bytes, producing a series of integers. - four bytes"):
    pending
    VariableLengthQuantity.decode(List(0x81, 0x80, 0x80, 0x0)) should be(Right(List(0x200000)))

  test("Decode a series of bytes, producing a series of integers. - maximum 32-bit integer"):
    pending
    VariableLengthQuantity.decode(List(0x8f, 0xff, 0xff, 0xff, 0x7f)) should be(Right(List(0xffffffff)))

  test("Decode a series of bytes, producing a series of integers. - incomplete sequence causes error"):
    pending
    VariableLengthQuantity.decode(List(0xff)).isLeft should be(true)

  test("Decode a series of bytes, producing a series of integers. - incomplete sequence causes error, even if value is zero"):
    pending
    VariableLengthQuantity.decode(List(0x80)).isLeft should be(true)

  test("Decode a series of bytes, producing a series of integers. - multiple values"):
    pending
    VariableLengthQuantity.decode(List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0)) should be(
      Right(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000)),
    )
