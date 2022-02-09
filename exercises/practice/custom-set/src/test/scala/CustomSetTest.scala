import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** @version 1.3.0 */
class CustomSetTest extends AnyFunSuite with Matchers {

  // Empty test cases - Returns true if the set contains no elements
  test("sets with no elements are empty") {
    val set = CustomSet.fromList(List())
    CustomSet.empty(set) should be(true)
  }

  test("sets with elements are not empty") {
    pending
    val set = CustomSet.fromList(List(1))
    CustomSet.empty(set) should be(false)
  }

  // Contains test cases - Sets can report if they contain an element
  test("nothing is contained in an empty set") {
    pending
    val set = CustomSet.fromList(List())
    CustomSet.member(set, 1) should be(false)
  }

  test("when the element is in the set") {
    pending
    val set = CustomSet.fromList(List(1, 2, 3))
    CustomSet.member(set, 1) should be(true)
  }

  test("when the element is not in the set") {
    pending
    val set = CustomSet.fromList(List(1, 2, 3))
    CustomSet.member(set, 4) should be(false)
  }

  // Subset test cases - A set is a subset if all of its elements are contained in the other set
  test("empty set is a subset of another empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    CustomSet.isSubsetOf(set1, set2) should be(true)
  }

  test("empty set is a subset of non-empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(1))
    CustomSet.isSubsetOf(set1, set2) should be(true)
  }

  test("non-empty set is not a subset of empty set") {
    pending
    val set1 = CustomSet.fromList(List(1))
    val set2 = CustomSet.fromList(List())
    CustomSet.isSubsetOf(set1, set2) should be(false)
  }

  test("set is a subset of set with exact same elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(1, 2, 3))
    CustomSet.isSubsetOf(set1, set2) should be(true)
  }

  test("set is a subset of larger set with same elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(4, 1, 2, 3))
    CustomSet.isSubsetOf(set1, set2) should be(true)
  }

  test("set is not a subset of set that does not contain its elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(4, 1, 3))
    CustomSet.isSubsetOf(set1, set2) should be(false)
  }

  // Disjoint test cases - Sets are disjoint if they share no elements
  test("the empty set is disjoint with itself") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    CustomSet.isDisjointFrom(set1, set2) should be(true)
  }

  test("empty set is disjoint with non-empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(1))
    CustomSet.isDisjointFrom(set1, set2) should be(true)
  }

  test("non-empty set is disjoint with empty set") {
    pending
    val set1 = CustomSet.fromList(List(1))
    val set2 = CustomSet.fromList(List())
    CustomSet.isDisjointFrom(set1, set2) should be(true)
  }

  test("sets are not disjoint if they share an element") {
    pending
    val set1 = CustomSet.fromList(List(1, 2))
    val set2 = CustomSet.fromList(List(2, 3))
    CustomSet.isDisjointFrom(set1, set2) should be(false)
  }

  test("sets are disjoint if they share no elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2))
    val set2 = CustomSet.fromList(List(3, 4))
    CustomSet.isDisjointFrom(set1, set2) should be(true)
  }

  // Equal test cases - Sets with the same elements are equal
  test("empty sets are equal") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    CustomSet.isEqual(set1, set2) should be(true)
  }

  test("empty set is not equal to non-empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(1, 2, 3))
    CustomSet.isEqual(set1, set2) should be(false)
  }

  test("non-empty set is not equal to empty set") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List())
    CustomSet.isEqual(set1, set2) should be(false)
  }

  test("sets with the same elements are equal") {
    pending
    val set1 = CustomSet.fromList(List(1, 2))
    val set2 = CustomSet.fromList(List(2, 1))
    CustomSet.isEqual(set1, set2) should be(true)
  }

  test("sets with different elements are not equal") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(1, 2, 4))
    CustomSet.isEqual(set1, set2) should be(false)
  }

  test("set is not equal to larger set with same elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(1, 2, 3, 4))
    CustomSet.isEqual(set1, set2) should be(false)
  }

  // Add test cases - Unique elements can be added to a set
  test("add to empty set") {
    pending
    val set = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List(3))
    CustomSet.isEqual(CustomSet.insert(set, 3), expected) should be(true)
  }

  test("add to non-empty set") {
    pending
    val set = CustomSet.fromList(List(1, 2, 4))
    val expected = CustomSet.fromList(List(1, 2, 3, 4))
    CustomSet.isEqual(CustomSet.insert(set, 3), expected) should be(true)
  }

  test("adding an existing element does not change the set") {
    pending
    val set = CustomSet.fromList(List(1, 2, 3))
    val expected = CustomSet.fromList(List(1, 2, 3))
    CustomSet.isEqual(CustomSet.insert(set, 3), expected) should be(true)
  }

  // Intersection test cases - Intersection returns a set of all shared elements
  test("intersection of two empty sets is an empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be(
      true)
  }

  test("intersection of an empty set and non-empty set is an empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(3, 2, 5))
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be(
      true)
  }

  test("intersection of a non-empty set and an empty set is an empty set") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3, 4))
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be(
      true)
  }

  test("intersection of two sets with no shared elements is an empty set") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3))
    val set2 = CustomSet.fromList(List(4, 5, 6))
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be(
      true)
  }

  test(
    "intersection of two sets with shared elements is a set of the shared elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3, 4))
    val set2 = CustomSet.fromList(List(3, 2, 5))
    val expected = CustomSet.fromList(List(2, 3))
    CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be(
      true)
  }

  // Difference test cases - Difference (or Complement) of a set is a set of all elements that are only in the first set
  test("difference of two empty sets is an empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.difference(set1, set2), expected) should be(
      true)
  }

  test("difference of empty set and non-empty set is an empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(3, 2, 5))
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.difference(set1, set2), expected) should be(
      true)
  }

  test("difference of a non-empty set and an empty set is the non-empty set") {
    pending
    val set1 = CustomSet.fromList(List(1, 2, 3, 4))
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List(1, 2, 3, 4))
    CustomSet.isEqual(CustomSet.difference(set1, set2), expected) should be(
      true)
  }

  test(
    "difference of two non-empty sets is a set of elements that are only in the first set") {
    pending
    val set1 = CustomSet.fromList(List(3, 2, 1))
    val set2 = CustomSet.fromList(List(2, 4))
    val expected = CustomSet.fromList(List(1, 3))
    CustomSet.isEqual(CustomSet.difference(set1, set2), expected) should be(
      true)
  }

  // Union test cases - Union returns a set of all elements in either set
  test("union of empty sets is an empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List())
    CustomSet.isEqual(CustomSet.union(set1, set2), expected) should be(true)
  }

  test("union of an empty set and non-empty set is the non-empty set") {
    pending
    val set1 = CustomSet.fromList(List())
    val set2 = CustomSet.fromList(List(2))
    val expected = CustomSet.fromList(List(2))
    CustomSet.isEqual(CustomSet.union(set1, set2), expected) should be(true)
  }

  test("union of a non-empty set and empty set is the non-empty set") {
    pending
    val set1 = CustomSet.fromList(List(1, 3))
    val set2 = CustomSet.fromList(List())
    val expected = CustomSet.fromList(List(1, 3))
    CustomSet.isEqual(CustomSet.union(set1, set2), expected) should be(true)
  }

  test("union of non-empty sets contains all unique elements") {
    pending
    val set1 = CustomSet.fromList(List(1, 3))
    val set2 = CustomSet.fromList(List(2, 3))
    val expected = CustomSet.fromList(List(3, 2, 1))
    CustomSet.isEqual(CustomSet.union(set1, set2), expected) should be(true)
  }
}
