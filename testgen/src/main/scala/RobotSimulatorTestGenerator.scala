import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object RobotSimulatorTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/robot-simulator.json")

    def getPositionArgs(robotMap: Map[String, String]): String =
      robotMap("position").stripPrefix("(").stripSuffix(")")

    def getRobotMap(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("robot").
        asInstanceOf[Map[String, String]]
    }

    def getExpectedMap(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("expected").
        asInstanceOf[Map[String, String]]
    }

    def directionToBearing(direction: String): String =
      direction.toLowerCase match {
        case "north" => "Bearing.North"
        case "south" => "Bearing.South"
        case "east" => "Bearing.East"
        case "west" => "Bearing.West"
      }

    def toCreateSutCall(labeledTest: LabeledTest): String = {
      val robotMap = getRobotMap(labeledTest)
      val bearing = directionToBearing(robotMap("direction"))
      s"Robot($bearing, (${getPositionArgs(robotMap)}))"
    }

    def toCreateExpected(labeledTest: LabeledTest): String = {
      val robotMap = getExpectedMap(labeledTest)
      val bearing = directionToBearing(robotMap("direction"))
      s"Robot($bearing, (${getPositionArgs(robotMap)}))"
    }

    def toTurnFunction(map: Map[String, String]) =
      map.get("direction") match {
        case Some(_) => "bearing"
        case None => "coordinates"
      }

    def toTurnSutCall(labeledTest: LabeledTest): String = {
      val property = labeledTest.property
      val expected = getExpectedMap(labeledTest)
      s"${toCreateSutCall(labeledTest)}.$property.${toTurnFunction(expected)}"
    }

    def toTurnExpected(labeledTest: LabeledTest): String = {
      val expected = getExpectedMap(labeledTest)
      expected.get("direction") match {
        case Some(s) => directionToBearing(s)
        case None => s"(${getPositionArgs(expected)})"
      }
    }

    def toInstructSutCall(labeledTest: LabeledTest): String = {
      val property = labeledTest.property
      val instructions = labeledTest.result("instructions")
      s"""${toCreateSutCall(labeledTest)}.simulate("$instructions")"""
    }

    def toInstructExpected(labeledTest: LabeledTest): String =
      toCreateExpected(labeledTest)

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val property = labeledTest.property
          val (sutCall, expected) = property match {
            case "create" =>
              (toCreateSutCall(labeledTest),
                toCreateExpected(labeledTest))
            case "turnRight" | "turnLeft" | "advance" =>
              (toTurnSutCall(labeledTest),
                toTurnExpected(labeledTest))
            case "instructions" =>
              (toInstructSutCall(labeledTest),
                toInstructExpected(labeledTest))
            case _ => throw new IllegalStateException()
          }
          TestCaseData(labeledTest.parentDescriptions.mkString(" - ") + " - " + labeledTest.description,
            sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTest())

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
