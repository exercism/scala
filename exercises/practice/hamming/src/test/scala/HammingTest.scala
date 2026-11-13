import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class HammingTest extends AnyFunSuite with Matchers:

  test("empty strands"):
    Hamming.distance("", "") should be(Some(0))

  test("single letter identical strands"):
    pending
    Hamming.distance("A", "A") should be(Some(0))

  test("single letter different strands"):
    pending
    Hamming.distance("G", "T") should be(Some(1))

  test("long identical strands"):
    pending
    Hamming.distance("GGACTGAAATCTG", "GGACTGAAATCTG") should be(Some(0))

  test("long different strands"):
    pending
    Hamming.distance("GGACGGATTCTG", "AGGACGGATTCT") should be(Some(9))

  test("disallow first strand longer"):
    pending
    Hamming.distance("AATG", "AAA") should be(None)

  test("disallow second strand longer"):
    pending
    Hamming.distance("ATA", "AGTG") should be(None)

  test("disallow empty first strand"):
    pending
    Hamming.distance("", "G") should be(None)

  test("disallow empty second strand"):
    pending
    Hamming.distance("G", "") should be(None)
