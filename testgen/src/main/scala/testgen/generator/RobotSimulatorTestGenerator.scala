package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object RobotSimulatorTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/robot-simulator.json")

    def getPosition(labeledTest: LabeledTest): (Int, Int) =
      val inputMap = fromInputMap(labeledTest.result, "position")
        .asInstanceOf[Map[String, Any]]
      getPosition2(inputMap)

    def getPosition2(positionMap: Map[String, Any]): (Int, Int) =
      (positionMap("x").asInstanceOf[Int], positionMap("y").asInstanceOf[Int])

    def getDirection(labeledTest: LabeledTest): String =
      fromInputMap(labeledTest.result, "direction").toString

    def toPositionArgs(position: (Int, Int)): String =
      s"${position._1}, ${position._2}"

    def getExpectedMap(labeledTest: LabeledTest): Map[String, Any] =
      labeledTest.result("expected").asInstanceOf[Map[String, Any]]

    def directionToBearing(direction: String): String =
      direction.toLowerCase match
        case "north" => "Bearing.North"
        case "south" => "Bearing.South"
        case "east"  => "Bearing.East"
        case "west"  => "Bearing.West"

    def toCreateSutCall(labeledTest: LabeledTest): String =
      val bearing = directionToBearing(getDirection(labeledTest))
      s"Robot($bearing, (${toPositionArgs(getPosition(labeledTest))}))"

    def toCreateExpected(labeledTest: LabeledTest): String =
      val robotMap     = getExpectedMap(labeledTest)
      val bearing      = directionToBearing(robotMap("direction").toString)
      val positionArgs = toPositionArgs(
        getPosition2(
          robotMap("position")
            .asInstanceOf[Map[String, Int]],
        ),
      )
      s"Robot($bearing, ($positionArgs))"

    def toTurnFunction(map: Map[String, Any]) =
      map.get("direction") match
        case Some(_) => "bearing"
        case None    => "coordinates"

    def toTurnSutCall(labeledTest: LabeledTest): String =
      val property = labeledTest.property
      val expected = getExpectedMap(labeledTest)
      s"${toCreateSutCall(labeledTest)}.$property.${toTurnFunction(expected)}"

    def toTurnExpected(labeledTest: LabeledTest): String =
      val expected = getExpectedMap(labeledTest)
      expected.foreach { case (k, v) => System.out.println(s"$k") }
      expected.get("direction") match
        case Some(s: String) => directionToBearing(s)
        case None            =>
          val positionMap = expected("position").asInstanceOf[Map[String, Int]]
          s"(${toPositionArgs(getPosition2(positionMap))})"

    def toInstructSutCall(labeledTest: LabeledTest): String =
      val instructions = fromInputMap(labeledTest.result, "instructions")
      s"""${toCreateSutCall(labeledTest)}.simulate("$instructions")"""

    def toInstructExpected(labeledTest: LabeledTest): String =
      toCreateExpected(labeledTest)

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val property            = labeledTest.property
        val (sutCall, expected) = property match
          case "create"                             =>
            (toCreateSutCall(labeledTest), toCreateExpected(labeledTest))
          case "turnRight" | "turnLeft" | "advance" =>
            (toTurnSutCall(labeledTest), toTurnExpected(labeledTest))
          case "instructions"                       =>
            (toInstructSutCall(labeledTest), toInstructExpected(labeledTest))
          case _                                    => throw new IllegalStateException()
        TestCaseData(labeledTest.parentDescriptions.mkString(" - ") + " - " + labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTest())

    println(s"-------------")
    println(code)
    println(s"-------------")
