import scala.language.postfixOps
import java.io.FileWriter
import java.io.File

case class TestCaseGen(description: String, callSUT: String, expected: String,
    result: String, checkResult: String)

object TestCaseGen {
  def apply(description: String, callSUT: String, expected: String): TestCaseGen =
    TestCaseGen(description, callSUT, expected,
        s"val result = $callSUT", s"result should be ($expected)")

  implicit def toTestCaseGenSeq[T](ts: Seq[T])(
      implicit toGen: T => TestCaseGen): Seq[TestCaseGen] =
    ts map toGen
}

class TestBuilder(testName: String) {

  private var imports: Seq[String] = Seq()
  def addImport(imprt: String): Unit = imports = imports :+ imprt

  private var version: Option[String] = None
  def setVersion(v: String): Unit = this.version = Some(v)

  private var testCases: Seq[(Seq[TestCaseGen], Option[String])] = Seq()
  def addTestCases(testCases: Seq[TestCaseGen], description: Option[String] = None): Unit =
    this.testCases = this.testCases :+ (testCases, description)

  def toFile: Unit = {
    val file = new File(s"$testName.scala")
    val fileWriter = new FileWriter(file)
    try { fileWriter.write(build) } finally fileWriter.close

    println(s"file ${file.getAbsolutePath} created")
  }

  def build: String =
s"""$printImports
$printVersion
class $testName extends AnyFunSuite with Matchers {
$printTestCases
}
"""

  private lazy val printVersion: String =
    version match {
      case Some(v) => s"""/** @version $v */"""
      case None => ""
    }

  private lazy val printImports: String =
    "import org.scalatest.funsuite.AnyFunSuite\n" +
    "import org.scalatest.matchers.should.Matchers\n" +
    (imports map (imp => s"import $imp\n") mkString)

  private lazy val printTestCases: String = {
    var pending = false
    def printPending: String =
      if (pending) s"pending\n    " else { pending = true; "" }

    def printTestCaseGroup(testCaseGroup: Seq[TestCaseGen],
        groupDescription: Option[String]): String =
    {
      val description = groupDescription map (s => s"\n  // $s") getOrElse ""
      val testCases = testCaseGroup map printTestCase mkString

      s"$description$testCases"
    }

    def printTestCase(tc: TestCaseGen): String =
s"""
  test("${tc.description}") {
    ${printPending}${tc.result}
    ${tc.checkResult}
  }
"""

    testCases map (printTestCaseGroup _).tupled mkString
  }
}

object TestBuilder {
  def main(args: Array[String]): Unit = {
    val testCases13 =
      (1 to 3) map (i => TestCaseGen(s"test$i", s"identity($i)", s"$i"))
    val testCases45 =
      (4 to 5) map (i => TestCaseGen(s"test$i", s"identity($i)", s"$i"))
    val tb = new TestBuilder("IdentityTest")
    tb.addImport("scala.collection.mutable")
    tb.addTestCases(testCases13, Some("identity tests 1-3"))
    tb.addTestCases(testCases45)
    println("-----")
    println(tb.build)
    println("-----")
    tb.toFile
  }
}
