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

  it should "handle hour rolls over continuously" in {
    Clock(100, 0) should be (Clock(4, 0))
  }

  it should "implement 60 min as the next hour" in {
    Clock(1, 60) should be (Clock(2, 0))
  }

  it should "construct consistently between constructors" in {
    Clock(60) should be (Clock(1, 0))
  }

  it should "support rolling over minutes" in {
    Clock(0, 160) should be (Clock(2, 40))
  }

  it should "handle minutes rolling over consistently" in {
    Clock(0, 1723) should be (Clock(4, 43))
  }

  it should "support rolling over both hours and minutes" in {
    Clock(25, 160) should be (Clock(3, 40))
  }

  it should "handle hours and minutes rolling over consistently" in {
    Clock(201, 3001) should be (Clock(11, 1))
  }

  it should "handle hours and minutes rolling over to midnight" in {
    Clock(72, 8640) should be (Clock(0, 0))
  }

  it should "handle negative hour" in {
    Clock(-1, 15) should be (Clock(23, 15))
  }

  it should "handle rolling over negative hour" in {
    Clock(-25, 0) should be (Clock(23, 0))
  }

  it should "handle negative hours rolling over consistently" in {
    Clock(-91, 0) should be (Clock(5, 0))
  }

  it should "handle negative minute" in {
    Clock(1, -40) should be (Clock(0, 20))
  }

  it should "handle rolling over negative minute" in {
    Clock(1, -160) should be (Clock(22, 20))
  }

  it should "handle negative minutes rolling over consistently" in {
    Clock(1, -4820) should be (Clock(16, 40))
  }

  it should "handle negative hour and minutes roll over" in {
    Clock(-25, -160) should be (Clock(20, 20))
  }

  it should "handle negative hour and minutes rolling over consistently" in {
    Clock(-121, -5810) should be (Clock(22, 10))
  }

  it should "add minutes" in {
    Clock(10, 0) + Clock(3) should be (Clock(10, 3))
  }

  it should "add no minutes" in {
    Clock(6, 41) + Clock(0) should be (Clock(6, 41))
  }

  it should "add to next hour" in {
    Clock(0, 45) + Clock(40) should be (Clock(1, 25))
  }

  it should "add more than one hour" in {
    Clock(10, 0) + Clock(61) should be (Clock(11, 1))
  }

  it should "add more than two hours with carry" in {
    Clock(0, 45) + Clock(160) should be (Clock(3, 25))
  }

  it should "add across midnight" in {
    Clock(23, 59) + Clock(2) should be (Clock(0, 1))
  }

  it should "add more than one day (1500 min == 25 hours)" in {
    Clock(5, 32) + Clock(1500) should be (Clock(6, 32))
  }

  it should "add more than 2 days" in {
    Clock(1, 1) + Clock(3500) should be (Clock(11, 21))
  }

  it should "subtract minutes" in {
    Clock(10, 3) - Clock(3) should be (Clock(10, 0))
  }

  it should "subtract to previous hour" in {
    Clock(10, 3) - Clock(30) should be (Clock(9, 33))
  }

  it should "subtract more than an hour" in {
    Clock(10, 3) - Clock(70) should be (Clock(8, 53))
  }

  it should "subtract across midnight" in {
    Clock(0, 3) - Clock(4) should be (Clock(23, 59))
  }

  it should "subtract more than two hours" in {
    Clock(0, 0) - Clock(160) should be (Clock(21, 20))
  }

  it should "subtract more than two hours with borrow" in {
    Clock(6, 15) - Clock(160) should be (Clock(3, 35))
  }

  it should "subtract more than one day (1500 min = 25 hrs)" in {
    Clock(5, 32) - Clock(1500) should be (Clock(4, 32))
  }

  it should "subtract more than two days" in {
    Clock(2, 20) - Clock(3000) should be (Clock(0, 20))
  }

  it should "handle equality of clocks with same time" in {
    Clock(15, 37) should be (Clock(15, 37))
  }

  it should "handle equality of clocks that are one minute apart" in {
    Clock(15, 36) should not be (Clock(15, 37))
  }

  it should "handle equality of clocks that are one hour apart" in {
    Clock(14, 37) should not be (Clock(15, 37))
  }

  it should "handle equality of clocks with hour overflow" in {
    Clock(10, 37) should be (Clock(34, 37))
  }

  it should "handle equality of clocks with hour overflow by several days" in {
    Clock(3, 11) should be (Clock(99, 11))
  }

  it should "handle equality of clocks with negative hour" in {
    Clock(22, 40) should be (Clock(-2, 40))
  }

  it should "handle equality of clocks with negative hour that wraps" in {
    Clock(17, 3) should be (Clock(-31, 3))
  }

  it should "handle equality of clocks with negative hour that wraps multiple times" in {
    Clock(13, 49) should be (Clock(-83, 49))
  }

  it should "handle equality of clocks with minute overflow" in {
    Clock(0, 1) should be (Clock(0, 1441))
  }

  it should "handle equality of clocks with minute overflow by several days" in {
    Clock(2, 2) should be (Clock(2, 4322))
  }

  it should "handle equality of clocks with negative minute" in {
    Clock(2, 40) should be (Clock(3, -20))
  }

  it should "handle equality of clocks with negative minute that wraps" in {
    Clock(4, 10) should be (Clock(5, -1490))
  }

  it should "handle equality of clocks with negative minute that wraps mutiple times" in {
    Clock(6, 15) should be (Clock(6, -4305))
  }

  it should "handle equality of clocks with negative hours and minute" in {
    Clock(7, 32) should be (Clock(-12, -268))
  }

  it should "handle equality of clocks with negative hours and minute that wrap" in {
    Clock(18, 7) should be (Clock(-54, -11513))
  }
}
