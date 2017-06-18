import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class RnaTranscriptionTest extends FunSuite with Matchers {

  test("rna complement of cytosine is guanine") {
    RnaTranscription.toRna("C") should be (Some("G"))
  }

  test("rna complement of guanine is cytosine") {
    pending
    RnaTranscription.toRna("G") should be (Some("C"))
  }

  test("rna complement of thymine is adenine") {
    pending
    RnaTranscription.toRna("T") should be (Some("A"))
  }

  test("rna complement of adenine is uracil") {
    pending
    RnaTranscription.toRna("A") should be (Some("U"))
  }

  test("rna complement") {
    pending
    RnaTranscription.toRna("ACGTGGTCTTAA") should be (Some("UGCACCAGAAUU"))
  }

  test("dna correctly handles invalid input") {
    pending
    RnaTranscription.toRna("U") should be (None)
  }

  test("dna correctly handles completely invalid input") {
    pending
    RnaTranscription.toRna("XXX") should be (None)
  }

  test("dna correctly handles partially invalid input") {
    pending
    RnaTranscription.toRna("ACGTXXXCTTAA") should be (None)
  }
}