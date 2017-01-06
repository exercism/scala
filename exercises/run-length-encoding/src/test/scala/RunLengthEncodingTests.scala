import org.scalatest.FunSuite
import org.scalatest.Matchers

class RunLengthEncodingTests extends FunSuite with Matchers {

  test("encode empty string") {
    RunLengthEncoding.encode("") should be ("")
  }

  test("encode single characters only") {
    pending
    RunLengthEncoding.encode("XYZ") should be ("XYZ")
  }

  test("decode empty string") {
    pending
    RunLengthEncoding.decode("") should be ("")
  }

  test("decode single characters only") {
    pending
    RunLengthEncoding.decode("XYZ") should be ("XYZ")
  }

  test("encode simple") {
    pending
    RunLengthEncoding.encode("AABBBCCCC") should be ("2A3B4C")
  }

  test("decode simple") {
    pending
    RunLengthEncoding.decode("2A3B4C") should be ("AABBBCCCC")
  }

  test("encode with single values") {
    pending
    RunLengthEncoding.encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB") should
      be ("12WB12W3B24WB")
  }

  test("decode with single values") {
    pending
    RunLengthEncoding.decode("12WB12W3B24WB") should be ("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB")
  }

  test("decode(encode(...)) combination") {
    pending
    RunLengthEncoding.decode(
        RunLengthEncoding.encode("zzz ZZ  zZ")) should be ("zzz ZZ  zZ")
  }

}
