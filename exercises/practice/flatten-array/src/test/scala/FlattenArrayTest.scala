import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class FlattenArrayTest extends AnyFunSuite with Matchers:

  test("empty"):
    FlattenArray.flatten(List()) should be(List())

  test("no nesting"):
    pending
    FlattenArray.flatten(List(0, 1, 2)) should be(List(0, 1, 2))

  test("flattens a nested array"):
    pending
    FlattenArray.flatten(List(List(List()))) should be(List())

  test("flattens array with just integers present"):
    pending
    FlattenArray.flatten(List(1, List(2, 3, 4, 5, 6, 7), 8)) should be(List(1, 2, 3, 4, 5, 6, 7, 8))

  test("5 level nesting"):
    pending
    FlattenArray.flatten(List(0, 2, List(List(2, 3), 8, 100, 4, List(List(List(50)))), -2)) should be(List(0, 2, 2, 3, 8, 100, 4, 50, -2))

  test("6 level nesting"):
    pending
    FlattenArray.flatten(List(1, List(2, List(List(3)), List(4, List(List(5))), 6, 7), 8)) should be(List(1, 2, 3, 4, 5, 6, 7, 8))

  test("null values are omitted from the final result"):
    pending
    FlattenArray.flatten(List(1, 2, null)) should be(List(1, 2))

  test("consecutive null values at the front of the array are omitted from the final result"):
    pending
    FlattenArray.flatten(List(null, null, 3)) should be(List(3))

  test("consecutive null values in the middle of the array are omitted from the final result"):
    pending
    FlattenArray.flatten(List(1, null, null, 4)) should be(List(1, 4))

  test("6 level nested array with null values"):
    pending
    FlattenArray.flatten(List(0, 2, List(List(2, 3), 8, List(List(100)), null, List(List(null))), -2)) should be(List(0, 2, 2, 3, 8, 100, -2))

  test("all values in nested array are null"):
    pending
    FlattenArray.flatten(List(null, List(List(List(null))), null, null, List(List(null, null), null), null)) should be(List())
