import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class RnaTranscriptionTest extends AnyFunSuite with Matchers:

  test("Empty RNA sequence"):
    RnaTranscription.toRna("") should be(Some(""))

  test("RNA complement of cytosine is guanine"):
    pending
    RnaTranscription.toRna("C") should be(Some("G"))

  test("RNA complement of guanine is cytosine"):
    pending
    RnaTranscription.toRna("G") should be(Some("C"))

  test("RNA complement of thymine is adenine"):
    pending
    RnaTranscription.toRna("T") should be(Some("A"))

  test("RNA complement of adenine is uracil"):
    pending
    RnaTranscription.toRna("A") should be(Some("U"))

  test("RNA complement"):
    pending
    RnaTranscription.toRna("ACGTGGTCTTAA") should be(Some("UGCACCAGAAUU"))
