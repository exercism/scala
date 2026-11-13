import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class GrainsTest extends AnyFunSuite with Matchers:

  test("returns the number of grains on the square - grains on square 1"):
    Grains.square(1) should be(Some(1))

  test("returns the number of grains on the square - grains on square 2"):
    pending
    Grains.square(2) should be(Some(2))

  test("returns the number of grains on the square - grains on square 3"):
    pending
    Grains.square(3) should be(Some(4))

  test("returns the number of grains on the square - grains on square 4"):
    pending
    Grains.square(4) should be(Some(8))

  test("returns the number of grains on the square - grains on square 16"):
    pending
    Grains.square(16) should be(Some(32768))

  test("returns the number of grains on the square - grains on square 32"):
    pending
    Grains.square(32) should be(Some(BigInt("2147483648")))

  test("returns the number of grains on the square - grains on square 64"):
    pending
    Grains.square(64) should be(Some(BigInt("9223372036854775808")))

  test("returns the number of grains on the square - square 0 is invalid"):
    pending
    Grains.square(0) should be(None)

  test("returns the number of grains on the square - negative square is invalid"):
    pending
    Grains.square(-1) should be(None)

  test("returns the number of grains on the square - square greater than 64 is invalid"):
    pending
    Grains.square(65) should be(None)

  test("returns the total number of grains on the board"):
    pending
    Grains.total should be(BigInt("18446744073709551615"))
