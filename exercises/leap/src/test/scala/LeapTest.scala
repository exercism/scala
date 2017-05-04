import org.scalatest._

class LeapTest extends FunSuite {
  test ("vanilla leap year") {
    assert(Year.isLeap(1996))
  }

  test ("any old year") {
    pending
    assert(!Year.isLeap(1997))
  }

  test("an even year") {
    pending
    assert(!Year.isLeap(1986))
  }

  test ("century") {
    pending
    assert(!Year.isLeap(1900))
  }

  test ("exceptional century") {
    pending
    assert(Year.isLeap(2000))
  }

  test("exceptional century that is no millenium") {
    pending
    assert(Year.isLeap(1600))
  }
}
