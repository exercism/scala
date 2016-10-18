import org.scalatest.{FunSuite, Matchers}

class BracketsTest extends FunSuite with Matchers {
  test("paired square brackets") {
    pending
    Brackets.areBalanced("""[]""") should be (true)
  }

  test("empty string") {
    pending
    Brackets.areBalanced("""""") should be (true)
  }

  test("unpaired brackets") {
    pending
    Brackets.areBalanced("""[[""") should be (false)
  }

  test("wrong ordered brackets") {
    pending
    Brackets.areBalanced("""}{""") should be (false)
  }

  test("paired with whitespace") {
    pending
    Brackets.areBalanced("""{ }""") should be (true)
  }

  test("simple nested brackets") {
    pending
    Brackets.areBalanced("""{[]}""") should be (true)
  }

  test("several paired brackets") {
    pending
    Brackets.areBalanced("""{}[]""") should be (true)
  }

  test("paired and nested brackets") {
    pending
    Brackets.areBalanced("""([{}({}[])])""") should be (true)
  }

  test("unopened closing brackets") {
    pending
    Brackets.areBalanced("""{[)][]}""") should be (false)
  }

  test("unpaired and nested brackets") {
    pending
    Brackets.areBalanced("""([{])""") should be (false)
  }

  test("paired and wrong nested brackets") {
    pending
    Brackets.areBalanced("""[({]})""") should be (false)
  }

  test("math expression") {
    pending
    Brackets.areBalanced("""(((185 + 223.85) * 15) - 543)/2""") should be (true)
  }

  test("complex latex expression") {
    pending
    Brackets.areBalanced("""\left(\begin{array}{cc} \frac{1}{3} & x\\ \mathrm{e}^{x} &... x^2 \end{array}\right)""") should be (true)
  }
}
