import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class AlphameticsTest extends FunSuite with Matchers {

  test("puzzle with three letters") {
    Alphametics.solve("I + BB == ILL") should be (Some(Map('B' -> 9,
      'I' -> 1,
      'L' -> 0)))
  }

  test("solution must have unique value for each letter") {
    pending
    Alphametics.solve("A == B") should be (None)
  }

  test("leading zero solution is invalid") {
    pending
    Alphametics.solve("ACA + DD == BD") should be (None)
  }

  test("puzzle with four letters") {
    pending
    Alphametics.solve("AS + A == MOM") should be (Some(Map('A' -> 9,
      'M' -> 1,
      'O' -> 0,
      'S' -> 2)))
  }

  test("puzzle with six letters") {
    pending
    Alphametics.solve("NO + NO + TOO == LATE") should be (Some(Map('A' -> 0,
      'E' -> 2,
      'L' -> 1,
      'N' -> 7,
      'O' -> 4,
      'T' -> 9)))
  }

  test("puzzle with seven letters") {
    pending
    Alphametics.solve("HE + SEES + THE == LIGHT") should be (Some(Map('E' -> 4,
      'G' -> 2,
      'H' -> 5,
      'I' -> 0,
      'L' -> 1,
      'S' -> 9,
      'T' -> 7)))
  }

  test("puzzle with eight letters") {
    pending
    Alphametics.solve("SEND + MORE == MONEY") should be (Some(Map('D' -> 7,
      'E' -> 5,
      'M' -> 1,
      'N' -> 6,
      'O' -> 0,
      'R' -> 8,
      'S' -> 9,
      'Y' -> 2)))
  }

  test("puzzle with ten letters") {
    pending
    Alphametics.solve("AND + A + STRONG + OFFENSE + AS + A + GOOD == DEFENSE") should be (Some(Map('A' -> 5,
      'D' -> 3,
      'E' -> 4,
      'F' -> 7,
      'G' -> 8,
      'N' -> 0,
      'O' -> 2,
      'R' -> 1,
      'S' -> 6,
      'T' -> 9)))
  }
}