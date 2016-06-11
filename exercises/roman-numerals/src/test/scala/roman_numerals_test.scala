import org.scalatest._

class RomanNumeralsSpecs extends FunSpec with Matchers {

  it ("0 equals empty string") {
    RomanNumeral(0).value should be ("")
  }

  it ("1 equals I") {
    RomanNumeral(1).value should be ("I")
  }

  it ("2 equals II") {
    RomanNumeral(2).value should be ("II")
  }

  it ("3 equals III") {
    RomanNumeral(3).value should be ("III")
  }

  it ("4 equals IV") {
    RomanNumeral(4).value should be ("IV")
  }

  it ("5 equals V") {
    RomanNumeral(5).value should be ("V")
  }

  it ("6 equals VI") {
    RomanNumeral(6).value should be ("VI")
  }

  it ("9 equals IX") {
    RomanNumeral(9).value should be ("IX")
  }

  it ("27 equals XXVII") {
    RomanNumeral(27).value should be ("XXVII")
  }

  it ("48 equals XLVIII") {
    RomanNumeral(48).value should be ("XLVIII")
  }

  it ("59 equals LIX") {
    RomanNumeral(59).value should be ("LIX")
  }

  it ("93 equals XCIII") {
    RomanNumeral(93).value should be ("XCIII")
  }

  it ("141 equals CXLI") {
    RomanNumeral(141).value should be ("CXLI")
  }

  it ("402 equals CDII") {
    RomanNumeral(402).value should be ("CDII")
  }

  it ("575 equals DLXXV") {
    RomanNumeral(575).value should be ("DLXXV")
  }

  it ("911 equals CMXI") {
    RomanNumeral(911).value should be ("CMXI")
  }

  it ("1024 equals MXXIV") {
    RomanNumeral(1024).value should be ("MXXIV")
  }

  it ("3000 equals MMM") {
    RomanNumeral(3000).value should be ("MMM")
  }
}
