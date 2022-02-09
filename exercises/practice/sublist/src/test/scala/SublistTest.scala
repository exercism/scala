import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class SublistTest extends AnyFunSuite with Matchers {

  test("empty lists") {
    Sublist.sublist(List(), List()) should be(Sublist.Equal)
  }

  test("empty list within non empty list") {
    pending
    Sublist.sublist(List(), List(1, 2, 3)) should be(Sublist.Sublist)
  }

  test("non empty list contains empty list") {
    pending
    Sublist.sublist(List(1, 2, 3), List()) should be(Sublist.Superlist)
  }

  test("list equals itself") {
    pending
    Sublist.sublist(List(1, 2, 3), List(1, 2, 3)) should be(Sublist.Equal)
  }

  test("different lists") {
    pending
    Sublist.sublist(List(1, 2, 3), List(2, 3, 4)) should be(Sublist.Unequal)
  }

  test("false start") {
    pending
    Sublist.sublist(List(1, 2, 5), List(0, 1, 2, 3, 1, 2, 5, 6)) should be(
      Sublist.Sublist)
  }

  test("consecutive") {
    pending
    Sublist.sublist(List(1, 1, 2), List(0, 1, 1, 1, 2, 1, 2)) should be(
      Sublist.Sublist)
  }

  test("sublist at start") {
    pending
    Sublist.sublist(List(0, 1, 2), List(0, 1, 2, 3, 4, 5)) should be(
      Sublist.Sublist)
  }

  test("sublist in middle") {
    pending
    Sublist.sublist(List(2, 3, 4), List(0, 1, 2, 3, 4, 5)) should be(
      Sublist.Sublist)
  }

  test("sublist at end") {
    pending
    Sublist.sublist(List(3, 4, 5), List(0, 1, 2, 3, 4, 5)) should be(
      Sublist.Sublist)
  }

  test("at start of superlist") {
    pending
    Sublist.sublist(List(0, 1, 2, 3, 4, 5), List(0, 1, 2)) should be(
      Sublist.Superlist)
  }

  test("in middle of superlist") {
    pending
    Sublist.sublist(List(0, 1, 2, 3, 4, 5), List(2, 3)) should be(
      Sublist.Superlist)
  }

  test("at end of superlist") {
    pending
    Sublist.sublist(List(0, 1, 2, 3, 4, 5), List(3, 4, 5)) should be(
      Sublist.Superlist)
  }

  test("first list missing element from second list") {
    pending
    Sublist.sublist(List(1, 3), List(1, 2, 3)) should be(Sublist.Unequal)
  }

  test("second list missing element from first list") {
    pending
    Sublist.sublist(List(1, 2, 3), List(1, 3)) should be(Sublist.Unequal)
  }

  test("order matters to a list") {
    pending
    Sublist.sublist(List(1, 2, 3), List(3, 2, 1)) should be(Sublist.Unequal)
  }

  test("same digits but different numbers") {
    pending
    Sublist.sublist(List(1, 0, 1), List(10, 1)) should be(Sublist.Unequal)
  }
}
