import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
class BracketPushTest extends FunSuite with Matchers {

  test("paired square brackets") {
    BracketPush.isPaired("[]") should be (true)
  }

  test("empty string") {
    BracketPush.isPaired("") should be (true)
  }

  test("unpaired brackets") {
    BracketPush.isPaired("[[") should be (false)
  }

  test("wrong ordered brackets") {
    BracketPush.isPaired("}{") should be (false)
  }

  test("wrong closing bracket") {
    BracketPush.isPaired("{]") should be (false)
  }

  test("paired with whitespace") {
    BracketPush.isPaired("{ }") should be (true)
  }

  test("simple nested brackets") {
    BracketPush.isPaired("{[]}") should be (true)
  }

  test("several paired brackets") {
    BracketPush.isPaired("{}[]") should be (true)
  }

  test("paired and nested brackets") {
    BracketPush.isPaired("([{}({}[])])") should be (true)
  }

  test("unopened closing brackets") {
    BracketPush.isPaired("{[)][]}") should be (false)
  }

  test("unpaired and nested brackets") {
    BracketPush.isPaired("([{])") should be (false)
  }

  test("paired and wrong nested brackets") {
    BracketPush.isPaired("[({]})") should be (false)
  }

  test("math expression") {
    BracketPush.isPaired("(((185 + 223.85) * 15) - 543)/2") should be (true)
  }

  test("complex latex expression") {
    BracketPush.isPaired("\\\\left(\\\\begin{array}{cc} \\\\frac{1}{3} & x\\\\\\\\ \\\\mathrm{e}^{x} &... x^2 \\\\end{array}\\\\right)") should be (true)
  }
}
