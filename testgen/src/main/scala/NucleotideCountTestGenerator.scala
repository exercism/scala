import testgen._
import TestSuiteBuilder._
import java.io.File

object NucleotideCountTestGenerator {
  
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/nucleotide-count.json")

    def mapExpectedToString(arg: Map[String, Int]): String = {
      s"Right(Map(${arg.toSeq
        .sortBy(_._1)
        .map{case (key, value) => "\'" + key.toString + "\' -> " + value.toString}
        .mkString(",\n")}))"
    }

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(s: String) => "true"
        case Right(m: Map[String, Int]) => mapExpectedToString(m)
        case _ => throw new IllegalArgumentException
      }
    }

    def toSutCall(labeledTest: LabeledTest, argNames: String*): String = {
      val args = sutArgsFromInput(labeledTest.result, argNames: _*)
      val property = labeledTest.property
      labeledTest.expected match {
        case Left(_) => s"""new DNA($args).$property.isLeft"""
        case Right(_) => s"""new DNA($args).$property"""
        case _ => throw new IllegalArgumentException
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val sutCall = toSutCall(labeledTest, argNames: _*)
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }


    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("strand"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}