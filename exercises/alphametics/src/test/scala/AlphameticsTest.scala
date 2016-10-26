import org.scalatest.{FunSuite, Matchers}

class AlphameticsTest extends FunSuite with Matchers {
  test("solve short puzzle") {
    Alphametics.solve("I + BB == ILL") should
      be (Some(Map('I' -> 1, 'B' -> 9, 'L' -> 0)))
  }

  test("solve long puzzle") {
    pending
    Alphametics.solve("SEND + MORE == MONEY") should
      be (Some(Map('S' -> 9, 'E' -> 5, 'N' -> 6, 'D' -> 7, 'M' -> 1,
            'O' -> 0, 'R' -> 8, 'Y' -> 2)))
  }

  test("solve puzzle with multiplication") {
    pending
    Alphametics.solve("IF * DR == DORI") should
      be (Some(Map('I' -> 8, 'F' -> 2, 'D' -> 3, 'R' -> 9, 'O' -> 1)))
  }

  test("solve puzzle with exponents") {
    pending
    Alphametics.solve("PI * R ^ 2 == AREA") should
      be (Some(Map('P' -> 9, 'I' -> 6, 'R' -> 7, 'A' -> 4, 'E' -> 0)))
  }

  test("solution must have unique value for each letter") {
    pending
    Alphametics.solve("A == B") should be (None)
  }

  test("leading zero solution is invalid") {
    pending
    Alphametics.solve("ACA + DD == BD") should be (None)
  }
}
