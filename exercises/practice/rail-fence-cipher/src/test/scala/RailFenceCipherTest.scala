import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RailFenceCipherTest extends AnyFunSuite with Matchers:

  test("encode - encode with two rails"):
    RailFenceCipher.encode("XOXOXOXOXOXOXOXOXO", 2) should be("XXXXXXXXXOOOOOOOOO")

  test("encode - encode with three rails"):
    pending
    RailFenceCipher.encode("WEAREDISCOVEREDFLEEATONCE", 3) should be("WECRLTEERDSOEEFEAOCAIVDEN")

  test("encode - encode with ending in the middle"):
    pending
    RailFenceCipher.encode("EXERCISES", 4) should be("ESXIEECSR")

  test("decode - decode with three rails"):
    pending
    RailFenceCipher.decode("TEITELHDVLSNHDTISEIIEA", 3) should be("THEDEVILISINTHEDETAILS")

  test("decode - decode with five rails"):
    pending
    RailFenceCipher.decode("EIEXMSMESAORIWSCE", 5) should be("EXERCISMISAWESOME")

  test("decode - decode with six rails"):
    pending
    RailFenceCipher.decode("133714114238148966225439541018335470986172518171757571896261", 6) should be(
      "112358132134558914423337761098715972584418167651094617711286",
    )
