import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class AtbashCipherTest extends AnyFunSuite with Matchers:

  test("encode - encode yes"):
    AtbashCipher.encode("yes") should be("bvh")

  test("encode - encode no"):
    pending
    AtbashCipher.encode("no") should be("ml")

  test("encode - encode OMG"):
    pending
    AtbashCipher.encode("OMG") should be("lnt")

  test("encode - encode spaces"):
    pending
    AtbashCipher.encode("O M G") should be("lnt")

  test("encode - encode mindblowingly"):
    pending
    AtbashCipher.encode("mindblowingly") should be("nrmwy oldrm tob")

  test("encode - encode numbers"):
    pending
    AtbashCipher.encode("Testing,1 2 3, testing.") should be("gvhgr mt123 gvhgr mt")

  test("encode - encode deep thought"):
    pending
    AtbashCipher.encode("Truth is fiction.") should be("gifgs rhurx grlm")

  test("encode - encode all the letters"):
    pending
    AtbashCipher.encode("The quick brown fox jumps over the lazy dog.") should be("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt")

  test("decode - decode exercism"):
    pending
    AtbashCipher.decode("vcvix rhn") should be("exercism")

  test("decode - decode a sentence"):
    pending
    AtbashCipher.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v") should be("anobstacleisoftenasteppingstone")

  test("decode - decode numbers"):
    pending
    AtbashCipher.decode("gvhgr mt123 gvhgr mt") should be("testing123testing")

  test("decode - decode all the letters"):
    pending
    AtbashCipher.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt") should be("thequickbrownfoxjumpsoverthelazydog")

  test("decode - decode with too many spaces"):
    pending
    AtbashCipher.decode("vc vix    r hn") should be("exercism")

  test("decode - decode with no spaces"):
    pending
    AtbashCipher.decode("zmlyhgzxovrhlugvmzhgvkkrmthglmv") should be("anobstacleisoftenasteppingstone")
