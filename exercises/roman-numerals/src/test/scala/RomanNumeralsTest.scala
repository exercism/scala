import org.scalatest._

class RomanNumeralsSpecs extends FunSpec with Matchers {

  it ("0 equals empty string") {
    RomanNumeral.toNumerals(0) should be ("")
  }

  it ("1 equals I") {
    pending
    RomanNumeral.toNumerals(1) should be ("I")
  }

  it ("2 equals II") {
    pending
    RomanNumeral.toNumerals(2) should be ("II")
  }

  it ("3 equals III") {
    pending
    RomanNumeral.toNumerals(3) should be ("III")
  }

  it ("4 equals IV") {
    pending
    RomanNumeral.toNumerals(4) should be ("IV")
  }

  it ("5 equals V") {
    pending
    RomanNumeral.toNumerals(5) should be ("V")
  }

  it ("6 equals VI") {
    pending
    RomanNumeral.toNumerals(6) should be ("VI")
  }

  it ("9 equals IX") {
    pending
    RomanNumeral.toNumerals(9) should be ("IX")
  }

  it ("27 equals XXVII") {
    pending
    RomanNumeral.toNumerals(27) should be ("XXVII")
  }

  it ("48 equals XLVIII") {
    pending
    RomanNumeral.toNumerals(48) should be ("XLVIII")
  }

  it ("59 equals LIX") {
    pending
    RomanNumeral.toNumerals(59) should be ("LIX")
  }

  it ("93 equals XCIII") {
    pending
    RomanNumeral.toNumerals(93) should be ("XCIII")
  }

  it ("141 equals CXLI") {
    pending
    RomanNumeral.toNumerals(141) should be ("CXLI")
  }

  it ("402 equals CDII") {
    pending
    RomanNumeral.toNumerals(402) should be ("CDII")
  }

  it ("575 equals DLXXV") {
    pending
    RomanNumeral.toNumerals(575) should be ("DLXXV")
  }

  it ("911 equals CMXI") {
    pending
    RomanNumeral.toNumerals(911) should be ("CMXI")
  }

  it ("1024 equals MXXIV") {
    pending
    RomanNumeral.toNumerals(1024) should be ("MXXIV")
  }

  it ("3000 equals MMM") {
    pending
    RomanNumeral.toNumerals(3000) should be ("MMM")
  }
}
