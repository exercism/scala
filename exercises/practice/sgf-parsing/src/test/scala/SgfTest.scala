import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class SgfTest extends AnyFunSuite with Matchers:

  import Sgf.*

  test("empty input"):
    parseSgf("") should be(None)

  test("tree with no nodes"):
    pending
    parseSgf("()") should be(None)

  test("node without tree"):
    pending
    parseSgf(";") should be(None)

  test("node without properties"):
    pending
    parseSgf("(;)") should be(Some(Node(Map())))

  test("single node tree"):
    pending
    parseSgf("(;A[B])") should be(Some(Node(Map("A" -> List("B")))))

  test("multiple properties"):
    pending
    parseSgf("(;A[b]C[d])") should be(Some(Node(Map("A" -> List("b"), "C" -> List("d")))))

  test("properties without delimiter"):
    pending
    parseSgf("(;A)") should be(None)

  test("all lowercase property"):
    pending
    parseSgf("(;a[b])") should be(None)

  test("upper and lowercase property"):
    pending
    parseSgf("(;Aa[b])") should be(None)

  test("two nodes"):
    pending
    parseSgf("(;A[B];B[C])") should be(Some(Node(Map("A" -> List("B")), List(Node(Map("B" -> List("C")))))))

  test("two child trees"):
    pending
    parseSgf("(;A[B](;B[C])(;C[D]))") should be(Some(Node(Map("A" -> List("B")), List(Node(Map("B" -> List("C"))), Node(Map("C" -> List("D")))))))

  test("multiple property values"):
    pending
    parseSgf("(;A[b][c][d])") should be(Some(Node(Map("A" -> List("b", "c", "d")))))

  test("within property values, whitespace characters such as tab are converted to spaces"):
    pending
    parseSgf("(;A[hello\t\tworld])") should be(Some(Node(Map("A" -> List("hello  world")))))

  test("within property values, newlines remain as newlines"):
    pending
    parseSgf("(;A[hello\n\nworld])") should be(Some(Node(Map("A" -> List("hello\n\nworld")))))

  test("escaped closing bracket within property value becomes just a closing bracket"):
    pending
    parseSgf("(;A[\\]])") should be(Some(Node(Map("A" -> List("]")))))

  test("escaped backslash in property value becomes just a backslash"):
    pending
    parseSgf("(;A[\\\\])") should be(Some(Node(Map("A" -> List("\\")))))

  test("opening bracket within property value doesn't need to be escaped"):
    pending
    parseSgf("(;A[x[y\\]z][foo]B[bar];C[baz])") should be(Some(Node(Map("A" -> List("x[y]z", "foo"), "B" -> List("bar")), List(Node(Map("C" -> List("baz")))))))

  test("semicolon in property value doesn't need to be escaped"):
    pending
    parseSgf("(;A[a;b][foo]B[bar];C[baz])") should be(Some(Node(Map("A" -> List("a;b", "foo"), "B" -> List("bar")), List(Node(Map("C" -> List("baz")))))))

  test("parentheses in property value don't need to be escaped"):
    pending
    parseSgf("(;A[x(y)z][foo]B[bar];C[baz])") should be(Some(Node(Map("A" -> List("x(y)z", "foo"), "B" -> List("bar")), List(Node(Map("C" -> List("baz")))))))

  test("escaped tab in property value is converted to space"):
    pending
    parseSgf("(;A[hello\\\tworld])") should be(Some(Node(Map("A" -> List("hello world")))))

  test("escaped newline in property value is converted to nothing at all"):
    pending
    parseSgf("(;A[hello\\\nworld])") should be(Some(Node(Map("A" -> List("helloworld")))))

  test("escaped t and n in property value are just letters, not whitespace"):
    pending
    parseSgf("(;A[\\t = t and \\n = n])") should be(Some(Node(Map("A" -> List("t = t and n = n")))))

  test("mixing various kinds of whitespace and escaped characters in property value"):
    pending
    parseSgf("(;A[\\]b\nc\\\nd\t\te\\\\ \\\n\\]])") should be(Some(Node(Map("A" -> List("]b\ncd  e\\ ]")))))
