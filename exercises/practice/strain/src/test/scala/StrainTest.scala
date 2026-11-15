import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate_tests.
  */
class StrainTest extends AnyFunSuite with Matchers:

  test("keep on empty list returns empty list"):
    val list = List()
    Strain.keep[Int](list, _ => true) should be(List())

  test("keeps everything"):
    pending
    val list = List(1, 3, 5)
    Strain.keep[Int](list, _ => true) should be(List(1, 3, 5))

  test("keeps nothing"):
    pending
    val list = List(1, 3, 5)
    Strain.keep[Int](list, _ => false) should be(List())

  test("keeps first and last"):
    pending
    val list = List(1, 2, 3)
    Strain.keep[Int](list, x => x % 2 == 1) should be(List(1, 3))

  test("keeps neither first nor last"):
    pending
    val list = List(1, 2, 3)
    Strain.keep[Int](list, x => x % 2 == 0) should be(List(2))

  test("keeps strings"):
    pending
    val list = List("apple", "zebra", "banana", "zombies", "cherimoya", "zealot")
    Strain.keep[String](list, x => x.startsWith("z")) should be(List("zebra", "zombies", "zealot"))

  test("keeps lists"):
    pending
    val list = List(List(1, 2, 3), List(5, 5, 5), List(5, 1, 2), List(2, 1, 2), List(1, 5, 2), List(2, 2, 1), List(1, 2, 5))
    Strain.keep[List[Int]](list, x => x.contains(5)) should be(List(List(5, 5, 5), List(5, 1, 2), List(1, 5, 2), List(1, 2, 5)))

  test("discard on empty list returns empty list"):
    pending
    val list = List()
    Strain.discard[Int](list, _ => true) should be(List())

  test("discards everything"):
    pending
    val list = List(1, 3, 5)
    Strain.discard[Int](list, _ => true) should be(List())

  test("discards nothing"):
    pending
    val list = List(1, 3, 5)
    Strain.discard[Int](list, _ => false) should be(List(1, 3, 5))

  test("discards first and last"):
    pending
    val list = List(1, 2, 3)
    Strain.discard[Int](list, x => x % 2 == 1) should be(List(2))

  test("discards neither first nor last"):
    pending
    val list = List(1, 2, 3)
    Strain.discard[Int](list, x => x % 2 == 0) should be(List(1, 3))

  test("discards strings"):
    pending
    val list = List("apple", "zebra", "banana", "zombies", "cherimoya", "zealot")
    Strain.discard[String](list, x => x.startsWith("z")) should be(List("apple", "banana", "cherimoya"))

  test("discards lists"):
    pending
    val list = List(List(1, 2, 3), List(5, 5, 5), List(5, 1, 2), List(2, 1, 2), List(1, 5, 2), List(2, 2, 1), List(1, 2, 5))
    Strain.discard[List[Int]](list, x => x.contains(5)) should be(List(List(1, 2, 3), List(2, 1, 2), List(2, 2, 1)))
