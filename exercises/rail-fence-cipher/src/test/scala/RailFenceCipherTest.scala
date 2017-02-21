import org.scalatest.{FunSuite, Matchers}

class RailFenceCipherTest extends FunSuite with Matchers {

  // Encode test cases
  test("test to encode with two rails") {
    val encoded = RailFenceCipher.encode("XOXOXOXOXOXOXOXOXO", 2)
    encoded should be ("XXXXXXXXXOOOOOOOOO")
  }

  test("test to encode with three rails") {
    pending
    val encoded = RailFenceCipher.encode("WEAREDISCOVEREDFLEEATONCE", 3)
    encoded should be ("WECRLTEERDSOEEFEAOCAIVDEN")
  }

  test("test to encode with ending in the middle") {
    pending
    val encoded = RailFenceCipher.encode("EXERCISES", 4)
    encoded should be ("ESXIEECSR")
  }

  // Decode test cases
  test("test to decode with three rails") {
    pending
    val decoded = RailFenceCipher.decode("TEITELHDVLSNHDTISEIIEA", 3)
    decoded should be ("THEDEVILISINTHEDETAILS")
  }

  test("test to decode with five rails") {
    pending
    val decoded = RailFenceCipher.decode("EIEXMSMESAORIWSCE", 5)
    decoded should be ("EXERCISMISAWESOME")
  }

  test("test to decode with six rails") {
    pending
    val decoded = RailFenceCipher.decode("133714114238148966225439541018335470986172518171757571896261", 6)
    decoded should be ("112358132134558914423337761098715972584418167651094617711286")
  }

}
