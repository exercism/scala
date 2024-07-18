package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object GrainsTestGenerator:
  def main(args: Array[String]): Unit =

    def toString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Left(_)              => "None"
        case Right(-1)            => "None"
        case Right(n: BigDecimal) => if n > Int.MaxValue then s"""Some(BigInt(\"$n\"))""" else s"Some($n)"
        case Right(n: Double)     => s"""Some(BigInt("${n.toLong}"))"""
        case Right(n)             => s"Some($n)"

    def toStringForTotal(expected: CanonicalDataParser.Expected): String =
      expected match
        case Right(n: BigDecimal) => s"""BigInt(\"$n\")"""
        case Right(l: Long)       => s"""Long(\"${l.toString}\")"""
        case Right(d: Double)     => s"""Long(\"${d.toLong.toString}\")"""
        case Right(v)             => v.toString
        case _                    => throw new IllegalStateException

    val file                                       = new File("src/main/resources/grains.json")
    def fromLabeledTestFromInput(): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val square = labeledTest.result("input").asInstanceOf[Map[String, Any]].get("square")
        val args   = square match
          case None    => ""
          case Some(s) => s.toString

        val property = labeledTest.property.mkString
        val sutCall  =
          if args.length > 0 then s"""Grains.$property($args)"""
          else s"""Grains.$property"""
        val expected =
          if args.length > 0 then toString(labeledTest.expected)
          else toStringForTotal(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput())
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
    println(code)
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
