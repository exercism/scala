import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RomanNumeralsTest extends AnyFunSuite with Matchers:

  test("1 is I"):
    RomanNumerals.roman(1) should be("I")

  test("2 is II"):
    pending
    RomanNumerals.roman(2) should be("II")

  test("3 is III"):
    pending
    RomanNumerals.roman(3) should be("III")

  test("4 is IV"):
    pending
    RomanNumerals.roman(4) should be("IV")

  test("5 is V"):
    pending
    RomanNumerals.roman(5) should be("V")

  test("6 is VI"):
    pending
    RomanNumerals.roman(6) should be("VI")

  test("9 is IX"):
    pending
    RomanNumerals.roman(9) should be("IX")

  test("16 is XVI"):
    pending
    RomanNumerals.roman(16) should be("XVI")

  test("27 is XXVII"):
    pending
    RomanNumerals.roman(27) should be("XXVII")

  test("48 is XLVIII"):
    pending
    RomanNumerals.roman(48) should be("XLVIII")

  test("49 is XLIX"):
    pending
    RomanNumerals.roman(49) should be("XLIX")

  test("59 is LIX"):
    pending
    RomanNumerals.roman(59) should be("LIX")

  test("66 is LXVI"):
    pending
    RomanNumerals.roman(66) should be("LXVI")

  test("93 is XCIII"):
    pending
    RomanNumerals.roman(93) should be("XCIII")

  test("141 is CXLI"):
    pending
    RomanNumerals.roman(141) should be("CXLI")

  test("163 is CLXIII"):
    pending
    RomanNumerals.roman(163) should be("CLXIII")

  test("166 is CLXVI"):
    pending
    RomanNumerals.roman(166) should be("CLXVI")

  test("402 is CDII"):
    pending
    RomanNumerals.roman(402) should be("CDII")

  test("575 is DLXXV"):
    pending
    RomanNumerals.roman(575) should be("DLXXV")

  test("666 is DCLXVI"):
    pending
    RomanNumerals.roman(666) should be("DCLXVI")

  test("911 is CMXI"):
    pending
    RomanNumerals.roman(911) should be("CMXI")

  test("1024 is MXXIV"):
    pending
    RomanNumerals.roman(1024) should be("MXXIV")

  test("1666 is MDCLXVI"):
    pending
    RomanNumerals.roman(1666) should be("MDCLXVI")

  test("3000 is MMM"):
    pending
    RomanNumerals.roman(3000) should be("MMM")

  test("3001 is MMMI"):
    pending
    RomanNumerals.roman(3001) should be("MMMI")

  test("3888 is MMMDCCCLXXXVIII"):
    pending
    RomanNumerals.roman(3888) should be("MMMDCCCLXXXVIII")

  test("3999 is MMMCMXCIX"):
    pending
    RomanNumerals.roman(3999) should be("MMMCMXCIX")
