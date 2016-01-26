import org.scalatest.{Matchers, FunSuite}

class QueensTest extends FunSuite with Matchers {
  test ("empty boardString") {
    Queens().boardString(None, None) should equal(
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n")
  }

  test("boardString") {
    Queens().boardString(Some(Position(2, 4)), Some(Position(6, 6))) should equal(
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ W _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ _ _\n" +
      "_ _ _ _ _ _ B _\n" +
      "_ _ _ _ _ _ _ _\n")
  }

  test("canAttack - false") {
    Queens().canAttack(Position(2, 3), Position(4, 7)) should be (false)
  }

  test("canAttack - vert attack") {
    Queens().canAttack(Position(2, 4), Position(2, 7)) should be (true)
  }

  test("canAttack - horiz attack") {
    Queens().canAttack(Position(5, 4), Position(2, 4)) should be (true)
  }

  test("canAttack - diag attack") {
    Queens().canAttack(Position(1, 1), Position(6, 6)) should be (true)
    Queens().canAttack(Position(0, 6), Position(1, 7)) should be (true)
    Queens().canAttack(Position(4, 1), Position(6, 3)) should be (true)
    Queens().canAttack(Position(2, 2), Position(1, 3)) should be (true)
  }
}
