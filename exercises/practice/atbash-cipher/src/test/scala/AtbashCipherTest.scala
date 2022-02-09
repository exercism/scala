import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class AtbashCipherTest extends AnyFunSuite with Matchers {

  test("encode yes") {
    AtbashCipher.encode("yes") should be("bvh")
  }

  test("encode no") {
    pending
    AtbashCipher.encode("no") should be("ml")
  }

  test("encode OMG") {
    pending
    AtbashCipher.encode("OMG") should be("lnt")
  }

  test("encode spaces") {
    pending
    AtbashCipher.encode("O M G") should be("lnt")
  }

  test("encode mindblowingly") {
    pending
    AtbashCipher.encode("mindblowingly") should be("nrmwy oldrm tob")
  }

  test("encode numbers") {
    pending
    AtbashCipher.encode("Testing,1 2 3, testing.") should be(
      "gvhgr mt123 gvhgr mt")
  }

  test("encode deep thought") {
    pending
    AtbashCipher.encode("Truth is fiction.") should be("gifgs rhurx grlm")
  }

  test("encode all the letters") {
    pending
    AtbashCipher.encode("The quick brown fox jumps over the lazy dog.") should be(
      "gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt")
  }

  test("decode exercism") {
    pending
    AtbashCipher.decode("vcvix rhn") should be("exercism")
  }

  test("decode a sentence") {
    pending
    AtbashCipher.decode("zmlyh gzxov rhlug vmzhg vkkrm thglm v") should be(
      "anobstacleisoftenasteppingstone")
  }

  test("decode numbers") {
    pending
    AtbashCipher.decode("gvhgr mt123 gvhgr mt") should be("testing123testing")
  }

  test("decode all the letters") {
    pending
    AtbashCipher.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt") should be(
      "thequickbrownfoxjumpsoverthelazydog")
  }

  test("decode with too many spaces") {
    pending
    AtbashCipher.decode("vc vix    r hn") should be("exercism")
  }

  test("decode with no spaces") {
    pending
    AtbashCipher.decode("zmlyhgzxovrhlugvmzhgvkkrmthglmv") should be(
      "anobstacleisoftenasteppingstone")
  }
}
