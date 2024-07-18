package testgen
package generator

import java.io.File

import play.twirl.api.{Template1, Txt}
import TestSuiteBuilder.*

object ComplexNumberTestGenerator:

  def main(args: Array[String]): Unit =
    def toString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Right(n: Double)     => s"""$n +- equalityEpsilon"""
        case Right(n: Int)        => f"""$n%.1f +- equalityEpsilon"""
        case Right(xs: List[Any]) => s"""ComplexNumber(${toStringArg(xs.head)}, ${toStringArg(xs.tail.head)})"""
        case _                    => throw new IllegalArgumentException()

    def toStringArg(arg: Any) =
      System.out.println(arg.toString)
      arg match
        case "pi"    => "math.Pi"
        case "e"     => "math.E"
        case "ln(2)" => "math.log(2)"
        case _       => arg.toString

    def getZArg(labeledTest: LabeledTest): (String, String) =
      val inputMap = labeledTest.result("input").asInstanceOf[Map[String, Any]]
      val z        = inputMap("z").asInstanceOf[List[Any]]
      (toStringArg(z.head), toStringArg(z.tail.head))

    def getZArgs(labeledTest: LabeledTest): ((Int, Int), (Int, Int)) =
      val inputMap = labeledTest.result("input").asInstanceOf[Map[String, Any]]
      val z1       = inputMap("z1").asInstanceOf[List[Int]]
      val z2       = inputMap("z2").asInstanceOf[List[Int]]
      ((z1.head, z1.tail.head), (z2.head, z2.tail.head))

    def createAddTestCase(labeledTest: LabeledTest) =
      createArithTestCase(labeledTest, "+")

    def createSubTestCase(labeledTest: LabeledTest) =
      createArithTestCase(labeledTest, "-")

    def createMulTestCase(labeledTest: LabeledTest) =
      createArithTestCase(labeledTest, "*")

    def createDivTestCase(labeledTest: LabeledTest) =
      createArithTestCase(labeledTest, "/")

    def createArithTestCase(labeledTest: LabeledTest, op: String) =
      val (z1, z2) = getZArgs(labeledTest)
      val sutCall  =
        s"""val result = ComplexNumber(${z1._1}, ${z1._2}) $op ComplexNumber(${z2._1}, ${z2._2})
           |assertEquals(result, ${toString(labeledTest.expected)})""".stripMargin
      TestCaseData(labeledTest.description, sutCall, "")

    def createRealTestCase(labeledTest: LabeledTest) =
      val zArg    = getZArg(labeledTest)
      val sutCall = s"""ComplexNumber(real = ${zArg._1}).${labeledTest.property} should be (${toString(labeledTest.expected)})"""
      TestCaseData(labeledTest.description, sutCall, "")

    def createImaginaryTestCase(labeledTest: LabeledTest) =
      val zArg    = getZArg(labeledTest)
      val sutCall = s"""ComplexNumber(imaginary = ${zArg._2}).${labeledTest.property} should be (${toString(labeledTest.expected)})"""
      TestCaseData(labeledTest.description, sutCall, "")

    def createAbsTestCase(labeledTest: LabeledTest) =
      val zArg    = getZArg(labeledTest)
      val sutCall = s"""ComplexNumber(${zArg._1}, ${zArg._2}).${labeledTest.property} should be (${toString(labeledTest.expected)})"""
      TestCaseData(labeledTest.description, sutCall, "")

    def createConjugateTestCase(labeledTest: LabeledTest) =
      val zArg    = getZArg(labeledTest)
      val sutCall =
        s"""val result = ComplexNumber(${zArg._1}, ${zArg._2}).${labeledTest.property}
           |assertEquals(result, ${toString(labeledTest.expected)})""".stripMargin
      TestCaseData(labeledTest.description, sutCall, "")

    def createExpTestCase(labeledTest: LabeledTest) =
      val zArg    = getZArg(labeledTest)
      val sutCall =
        s"""val result = ComplexNumber.${labeledTest.property}(ComplexNumber(${toStringArg(zArg._1)}, ${toStringArg(zArg._2)}))
           |assertEquals(result, ${toString(labeledTest.expected)})""".stripMargin
      TestCaseData(labeledTest.description, sutCall, "")

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val property = labeledTest.property
        property match
          case "real"      => createRealTestCase(labeledTest)
          case "imaginary" => createImaginaryTestCase(labeledTest)
          case "add"       => createAddTestCase(labeledTest)
          case "sub"       => createSubTestCase(labeledTest)
          case "mul"       => createMulTestCase(labeledTest)
          case "div"       => createDivTestCase(labeledTest)
          case "abs"       => createAbsTestCase(labeledTest)
          case "conjugate" => createConjugateTestCase(labeledTest)
          case "exp"       => createExpTestCase(labeledTest)
          case _           => throw new IllegalArgumentException("Unknown test case " + property)
      }

    val file                        = new File("src/main/resources/complex-numbers.json")
    val template: TestSuiteTemplate =
      txt.funSuiteTemplateIgnoreExpected.asInstanceOf[Template1[TestSuiteData, Txt]]
    val code                        = TestSuiteBuilder.build(
      file = file,
      toTestCaseData = fromLabeledTestFromInput("ignored"),
      statements = List(
        "private val equalityEpsilon = 1e-15",
        "",
        "private def assertEquals(c1: ComplexNumber, c2: ComplexNumber) { ",
        "   c1.real should be (c2.real +- equalityEpsilon)",
        "   c1.imaginary should be (c2.imaginary +- equalityEpsilon)",
        "}",
      ),
    )(template)
    println(s"-------------")
    println(code)
    println(s"-------------")
