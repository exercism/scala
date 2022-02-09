import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.3.0 */
class BinarySearchTest extends AnyFunSuite with Matchers {

  test("finds a value in an array with one element") {
    BinarySearch.find(List(6), 6) should be(Some(0))
  }

  test("finds a value in the middle of an array") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 6) should be(Some(3))
  }

  test("finds a value at the beginning of an array") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 1) should be(Some(0))
  }

  test("finds a value at the end of an array") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 11) should be(Some(6))
  }

  test("finds a value in an array of odd length") {
    pending
    BinarySearch.find(List(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634),
                      144) should be(Some(9))
  }

  test("finds a value in an array of even length") {
    pending
    BinarySearch.find(List(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377), 21) should be(
      Some(5))
  }

  test("identifies that a value is not included in the array") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 7) should be(None)
  }

  test("a value smaller than the array's smallest value is not found") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 0) should be(None)
  }

  test("a value larger than the array's largest value is not found") {
    pending
    BinarySearch.find(List(1, 3, 4, 6, 8, 9, 11), 13) should be(None)
  }

  test("nothing is found in an empty array") {
    pending
    BinarySearch.find(List(), 1) should be(None)
  }

  test("nothing is found when the left and right bounds cross") {
    pending
    BinarySearch.find(List(1, 2), 0) should be(None)
  }
}
