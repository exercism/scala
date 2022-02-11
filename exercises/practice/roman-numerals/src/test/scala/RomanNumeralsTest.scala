import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class RomanNumeralsTest extends AnyFunSuite with Matchers {

  test("1 is a single I") {
    RomanNumerals.roman(1) should be ("I")
  }

  test("2 is two I's") {
    pending
    RomanNumerals.roman(2) should be ("II")
  }

  test("3 is three I's") {
    pending
    RomanNumerals.roman(3) should be ("III")
  }

  test("4, being 5 - 1, is IV") {
    pending
    RomanNumerals.roman(4) should be ("IV")
  }

  test("5 is a single V") {
    pending
    RomanNumerals.roman(5) should be ("V")
  }

  test("6, being 5 + 1, is VI") {
    pending
    RomanNumerals.roman(6) should be ("VI")
  }

  test("9, being 10 - 1, is IX") {
    pending
    RomanNumerals.roman(9) should be ("IX")
  }

  test("20 is two X's") {
    pending
    RomanNumerals.roman(27) should be ("XXVII")
  }

  test("48 is not 50 - 2 but rather 40 + 8") {
    pending
    RomanNumerals.roman(48) should be ("XLVIII")
  }

  test("49 is not 40 + 5 + 4 but rather 50 - 10 + 10 - 1") {
    pending
    RomanNumerals.roman(49) should be ("XLIX")
  }

  test("50 is a single L") {
    pending
    RomanNumerals.roman(59) should be ("LIX")
  }

  test("90, being 100 - 10, is XC") {
    pending
    RomanNumerals.roman(93) should be ("XCIII")
  }

  test("100 is a single C") {
    pending
    RomanNumerals.roman(141) should be ("CXLI")
  }

  test("60, being 50 + 10, is LX") {
    pending
    RomanNumerals.roman(163) should be ("CLXIII")
  }

  test("400, being 500 - 100, is CD") {
    pending
    RomanNumerals.roman(402) should be ("CDII")
  }

  test("500 is a single D") {
    pending
    RomanNumerals.roman(575) should be ("DLXXV")
  }

  test("900, being 1000 - 100, is CM") {
    pending
    RomanNumerals.roman(911) should be ("CMXI")
  }

  test("1000 is a single M") {
    pending
    RomanNumerals.roman(1024) should be ("MXXIV")
  }

  test("3000 is three M's") {
    pending
    RomanNumerals.roman(3000) should be ("MMM")
  }
}