import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import Sgf._

/** @version created manually **/
class SgfTest extends AnyFunSuite with Matchers {
  test("parse \"\"") {
    Sgf.parseSgf("") should be (None)
  }

  test("parse \"()\"") {
    pending
    Sgf.parseSgf("()") should be (None)
  }

  test("parse \";\"") {
    pending
    Sgf.parseSgf(";") should be (None)
  }

  test("parse \"(;)\"") {
    pending
    Sgf.parseSgf("(;)") should be (Some(Node(Map())))
  }

  test("parse \"(;A[B])\"") {
    pending
    Sgf.parseSgf("(;A[B])") should be (Some(Node(Map("A" -> List("B")))))
  }

  test("parse \"(;a)\"") {
    pending
    Sgf.parseSgf("(;a)") should be (None)
  }

  test("parse \"(;a[b])\"") {
    pending
    Sgf.parseSgf("(;a[b])") should be (None)
  }

  test("parse \"(;Aa[b])\"") {
    pending
    Sgf.parseSgf("(;Aa[b])") should be (None)
  }

  test("parse \"(;A[B];B[C])\"") {
    pending
    Sgf.parseSgf("(;A[B];B[C])") should be (
        Some(Node(Map("A" -> List("B")), List(Node(Map("B" -> List("C")))))))
  }

  test("parse \"(;A[B](;B[C])(;C[D]))\"") {
    pending
    Sgf.parseSgf("(;A[B](;B[C])(;C[D]))") should be (
        Some(Node(Map("A" -> List("B")), List(Node(Map("B" -> List("C"))),
                                              Node(Map("C" -> List("D")))))))
  }

  test("parse \"(;A[b][c][d])\"") {
    pending
    Sgf.parseSgf("(;A[b][c][d])") should be (Some(Node(Map("A" -> List("b", "c", "d")))))
  }

  test("""parse "(;A[\\]b\nc\\\nd\t\te\\\\ \\\n\\]])"""") {
    pending
    Sgf.parseSgf("(;A[\\]b\nc\\\nd\t\te\\\\ \\\n\\]])") should be (
        Some(Node(Map("A" -> List("]b cd  e\\ ]")))))
  }
}
