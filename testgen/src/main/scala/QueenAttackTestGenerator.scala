import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object QueenAttackTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/queen-attack.json")

    def getPosition(labeledTest: LabeledTest,
                    queenType: String): (Int, Int) = {
      val inputMap = labeledTest.result("input").asInstanceOf[Map[String, Any]]
      val queenMap = inputMap(queenType).asInstanceOf[Map[String, Any]]
      val positionMap = queenMap("position").asInstanceOf[Map[String, Int]]

      (positionMap("row"), positionMap("column"))
    }

    def getQueenPosition(labeledTest: LabeledTest): (Int, Int) =
      getPosition(labeledTest, "queen")

    def getWhiteQueenPosition(labeledTest: LabeledTest): (Int, Int) =
      getPosition(labeledTest, "white_queen")

    def getBlackQueenPosition(labeledTest: LabeledTest): (Int, Int) =
      getPosition(labeledTest, "black_queen")

    def toPositionArgs(position: (Int, Int)) =
      s"${position._1}, ${position._2}"

    def toCanAttackArgs(labeledTest: LabeledTest): String = {
      val whiteQueenArgs = toPositionArgs(getWhiteQueenPosition(labeledTest))
      val blackQueenArgs = toPositionArgs(getBlackQueenPosition(labeledTest))
      s"create($whiteQueenArgs), create($blackQueenArgs)"
    }

    def toCreateExpected(labeledTest: LabeledTest): String = {
      val expected = labeledTest.expected
      expected match {
        case Right(-1) => s"None"
        case Right(0) =>
          val args = toPositionArgs(getQueenPosition(labeledTest))
          s"Some(Queen($args))"
        case _ => throw new IllegalStateException
      }
    }

    def fromLabeledTestFromInput: ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val property = labeledTest.property
          val (clazz, args, expected) = property match {
            case "create" =>
              ("Queen",
                toPositionArgs(getQueenPosition(labeledTest)),
                toCreateExpected(labeledTest))
            case "canAttack" =>
              ("QueenAttack",
                toCanAttackArgs(labeledTest),
                labeledTest.expected.fold(_ => "false", _.toString))
            case _ => throw new IllegalStateException()
          }
          val sutCall = s"""$clazz.$property($args)"""
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTestFromInput,
        Seq(),
        Seq(" private def create(x: Int, y: Int): Queen = {",
          "    Queen.create(x, y) match {",
          "      case Some(q) => q",
          "      case None => fail(\"Error creating queen\")",
          "    }",
          "  }"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
