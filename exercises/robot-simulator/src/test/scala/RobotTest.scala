import org.scalatest.{Matchers, FunSuite}

class RobotTest  extends FunSuite with Matchers {
  test("create") {
    val robot = Robot(Bearing.North, (0, 0))
    robot.bearing should equal(Bearing.North)
    robot.coordinates should equal((0, 0))
  }

  test("advance - positive") { 
    pending
    Robot(Bearing.North, (0, 0)).advance should equal(Robot(Bearing.North, (0, 1)))
    Robot(Bearing.East, (0, 0)).advance should equal(Robot(Bearing.East, (1, 0)))
  }

  test("advance - negative") { 
    pending
    Robot(Bearing.South, (0, 0)).advance should equal(Robot(Bearing.South, (0, -1)))
    Robot(Bearing.West, (0, 0)).advance should equal(Robot(Bearing.West, (-1, 0)))
  }

  test("turning") { 
    pending
    Robot(Bearing.South, (0, 0)).turnRight should equal(Robot(Bearing.West, (0, 0)))
    Robot(Bearing.West, (0, 0)).turnLeft() should equal(Robot(Bearing.South, (0, 0)))
  }

  test("turning - edge cases") { 
    pending
    Robot(Bearing.West, (0, 0)).turnRight should equal(Robot(Bearing.North, (0, 0)))
    Robot(Bearing.North, (0, 0)).turnLeft should equal(Robot(Bearing.West, (0, 0)))
  }

  test("simulate Seurat") { 
    pending
    val seurat = Robot(Bearing.East, (-2, 1))
    val movedSeurat = seurat.simulate("RLAALAL")
    movedSeurat should equal(Robot(Bearing.West, (0, 2)))
  }

  test("simulate Erasmus") { 
    pending
    val erasmus = Robot(Bearing.North, (0, 0))
    val movedErasmus = erasmus.simulate("LAAARALA")
    movedErasmus should equal(Robot(Bearing.West, (-4, 1)))
  }

  test("simulate Chirox") { 
    pending
    val chirox = Robot(Bearing.East, (2, -7))
    val movedChirox = chirox.simulate("RRAAAAALA")
    movedChirox should equal(Robot(Bearing.South, (-3, -8)))
  }

  test("simulate Awesomo") { 
    pending
    val awesomo = Robot(Bearing.South, (8, 4))
    val movedAwesomo = awesomo.simulate("LAAARRRALLLL")
    movedAwesomo should equal(Robot(Bearing.North, (11, 5)))
  }
}
