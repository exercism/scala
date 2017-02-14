import org.scalatest.{FunSuite, Matchers}

class BinarySearchTest extends FunSuite with Matchers {

  test("finds a value in an array with one element") {
    val result = BinarySearch.search(Array(6), 6)
    result should be (Some(0))
  }

  test("finds a value in the middle of an array") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 6)
    result should be (Some(3))
  }

  test("finds a value at the beginning of an array") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 1)
    result should be (Some(0))
  }

  test("finds a value at the end of an array") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 11)
    result should be (Some(6))
  }

  test("finds a value in an array of odd length") {
    pending
    val result = BinarySearch.search(Array(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634), 144)
    result should be (Some(9))
  }

  test("finds a value in an array of even length") {
    pending
    val result = BinarySearch.search(Array(1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377), 21)
    result should be (Some(5))
  }

  test("identifies that a value is not included in the array") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 7)
    result should be (None)
  }

  test("a value smaller than the array's smallest value is not included") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 0)
    result should be (None)
  }

  test("a value larger than the array's largest value is not included") {
    pending
    val result = BinarySearch.search(Array(1, 3, 4, 6, 8, 9, 11), 13)
    result should be (None)
  }

  test("nothing is included in an empty array") {
    pending
    val result = BinarySearch.search(Array[Int](), 1)
    result should be (None)
  }
}
