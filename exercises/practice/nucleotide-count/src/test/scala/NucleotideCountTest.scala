import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class NucleotideCountTest extends AnyFunSuite with Matchers:

  test("empty strand"):
    DNA("").nucleotideCounts should be(Right(Map('A' -> 0, 'C' -> 0, 'G' -> 0, 'T' -> 0)))

  test("can count one nucleotide in single-character input"):
    pending
    DNA("G").nucleotideCounts should be(Right(Map('A' -> 0, 'C' -> 0, 'G' -> 1, 'T' -> 0)))

  test("strand with repeated nucleotide"):
    pending
    DNA("GGGGGGG").nucleotideCounts should be(Right(Map('A' -> 0, 'C' -> 0, 'G' -> 7, 'T' -> 0)))

  test("strand with multiple nucleotides"):
    pending
    DNA("AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC").nucleotideCounts should be(
      Right(Map('A' -> 20, 'C' -> 12, 'G' -> 17, 'T' -> 21)),
    )

  test("strand with invalid nucleotides"):
    pending
    DNA("AGXXACT").nucleotideCounts.isLeft should be(true)
