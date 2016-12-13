import org.scalatest._

class RomanNumeralsSpecs extends FunSpec with Matchers {

  it ("0 equals empty string") {
    RomanNumeral(0).value should be ("")
  }

  it ("1 equals I") {
    pending
    RomanNumeral(1).value should be ("I")
  }

  it ("2 equals II") {
    pending
    RomanNumeral(2).value should be ("II")
  }

  it ("3 equals III") {
    pending
    RomanNumeral(3).value should be ("III")
  }

  it ("4 equals IV") {
    pending
    RomanNumeral(4).value should be ("IV")
  }

  it ("5 equals V") {
    pending
    RomanNumeral(5).value should be ("V")
  }

  it ("6 equals VI") {
    pending
    RomanNumeral(6).value should be ("VI")
  }

  it ("9 equals IX") {
    pending
    RomanNumeral(9).value should be ("IX")
  }

  it ("27 equals XXVII") {
    pending
    RomanNumeral(27).value should be ("XXVII")
  }

  it ("48 equals XLVIII") {
    pending
    RomanNumeral(48).value should be ("XLVIII")
  }

  it ("59 equals LIX") {
    pending
    RomanNumeral(59).value should be ("LIX")
  }

  it ("93 equals XCIII") {
    pending
    RomanNumeral(93).value should be ("XCIII")
  }

  it ("141 equals CXLI") {
    pending
    RomanNumeral(141).value should be ("CXLI")
  }

  it ("402 equals CDII") {
    pending
    RomanNumeral(402).value should be ("CDII")
  }

  it ("575 equals DLXXV") {
    pending
    RomanNumeral(575).value should be ("DLXXV")
  }

  it ("911 equals CMXI") {
    pending
    RomanNumeral(911).value should be ("CMXI")
  }

  it ("1024 equals MXXIV") {
    pending
    RomanNumeral(1024).value should be ("MXXIV")
  }

  it ("3000 equals MMM") {
    pending
    RomanNumeral(3000).value should be ("MMM")
  }
}
