import org.scalatest.{Matchers, FunSuite}

/** @version 1.4.0 */
class BookStoreTest extends FunSuite with Matchers {


  test("Only a single book") {
    BookStore.total(List(1)) should be (800)
  }

  test("Two of the same book") {
    pending
    BookStore.total(List(2, 2)) should be (1600)
  }

  test("Empty basket") {
    pending
    BookStore.total(List()) should be (0)
  }

  test("Two different books") {
    pending
    BookStore.total(List(1, 2)) should be (1520)
  }

  test("Three different books") {
    pending
    BookStore.total(List(1, 2, 3)) should be (2160)
  }

  test("Four different books") {
    pending
    BookStore.total(List(1, 2, 3, 4)) should be (2560)
  }

  test("Five different books") {
    pending
    BookStore.total(List(1, 2, 3, 4, 5)) should be (3000)
  }

  test("Two groups of four is cheaper than group of five plus group of three") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 5)) should be (5120)
  }

  test("Two groups of four is cheaper than groups of five and three") {
    pending
    BookStore.total(List(1, 1, 2, 3, 4, 4, 5, 5)) should be (5120)
  }

  test("Group of four plus group of two is cheaper than two groups of three") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 4)) should be (4080)
  }

  test("Two each of first 4 books and 1 copy each of rest") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5)) should be (5560)
  }

  test("Two copies of each book") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)) should be (6000)
  }

  test("Three copies of first book and 2 each of remaining") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 1)) should be (6800)
  }

  test("Three each of first 2 books and 2 each of remaining books") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 1, 2)) should be (7520)
  }

  test("Four groups of four are cheaper than two groups each of five and three") {
    pending
    BookStore.total(List(1, 1, 2, 2, 3, 3, 4, 5, 1, 1, 2, 2, 3, 3, 4, 5)) should be (10240)
  }
}