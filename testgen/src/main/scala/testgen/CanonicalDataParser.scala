package testgen

import scala.io.Source
import scala.util.parsing.json.JSON
import CanonicalDataParser._
import scala.util.Try
import scala.Left
import scala.Right
import java.io.File

object CanonicalDataParser {
  type ParseResult = Map[String,Any]

  type Description = String
  type Comments = Seq[String]
  type Cases = Seq[LabeledTestItem]
  type Property = String
  type Result = Any
  type Error = String
  type Expected = Either[Error, Result]
  type Properties = Option[Map[String,Any]]

  def getOptional[T](result: ParseResult, key: String): Option[T] =
    result.get(key).asInstanceOf[Option[T]]
  def getRequired[T](result: ParseResult, key: String): T =
    getOptional(result, key) getOrElse (throw new Exception(s"missing: $key"))

  def parse(file: File): Exercise = {
    val fileContents = Source.fromFile(file).getLines.mkString
    val rawParseResult =
      JSON.parseFull(fileContents).get.asInstanceOf[ParseResult]
    val parseResult = rawParseResult mapValues restoreInts
    println(parseResult)
    parseResult
  }

  private def restoreInts(any: Any): Any =
    any match {
      case double: Double if (double.toInt.toDouble == double) => double.toInt
      case map: Map[_,_] => map mapValues restoreInts
      case seq: Seq[_] => seq map restoreInts
      case any => any
    }

  def main(args: Array[String]): Unit = {
    val path = "src/main/resources"
//    val name = "hello-world.json"
//    val name = "sum-of-multiples.json"
    val name = "bowling.json"
    val result = parse(new File(s"$path/$name"))
    println(result)
  }
}

case class Exercise(name: String, version: String, cases: Cases,
    comments: Option[Comments])
object Exercise {
  implicit def fromParseResult(result: ParseResult): Exercise = {
    val cases: Cases =
      getRequired[Seq[ParseResult]](result, "cases") map LabeledTestItem.fromParseResult
    Exercise(getRequired(result, "exercise"), getRequired(result, "version"),
        flattenCases(cases, List()), getOptional(result, "comments"))
  }

  // so far there are to few LabeledTestGroups to handle them separately
  private def flattenCases(cases: Cases, parentDescriptions: List[String]): Cases =
    cases match {
      case Seq() => Seq()
      case (ltg: LabeledTestGroup) +: xs => flattenCases(ltg.cases, ltg.description :: parentDescriptions) ++
        flattenCases(xs, parentDescriptions)
      case (lt: LabeledTest) +: xs => LabeledTest(lt.description, lt.property, lt.expected, lt.result, parentDescriptions) +:
        flattenCases(xs, parentDescriptions)
    }
}

sealed trait LabeledTestItem
object LabeledTestItem {
  implicit def fromParseResult(result: ParseResult): LabeledTestItem =
    if (result.contains("cases")) result: LabeledTestGroup
    else result: LabeledTest
}

case class LabeledTest(description: Description, property: Property,
    expected: Expected, result: ParseResult, parentDescriptions: List[String] = List()) extends LabeledTestItem
object LabeledTest {
  implicit def fromParseResult(result: ParseResult): LabeledTest = {
    val expected: Expected = {
      val any = getRequired[Any](result, "expected")
      val error = Try {
        Left(any.asInstanceOf[Map[String,String]]("error"))
      }
      error.getOrElse(Right(any))
    }
    LabeledTest(getRequired(result, "description"), getRequired(result, "property"),
        expected, result)
  }
}

case class LabeledTestGroup(description: Description, cases: Cases) extends LabeledTestItem
object LabeledTestGroup {
  implicit def fromParseResult(result: ParseResult): LabeledTestGroup = {
    val description = getRequired[String](result, "description")
    val cases =
      getRequired[Seq[ParseResult]](result, "cases") map LabeledTestItem.fromParseResult
    LabeledTestGroup(description, cases)
  }
}
