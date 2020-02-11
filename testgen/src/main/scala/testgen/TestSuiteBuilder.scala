package testgen

import java.io.FileWriter
import java.io.File

import play.twirl.api.Txt
import play.twirl.api.Template1
import TestSuiteBuilder._
import testgen.CanonicalDataParser.ParseResult

object TestSuiteBuilder {

  type TestSuiteTemplate = Template1[TestSuiteData, Txt]
  type ToTestCaseData = String => LabeledTestItem => TestCaseData
  type ToTestCaseDataList = String => LabeledTestItem => List[TestCaseData]
  type ToOptionTestCaseData = String => LabeledTestItem => Option[TestCaseData]

  private val DefaultTemplate: TestSuiteTemplate =
    txt.funSuiteTemplate.asInstanceOf[Template1[TestSuiteData, Txt]]

  def build(file: File, toTestCaseData: ToTestCaseData, imports: Seq[String] = Seq(), statements: Seq[String] = Seq())(
    implicit template: TestSuiteTemplate = DefaultTemplate): String =
  {
    val exercise @ Exercise(name, version, cases, comments) =
      CanonicalDataParser.parse(file)
    val tsName = testSuiteName(name)
    val testCasesAllPending = cases map toTestCaseData(sutName(name))
    val testCases =
      testCasesAllPending updated(0, testCasesAllPending.head.copy(pending = false))
    val testSuiteData =
      TestSuiteData(tsName, version, imports, testCases, statements)

    template.render(testSuiteData).toString
  }


  def buildFromList(file: File, toTestCaseData: ToTestCaseDataList, imports: Seq[String] = Seq(), statements: Seq[String] = Seq())(
    implicit template: TestSuiteTemplate = DefaultTemplate): String =
  {
    val exercise @ Exercise(name, version, cases, comments) =
      CanonicalDataParser.parse(file)
    val tsName = testSuiteName(name)
    val testCasesAllPending = cases.flatMap(toTestCaseData(sutName(name)))
    val testCases =
      testCasesAllPending updated(0, testCasesAllPending.head.copy(pending = false))
    val testSuiteData =
      TestSuiteData(tsName, version, imports, testCases, statements)

    template.render(testSuiteData).toString
  }

  def buildFromOption(file: File, toTestCaseData: ToOptionTestCaseData, imports: Seq[String] = Seq())(
    implicit template: TestSuiteTemplate = DefaultTemplate): String =
  {
    val exercise @ Exercise(name, version, cases, comments) =
      CanonicalDataParser.parse(file)
    val tsName = testSuiteName(name)
    val testCasesAllPending = cases.map(toTestCaseData(sutName(name))).flatten
    val testCases =
      testCasesAllPending updated(0, testCasesAllPending.head.copy(pending = false))
    val testSuiteData =
      TestSuiteData(tsName, version, imports, testCases)

    template.render(testSuiteData).toString
  }

  def withLabeledTest(f: String => LabeledTest => TestCaseData): ToTestCaseData =
    sut => item => f(sut)(item.asInstanceOf[LabeledTest])

  def withLabeledList(f: String => LabeledTest => List[TestCaseData]): ToTestCaseDataList =
    sut => item => f(sut)(item.asInstanceOf[LabeledTest])

  def withLabeledTestOpt(f: String => LabeledTest => Option[TestCaseData]): ToOptionTestCaseData =
    sut => item => f(sut)(item.asInstanceOf[LabeledTest])

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgs(labeledTest.result, argNames: _*)
      val sutCall = s"$sut.$sutFunction(${args})"
      val expected = toString(labeledTest.expected)

      TestCaseData(labeledTest.description, sutCall, expected)
    }

  // Use when arguments are layered under "input" json element
  def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgsFromInput(labeledTest.result, argNames: _*)
      val sutCall = s"$sut.$sutFunction(${args})"
      val expected = toString(labeledTest.expected)

      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def fromLabeledTestAlt(propArgs: (String, Seq[String])*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgsAlt(labeledTest.result, propArgs:_*)
      val sutCall = s"$sut.$sutFunction(${args})"
      val expected = toString(labeledTest.expected)

      TestCaseData(labeledTest.description, sutCall, expected)
    }

  // Use when arguments are layered under "input" json element
  def fromLabeledTestAltFromInput(propArgs: (String, Seq[String])*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgsAltFromInput(labeledTest.result, propArgs:_*)
      val sutCall = s"$sut.$sutFunction(${args})"
      val expected = toString(labeledTest.expected)

      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val path = "src/main/resources"
    val input: Map[String, ToTestCaseData] =
      Map("hello-world" -> fromLabeledTest(),
          "sum-of-multiples" -> fromLabeledTest("factors", "limit"),
          "bowling" -> fromLabeledTest("previous_rolls"))

    input foreach { case ((name, toTestCaseData)) =>
        val file = new File(s"$path/$name.json")

        val code = build(file, toTestCaseData)
        println(s"------ $name -------")
        println(code)
        println(s"------ \\$name -------")
    }
  }

  def sutName(exerciseName: String) =
    exerciseName split "-" map (_.capitalize) mkString

  def testSuiteName(exerciseName: String): String =
    sutName(exerciseName) + "Test"

  def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => toString(parseResult(name))) mkString(", ")

  // Use when arguments are layered under "input" json element
  def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => toString(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString(", ")

  def sutArgsAlt(parseResult: CanonicalDataParser.ParseResult, propArgs: (String, Seq[String])*): String =
    propArgs collect {
      case ((property, argNames)) if parseResult("property") == property =>
        sutArgs(parseResult, argNames:_*)
    } head

  // Use when arguments are layered under "input" json element
  def sutArgsAltFromInput(parseResult: CanonicalDataParser.ParseResult, propArgs: (String, Seq[String])*): String =
    propArgs collect {
      case ((property, argNames)) if parseResult("property") == property =>
        sutArgsFromInput(parseResult, argNames:_*)
    } head


  private def toString(expected: CanonicalDataParser.Expected): String =
      expected.fold(error => s"Left(${toString(error)})", toString)

  def quote(str: String): String =
    if ("\"\n" exists (str.contains(_:Char))) "\"\"\"" else "\""

  def escape(raw: String): String = {
    import scala.reflect.runtime.universe._
    Literal(Constant(raw)).toString
  }

  def toString(any: Any): String = {
    any match {
      case list: List[_] =>
        val vals = list.map(s => toString(s)).mkString(", ")
        s"List($vals)"
      case str: String =>
        val quot = quote(str)
        s"$quot$str$quot"
      case lstr: Long =>
        lstr.toString + "l"
      case _ => any.toString
    }
  }

  /**
    * Get value from "input" map
    */
  def fromInputMap(parseResult: ParseResult, name: String) =
    parseResult("input").asInstanceOf[Map[String, Any]](name)

  def writeToFile(text: String, dest: File): Unit = {
    val fileWriter = new FileWriter(dest)
    try { fileWriter.write(text) } finally fileWriter.close
  }
}

case class TestCaseData(description: String, sutCall: String, expected: String,
    pending: Boolean = true)

case class TestSuiteData(name: String, version: String, imports: Seq[String],
  testCases: Seq[TestCaseData], statements: Seq[String] = Seq())
