import org.scalatest.{FunSuite, Matchers}

class BinarySearchTest extends FunSuite with Matchers {
  test("finds a value in an array with one element") {
    pending
    val elements = Array[Int](6)
    val result = BinarySearch.search(elements, 6)
    assert(result.contains(0))
  }

  test("finds a value in the middle of an array") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 6)
    assert(result.contains(3))
  }

  test("finds a value at the beginning of an array") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 1)
    assert(result.contains(0))
  }

  test("finds a value at the end of an array") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 11)
    assert(result.contains(6))
  }

  test("finds a value in an array of odd length") {
    pending
    val elements = Array[Int](1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 634)
    val result = BinarySearch.search(elements, 144)
    assert(result.contains(9))
  }

  test("finds a value in an array of even length") {
    pending
    val elements = Array[Int](1, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377)
    val result = BinarySearch.search(elements, 21)
    assert(result.contains(5))
  }

  test("identifies that a value is not included in the array") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 7)
    assert(result.isEmpty)
  }

  test("a value smaller than the array's smallest value is not included") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 0)
    assert(result.isEmpty)
  }

  test("a value larger than the array's largest value is not included") {
    pending
    val elements = Array[Int](1, 3, 4, 6, 8, 9, 11)
    val result = BinarySearch.search(elements, 13)
    assert(result.isEmpty)
  }

  test("nothing is included in an empty array") {
    pending
    val elements = Array[Int]()
    val result = BinarySearch.search(elements, 1)
    assert(result.isEmpty)
  }

}