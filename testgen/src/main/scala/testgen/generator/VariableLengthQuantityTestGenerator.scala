package testgen
package generator

import java.io.File

import TestSuiteBuilder.{toString, *}

object VariableLengthQuantityTestGenerator:

  private def mapListToString(arg: List[?]): String =
    s"List(${arg
        .map {
          case d: Double => "0x" + d.toLong.toHexString.toUpperCase
          case i: Int    => "0x" + i.toLong.toHexString.toUpperCase
          case _         => throw new IllegalArgumentException()
        }
        .mkString(", ")})"

  private def toEncodeString(expected: CanonicalDataParser.Expected): String =
    expected match
      case Left(_)     => "None"
      case Right(null) => "None"
      case Right(n)    => s"${mapListToString(n.asInstanceOf[List[?]])}"

  private def toDecodeString(expected: CanonicalDataParser.Expected): String =
    expected match
      case Left(_)     => "true"
      case Right(null) => "true"
      case Right(n)    => s"Right(${mapListToString(n.asInstanceOf[List[Any]])})"

  private def toSutCall(sut: String, property: String, args: String, expected: CanonicalDataParser.Expected): String =
    if property.toString.equals("encode") then s"""$sut.$property($args)"""
    else
      expected match
        case Left(_)     => s"""$sut.$property($args).isLeft"""
        case Right(null) => s"""$sut.$property($args).isLeft"""
        case Right(n)    => s"""$sut.$property($args)"""

  private def toArgString(any: Any): String =
    any match
      case list: List[?] => s"${mapListToString(list)}"
      case _             => any.toString

  private def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => toArgString(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString ", "

  def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val args     = sutArgsFromInput(labeledTest.result, argNames*)
      val property = labeledTest.property
      val sutCall  = toSutCall(sut, property, args, labeledTest.expected)
      val expected =
        if labeledTest.property.toString.equals("encode") then toEncodeString(labeledTest.expected)
        else toDecodeString(labeledTest.expected)
      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/variable-length-quantity.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("integers"))
    println(s"-------------")
    println(code)
    println(s"-------------")
