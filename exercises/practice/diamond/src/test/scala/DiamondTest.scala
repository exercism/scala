import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class DiamondTest extends AnyFunSuite with Matchers {

  test("Degenerate case with a single 'A' row") {
    Diamond.rows('A') should be(List("A"))
  }

  test("Degenerate case with no row containing 3 distinct groups of spaces") {
    pending
    Diamond.rows('B') should be(List(" A ", "B B", " A "))
  }

  test("Smallest non-degenerate case with odd diamond side length") {
    pending
    Diamond.rows('C') should be(
      List("  A  ", " B B ", "C   C", " B B ", "  A  "))
  }

  test("Smallest non-degenerate case with even diamond side length") {
    pending
    Diamond.rows('D') should be(
      List("   A   ",
           "  B B  ",
           " C   C ",
           "D     D",
           " C   C ",
           "  B B  ",
           "   A   "))
  }

  test("Largest possible diamond") {
    pending
    Diamond.rows('Z') should be(
      List(
        "                         A                         ",
        "                        B B                        ",
        "                       C   C                       ",
        "                      D     D                      ",
        "                     E       E                     ",
        "                    F         F                    ",
        "                   G           G                   ",
        "                  H             H                  ",
        "                 I               I                 ",
        "                J                 J                ",
        "               K                   K               ",
        "              L                     L              ",
        "             M                       M             ",
        "            N                         N            ",
        "           O                           O           ",
        "          P                             P          ",
        "         Q                               Q         ",
        "        R                                 R        ",
        "       S                                   S       ",
        "      T                                     T      ",
        "     U                                       U     ",
        "    V                                         V    ",
        "   W                                           W   ",
        "  X                                             X  ",
        " Y                                               Y ",
        "Z                                                 Z",
        " Y                                               Y ",
        "  X                                             X  ",
        "   W                                           W   ",
        "    V                                         V    ",
        "     U                                       U     ",
        "      T                                     T      ",
        "       S                                   S       ",
        "        R                                 R        ",
        "         Q                               Q         ",
        "          P                             P          ",
        "           O                           O           ",
        "            N                         N            ",
        "             M                       M             ",
        "              L                     L              ",
        "               K                   K               ",
        "                J                 J                ",
        "                 I               I                 ",
        "                  H             H                  ",
        "                   G           G                   ",
        "                    F         F                    ",
        "                     E       E                     ",
        "                      D     D                      ",
        "                       C   C                       ",
        "                        B B                        ",
        "                         A                         "
      ))
  }
}
