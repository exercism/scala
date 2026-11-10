import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PangramTest extends AnyFunSuite with Matchers:

  test("empty sentence"):
    Pangrams.isPangram("") should be(false)

  test("perfect lower case"):
    pending
    Pangrams.isPangram("abcdefghijklmnopqrstuvwxyz") should be(true)

  test("only lower case"):
    pending
    Pangrams.isPangram("the quick brown fox jumps over the lazy dog") should be(true)

  test("missing the letter 'x'"):
    pending
    Pangrams.isPangram("a quick movement of the enemy will jeopardize five gunboats") should be(false)

  test("missing the letter 'h'"):
    pending
    Pangrams.isPangram("five boxing wizards jump quickly at it") should be(false)

  test("with underscores"):
    pending
    Pangrams.isPangram("the_quick_brown_fox_jumps_over_the_lazy_dog") should be(true)

  test("with numbers"):
    pending
    Pangrams.isPangram("the 1 quick brown fox jumps over the 2 lazy dogs") should be(true)

  test("missing letters replaced by numbers"):
    pending
    Pangrams.isPangram("7h3 qu1ck brown fox jumps ov3r 7h3 lazy dog") should be(false)

  test("mixed case and punctuation"):
    pending
    Pangrams.isPangram("\"Five quacking Zephyrs jolt my wax bed.\"") should be(true)

  test("a-m and A-M are 26 different characters but not a pangram"):
    pending
    Pangrams.isPangram("abcdefghijklm ABCDEFGHIJKLM") should be(false)
