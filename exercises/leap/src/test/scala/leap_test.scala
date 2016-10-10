import org.scalatest._

class LeapTest extends FunSuite {
  test ("vanilla leap year") {
    assert(Year(1996).isLeap)
  }

  test ("any old year") {
    pending
    assert(!Year(1997).isLeap)
  }

  test("an even year") {
    pending
    assert(!Year(1986).isLeap)
  }

  test ("century") {
    pending
    assert(!Year(1900).isLeap)
  }

  test ("exceptional century") {
    pending
    assert(Year(2000).isLeap)
  }

  test("exceptional century that is no millenium") {
    pending
    assert(Year(1600).isLeap)
  }
}
