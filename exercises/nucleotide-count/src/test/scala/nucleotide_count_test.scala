import org.scalatest._

class NucleotideCountSpecs extends FlatSpec with Matchers {
  "empty dna string" should "have no adenine" in {
    new DNA("").nucleotideCounts('A') should be (0)
  }

  it should "have no nucleotides" in {
    pending
    val expected = Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 0)
    new DNA("").nucleotideCounts should be (expected)
  }

  "a repetitive sequence" should "count cytosine" in {
    pending
    new DNA("CCCCC").nucleotideCounts('C') should be (5)
  }

  it should "have only guanine" in {
    pending
    val expected = Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 8)
    new DNA("GGGGGGGG").nucleotideCounts should be (expected)
  }

  "a mixed dna string" should "count only thymine" in {
    pending
    new DNA("GGGGGTAACCCGG").nucleotideCounts('T') should be (1)
  }

  it should "count a nucleotide only once" in {
    pending
    val dna = new DNA("CGATTGGG")
    dna.nucleotideCounts('T')
    dna.nucleotideCounts('T') should be (2)
  }

  it should "not change counts after counting adenine" in {
    pending
    val dna = new DNA("GATTACA")
    dna.nucleotideCounts('A')
    val expected = Map('A' -> 3, 'T' -> 2, 'C' -> 1, 'G' -> 1)
    dna.nucleotideCounts should be (expected)
  }

  it should "validate nucleotides" in {
    pending
    a [RuntimeException] should be thrownBy new DNA("GACT").nucleotideCounts('X')
  }

  it should "validate dna not rna" in {
    pending
    a [RuntimeException] should be thrownBy new DNA("ACGU")
  }

  it should "validate dna" in {
    pending
    a [RuntimeException] should be thrownBy new DNA("John")
  }

  it should "count all nucleotides" in {
    pending
    val s = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC"
    val dna = new DNA(s)
    val expected = Map('A' -> 20, 'T' -> 21, 'G' -> 17, 'C' -> 12)
    dna.nucleotideCounts should be (expected)
  }
}
