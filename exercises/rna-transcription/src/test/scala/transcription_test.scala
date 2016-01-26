import org.scalatest.{Matchers, FunSuite}

class DnaTest extends FunSuite with Matchers {
  test("transcribes blank string") {
    Dna().toRna("") should be ("")
  }

  test("transcribes cytidine to guanosine") {
    Dna().toRna("C") should be ("G")
  }

  test("transcribes guanosine to cytidine") {
    Dna().toRna("G") should be ("C")
  }

  test("transcribes adenosine to uracil") {
    Dna().toRna("A") should be ("U")
  }

  test("transcribes thymidine to adenosine") {
    Dna().toRna("T") should be ("A")
  }

  test("transcribes all ACGT to UGCA") {
    Dna().toRna("ACGTGGTCTTAA") should be ("UGCACCAGAAUU")
  }
}
