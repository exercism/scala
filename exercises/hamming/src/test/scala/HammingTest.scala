import org.scalatest._

class HammingTest extends FlatSpec with Matchers {
  it should "detect no difference between empty strands" in {
    Hamming.compute("", "") should be (Some(0))
  }

  it should "detect no difference between identical strands" in {
    pending
    Hamming.compute("GGACTGA", "GGACTGA") should be (Some(0))
  }

  it should "detect complete hamming distance in small strand" in {
    pending
    Hamming.compute("ACT", "GGA") should be (Some(3))
  }

  it should "give hamming distance in off by one strand" in {
    pending
    Hamming.compute("GGACGGATTCTG", "AGGACGGATTCT") should be (Some(9))
  }

  it should "give small hamming distance in middle somewhere" in {
    pending
    Hamming.compute("GGACG", "GGTCG") should be (Some(1))
  }

  it should "give a larger distance" in {
    pending
    Hamming.compute("ACCAGGG", "ACTATGG") should be (Some(2))
  }

  it should "be undefined for first String longer" in {
    pending
    Hamming.compute("AGGCTAGCGGTAGGAC", "AAACTAGGGG") should be (None)
  }

  it should "be undefined for second String longer" in {
    pending
    Hamming.compute("AAACTAGGGG", "AGGCTAGCGGTAGGAC") should be (None)
  }
}

