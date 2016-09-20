import org.scalatest._

class NucleotideCountSpecs extends FlatSpec with Matchers {
  behavior of "count"

  it should "have no adenine for an empty dna string" in {
    new DNA("").count('A') should be (Right(0))
  }

  it should "count cytosine for a repetitive sequence" in {
    pending
    new DNA("CCCCC").count('C') should be (Right(5))
  }

  it should "count only thymine for a mixed dna string" in {
    pending
    new DNA("GGGGGTAACCCGG").count('T') should be (Right(1))
  }

  it should "validate nucleotides" in {
    pending
    new DNA("GACT").count('X') should be (Left("invalid nucleotide 'X'"))
  }

  it should "validate dna" in {
    pending
    new DNA("GACYT").count('G') should be (Left("invalid nucleotide 'Y'"))
  }

  behavior of "nucleotideCounts"

  it should "have no nucleotides" in {
    pending
    val expected = Right(Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 0))
    new DNA("").nucleotideCounts should be (expected)
  }

  it should "have only guanine" in {
    pending
    val expected = Right(Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 8))
    new DNA("GGGGGGGG").nucleotideCounts should be (expected)
  }

  it should "count a nucleotide only once" in {
    pending
    val dna = new DNA("CGATTGGG")
    val counts = dna.nucleotideCounts.right.get
    counts('T')
    counts('T') should be (2)
  }

  it should "not change counts after counting adenine" in {
    pending
    val dna = new DNA("GATTACA")
    val counts = dna.nucleotideCounts.right.get
    counts('A')
    val expected = Right(Map('A' -> 3, 'T' -> 2, 'C' -> 1, 'G' -> 1))
    dna.nucleotideCounts should be (expected)
  }

  it should "count all nucleotides" in {
    pending
    val s = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"
    val dna = new DNA(s)
    val expected = Right(Map('A' -> 20, 'T' -> 21, 'G' -> 17, 'C' -> 12))
    dna.nucleotideCounts should be (expected)
  }

  it should "validate dna" in {
    pending
    new DNA("GACYT").nucleotideCounts should be (Left("invalid nucleotide 'Y'"))
  }
}

