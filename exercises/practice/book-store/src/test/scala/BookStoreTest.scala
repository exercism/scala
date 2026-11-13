import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class BookStoreTest extends AnyFunSuite with Matchers:

  test("Only a single book"):
    BookStore.total(List(1)) should be(800)

  test("Two of the same book"):
    pending
    BookStore.total(List(2, 2)) should be(1600)

  test("Empty basket"):
    pending
    BookStore.total(List()) should be(0)

  test("Two different books"):
    pending
    BookStore.total(List(1, 2)) should be(1520)

  test("Three different books"):
    pending
    BookStore.total(List(1, 2, 3)) should be(2160)

  test("Four different books"):
    pending
    BookStore.total(List(1, 2, 3, 4)) should be(2560)

  test("Five different books"):
    pending
    BookStore.total(List(1, 2, 3, 4, 5)) should be(3000)

  test("Two groups of four is cheaper than group of five plus group of three"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 5)) should be(5120)

  test("Two groups of four is cheaper than groups of five and three"):
    pending
    BookStore.total(List(1, 1, 2, 3, 4, 4, 5, 5)) should be(5120)

  test("Group of four plus group of two is cheaper than two groups of three"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 4)) should be(4080)

  test("Two each of first four books and one copy each of rest"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5)) should be(5560)

  test("Two copies of each book"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)) should be(6000)

  test("Three copies of first book and two each of remaining"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 1)) should be(6800)

  test("Three each of first two books and two each of remaining books"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 1, 2)) should be(7520)

  test("Four groups of four are cheaper than two groups each of five and three"):
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 5, 1, 1, 2, 2, 3, 3, 4, 5)) should be(10240)

  test("Check that groups of four are created properly even when there are more groups of three than groups of five"):
    pending
    BookStore.total(List(1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 5, 5)) should be(14560)

  test("One group of one and four is cheaper than one group of two and three"):
    pending
    BookStore.total(List(1, 1, 2, 3, 4)) should be(3360)

  test("One group of one and two plus three groups of four is cheaper than one group of each size"):
    pending
    BookStore.total(List(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5)) should be(10000)
