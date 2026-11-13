import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RobotSimulatorTest extends AnyFunSuite with Matchers:

  test("Create robot - at origin facing north"):
    val robot = Robot(Bearing.North, (0, 0))
    robot.bearing should be(Bearing.North)
    robot.coordinates should be((0, 0))

  test("Create robot - at negative position facing south"):
    pending
    val robot = Robot(Bearing.South, (-1, -1))
    robot.bearing should be(Bearing.South)
    robot.coordinates should be((-1, -1))

  test("Rotating clockwise - changes north to east"):
    pending
    Robot(Bearing.North, (0, 0)).simulate("R") should be(Robot(Bearing.East, (0, 0)))

  test("Rotating clockwise - changes east to south"):
    pending
    Robot(Bearing.East, (0, 0)).simulate("R") should be(Robot(Bearing.South, (0, 0)))

  test("Rotating clockwise - changes south to west"):
    pending
    Robot(Bearing.South, (0, 0)).simulate("R") should be(Robot(Bearing.West, (0, 0)))

  test("Rotating clockwise - changes west to north"):
    pending
    Robot(Bearing.West, (0, 0)).simulate("R") should be(Robot(Bearing.North, (0, 0)))

  test("Rotating counter-clockwise - changes north to west"):
    pending
    Robot(Bearing.North, (0, 0)).simulate("L") should be(Robot(Bearing.West, (0, 0)))

  test("Rotating counter-clockwise - changes west to south"):
    pending
    Robot(Bearing.West, (0, 0)).simulate("L") should be(Robot(Bearing.South, (0, 0)))

  test("Rotating counter-clockwise - changes south to east"):
    pending
    Robot(Bearing.South, (0, 0)).simulate("L") should be(Robot(Bearing.East, (0, 0)))

  test("Rotating counter-clockwise - changes east to north"):
    pending
    Robot(Bearing.East, (0, 0)).simulate("L") should be(Robot(Bearing.North, (0, 0)))

  test("Moving forward one - facing north increments Y"):
    pending
    Robot(Bearing.North, (0, 0)).simulate("A") should be(Robot(Bearing.North, (0, 1)))

  test("Moving forward one - facing south decrements Y"):
    pending
    Robot(Bearing.South, (0, 0)).simulate("A") should be(Robot(Bearing.South, (0, -1)))

  test("Moving forward one - facing east increments X"):
    pending
    Robot(Bearing.East, (0, 0)).simulate("A") should be(Robot(Bearing.East, (1, 0)))

  test("Moving forward one - facing west decrements X"):
    pending
    Robot(Bearing.West, (0, 0)).simulate("A") should be(Robot(Bearing.West, (-1, 0)))

  test("Follow series of instructions - moving east and north from README"):
    pending
    Robot(Bearing.North, (7, 3)).simulate("RAALAL") should be(Robot(Bearing.West, (9, 4)))

  test("Follow series of instructions - moving west and north"):
    pending
    Robot(Bearing.North, (0, 0)).simulate("LAAARALA") should be(Robot(Bearing.West, (-4, 1)))

  test("Follow series of instructions - moving west and south"):
    pending
    Robot(Bearing.East, (2, -7)).simulate("RRAAAAALA") should be(Robot(Bearing.South, (-3, -8)))

  test("Follow series of instructions - moving east and north"):
    pending
    Robot(Bearing.South, (8, 4)).simulate("LAAARRRALLLL") should be(Robot(Bearing.North, (11, 5)))
