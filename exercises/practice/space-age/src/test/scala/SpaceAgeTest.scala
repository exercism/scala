import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class SpaceAgeTest extends AnyFunSuite with Matchers:

  test("age on Earth"):
    SpaceAge.onEarth(1000000000) should be(31.69 +- 0.01)

  test("age on Mercury"):
    pending
    SpaceAge.onMercury(2134835688) should be(280.88 +- 0.01)

  test("age on Venus"):
    pending
    SpaceAge.onVenus(189839836) should be(9.78 +- 0.01)

  test("age on Mars"):
    pending
    SpaceAge.onMars(2129871239) should be(35.88 +- 0.01)

  test("age on Jupiter"):
    pending
    SpaceAge.onJupiter(901876382) should be(2.41 +- 0.01)

  test("age on Saturn"):
    pending
    SpaceAge.onSaturn(2000000000) should be(2.15 +- 0.01)

  test("age on Uranus"):
    pending
    SpaceAge.onUranus(1210123456) should be(0.46 +- 0.01)

  test("age on Neptune"):
    pending
    SpaceAge.onNeptune(1821023456) should be(0.35 +- 0.01)
