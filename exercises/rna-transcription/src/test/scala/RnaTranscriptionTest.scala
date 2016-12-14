import org.scalatest.{Matchers, FunSuite}

class DnaTest extends FunSuite with Matchers {
  test("transcribes blank string") {
    Dna.toRna("") should be (Some(""))
  }

  test("transcribes cytidine to guanine") {
    pending
    Dna.toRna("C") should be (Some("G"))
  }

  test("transcribes guanosine to cytosine") {
    pending
    Dna.toRna("G") should be (Some("C"))
  }

  test("transcribes adenosine to uracil") {
    pending
    Dna.toRna("A") should be (Some("U"))
  }

  test("transcribes thymidine to adenine") {
    pending
    Dna.toRna("T") should be (Some("A"))
  }

  test("transcribes all ACGT to UGCA") {
    pending
    Dna.toRna("ACGTGGTCTTAA") should be (Some("UGCACCAGAAUU"))
  }

  test("transcribes RNA only nucleotide uracil to None") {
    pending
    Dna.toRna("U") should be (None)
  }

  test("transcribes completely invalid DNA to None") {
    pending
    Dna.toRna("XXX") should be (None)
  }

  test("transcribes partially invalid DNA to None") {
    pending
    Dna.toRna("ACGTXXXCTTAA") should be (None)
  }
}

