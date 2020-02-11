import org.scalatest.{Matchers, FunSuite}

/** @version 2.1.0 */
class HammingTest extends FunSuite with Matchers {
  
  test("empty strands") {
    Hamming.distance("", "") should be (Some(0))
  }

  test("identical strands") {
    pending
    Hamming.distance("A", "A") should be (Some(0))
  }

  test("long identical strands") {
    pending
    Hamming.distance("GGACTGA", "GGACTGA") should be (Some(0))
  }

  test("complete distance in single nucleotide strands") {
    pending
    Hamming.distance("A", "G") should be (Some(1))
  }

  test("complete distance in small strands") {
    pending
    Hamming.distance("AG", "CT") should be (Some(2))
  }

  test("small distance in small strands") {
    pending
    Hamming.distance("AT", "CT") should be (Some(1))
  }

  test("small distance") {
    pending
    Hamming.distance("GGACG", "GGTCG") should be (Some(1))
  }

  test("small distance in long strands") {
    pending
    Hamming.distance("ACCAGGG", "ACTATGG") should be (Some(2))
  }

  test("non-unique character in first strand") {
    pending
    Hamming.distance("AAG", "AAA") should be (Some(1))
  }

  test("non-unique character in second strand") {
    pending
    Hamming.distance("AAA", "AAG") should be (Some(1))
  }

  test("same nucleotides in different positions") {
    pending
    Hamming.distance("TAG", "GAT") should be (Some(2))
  }

  test("large distance") {
    pending
    Hamming.distance("GATACA", "GCATAA") should be (Some(4))
  }

  test("large distance in off-by-one strand") {
    pending
    Hamming.distance("GGACGGATTCTG", "AGGACGGATTCT") should be (Some(9))
  }

  test("disallow first strand longer") {
    pending
    Hamming.distance("AATG", "AAA") should be (None)
  }

  test("disallow second strand longer") {
    pending
    Hamming.distance("ATA", "AGTG") should be (None)
  }
}
