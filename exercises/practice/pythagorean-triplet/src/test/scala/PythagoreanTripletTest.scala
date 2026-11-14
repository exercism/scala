import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PythagoreanTripletTest extends AnyFunSuite with Matchers:

  test("triplets whose sum is 12"):
    PythagoreanTriplet.pythagoreanTripletsSum(12) should be(Seq((3, 4, 5)))

  test("triplets whose sum is 108"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(108) should be(Seq((27, 36, 45)))

  test("triplets whose sum is 1000"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(1000) should be(Seq((200, 375, 425)))

  test("no matching triplets for 1001"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(1001) should be(Seq())

  test("returns all matching triplets"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(90) should be(Seq((9, 40, 41), (15, 36, 39)))

  test("several matching triplets"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(840) should be(
      Seq((40, 399, 401), (56, 390, 394), (105, 360, 375), (120, 350, 370), (140, 336, 364), (168, 315, 357), (210, 280, 350), (240, 252, 348)),
    )

  test("triplets for large number"):
    pending
    PythagoreanTriplet.pythagoreanTripletsSum(30000) should be(
      Seq((1200, 14375, 14425), (1875, 14000, 14125), (5000, 12000, 13000), (6000, 11250, 12750), (7500, 10000, 12500)),
    )
