import org.scalatest.{Matchers, FunSuite}

class DnaTest extends FunSuite with Matchers {
  test("transcribes blank string") {
    Dna.toRna("") should be (Some(""))
  }

  test("transcribes cytidine to guanine") {
    Dna.toRna("C") should be (Some("G"))
  }

  test("transcribes guanosine to cytosine") {
    Dna.toRna("G") should be (Some("C"))
  }

  test("transcribes adenosine to uracil") {
    Dna.toRna("A") should be (Some("U"))
  }

  test("transcribes thymidine to adenine") {
    Dna.toRna("T") should be (Some("A"))
  }

  test("transcribes all ACGT to UGCA") {
    Dna.toRna("ACGTGGTCTTAA") should be (Some("UGCACCAGAAUU"))
  }

  test("transcribes RNA only nucleotide uracil to None") {
    Dna.toRna("U") should be (None)
  }

  test("transcribes completely invalid DNA to None") {
    Dna.toRna("XXX") should be (None)
  }

  test("transcribes partially invalid DNA to None") {
    Dna.toRna("ACGTXXXCTTAA") should be (None)
  }
}

