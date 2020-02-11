import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object PalindromeProductsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/palindrome-products.json")

    def toStringFromMap(m: Map[String, Any]): String =
      s"Some((${m("value").toString}, " +
        s"Set(${m("factors").asInstanceOf[List[List[Int]]].map(xs => "(" + xs.map(_.toString).mkString(", ") + ")").mkString(", ")})))"


    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "None"
        case Right(m: Map[String, any]) => toStringFromMap(m)
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall = s"""$sut($args).$property"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTestFromInput("min", "max"),
        Seq(),
        Seq("// PalindromeProducts largest call is expecting a return type of",
            "// Option[(Int, Set[(Int, Int)])] - None is expected for error cases.",
            "// Some is expected for valid cases. The first element of the tuple ",
            "// is the largest palindrome product value. The second element of the",
            "// tuple is the list of factors.",
            "// PalindromeProducts smallest call is expecting a return type of",
            "// Option[(Int, Set[(Int, Int)])] - None is expected for error cases.",
            "// Some is expected for valid cases. The first element of the tuple ",
            "// is the smallest palindrome product value. The second element of the",
            "// tuple is the list of factors."))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
