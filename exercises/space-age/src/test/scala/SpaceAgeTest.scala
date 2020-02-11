import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
class SpaceAgeTest extends FunSuite with Matchers {

  test("age on Earth") {
    SpaceAge.onEarth(1000000000) should be (31.69 +- 0.01)
  }

  test("age on Mercury") {
    pending
    SpaceAge.onMercury(2134835688) should be (280.88 +- 0.01)
  }

  test("age on Venus") {
    pending
    SpaceAge.onVenus(189839836) should be (9.78 +- 0.01)
  }

  test("age on Mars") {
    pending
    SpaceAge.onMars(2.329871239E9) should be (39.25 +- 0.01)
  }

  test("age on Jupiter") {
    pending
    SpaceAge.onJupiter(901876382) should be (2.41 +- 0.01)
  }

  test("age on Saturn") {
    pending
    SpaceAge.onSaturn(3.0E9) should be (3.23 +- 0.01)
  }

  test("age on Uranus") {
    pending
    SpaceAge.onUranus(3.210123456E9) should be (1.21 +- 0.01)
  }

  test("age on Neptune") {
    pending
    SpaceAge.onNeptune(8.210123456E9) should be (1.58 +- 0.01)
  }
}