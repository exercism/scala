import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RunLengthEncodingTest extends AnyFunSuite with Matchers:

  test("run-length encode a string - empty string"):
    RunLengthEncoding.encode("") should be("")

  test("run-length encode a string - single characters only are encoded without count"):
    pending
    RunLengthEncoding.encode("XYZ") should be("XYZ")

  test("run-length encode a string - string with no single characters"):
    pending
    RunLengthEncoding.encode("AABBBCCCC") should be("2A3B4C")

  test("run-length encode a string - single characters mixed with repeated characters"):
    pending
    RunLengthEncoding.encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB") should be("12WB12W3B24WB")

  test("run-length encode a string - multiple whitespace mixed in string"):
    pending
    RunLengthEncoding.encode("  hsqq qww  ") should be("2 hs2q q2w2 ")

  test("run-length encode a string - lowercase characters"):
    pending
    RunLengthEncoding.encode("aabbbcccc") should be("2a3b4c")

  test("run-length decode a string - empty string"):
    pending
    RunLengthEncoding.decode("") should be("")

  test("run-length decode a string - single characters only"):
    pending
    RunLengthEncoding.decode("XYZ") should be("XYZ")

  test("run-length decode a string - string with no single characters"):
    pending
    RunLengthEncoding.decode("2A3B4C") should be("AABBBCCCC")

  test("run-length decode a string - single characters with repeated characters"):
    pending
    RunLengthEncoding.decode("12WB12W3B24WB") should be("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB")

  test("run-length decode a string - multiple whitespace mixed in string"):
    pending
    RunLengthEncoding.decode("2 hs2q q2w2 ") should be("  hsqq qww  ")

  test("run-length decode a string - lowercase string"):
    pending
    RunLengthEncoding.decode("2a3b4c") should be("aabbbcccc")

  test("encode and then decode - encode followed by decode gives original string"):
    pending
    RunLengthEncoding.decode(RunLengthEncoding.encode("zzz ZZ  zZ")) should be("zzz ZZ  zZ")
