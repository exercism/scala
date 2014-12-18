import org.scalatest.{Matchers, FlatSpec}

class ClockTest extends FlatSpec with Matchers {
  it should "construct from hour/min" in {
    Clock(8, 0).toString should be ("08:00")
    Clock(11, 9).toString should be ("11:09")
  }

  it should "construct from minutes" in {
    Clock(3).toString should be ("00:03")
  }

  it should "implement equals" in {
    Clock(15, 37) should be (Clock(15, 37))
    Clock(15, 37) should not be Clock(15, 36)
  }

  it should "support wraparound" in {
    Clock(24, 0) should be (Clock(0, 0))
    Clock(25, 0) should be (Clock(1, 0))
  }

  it should "construct consistently between constructors" in {
    Clock(60) should be (Clock(1, 0))
  }

  it should "add clocks" in {
    Clock(10, 0) + Clock(3) should be (Clock(10, 3))
    Clock(10, 0) + Clock(61) should be (Clock(11, 1))
    Clock(23, 30) + Clock(60) should be (Clock(0, 30))
  }

  it should "subtract clocks" in {
    Clock(10, 0) - Clock(90) should be (Clock(8, 30))
    Clock(0, 30) - Clock(60) should be (Clock(23, 30))
  }
}
