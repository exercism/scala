import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class ProteinTranslationTest extends AnyFunSuite with Matchers {

  test("Methionine RNA sequence") {
    ProteinTranslation.proteins("AUG") should be(Seq("Methionine"))
  }

  test("Phenylalanine RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UUU") should be(Seq("Phenylalanine"))
  }

  test("Phenylalanine RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UUC") should be(Seq("Phenylalanine"))
  }

  test("Leucine RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UUA") should be(Seq("Leucine"))
  }

  test("Leucine RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UUG") should be(Seq("Leucine"))
  }

  test("Serine RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UCU") should be(Seq("Serine"))
  }

  test("Serine RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UCC") should be(Seq("Serine"))
  }

  test("Serine RNA sequence 3") {
    pending
    ProteinTranslation.proteins("UCA") should be(Seq("Serine"))
  }

  test("Serine RNA sequence 4") {
    pending
    ProteinTranslation.proteins("UCG") should be(Seq("Serine"))
  }

  test("Tyrosine RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UAU") should be(Seq("Tyrosine"))
  }

  test("Tyrosine RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UAC") should be(Seq("Tyrosine"))
  }

  test("Cysteine RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UGU") should be(Seq("Cysteine"))
  }

  test("Cysteine RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UGC") should be(Seq("Cysteine"))
  }

  test("Tryptophan RNA sequence") {
    pending
    ProteinTranslation.proteins("UGG") should be(Seq("Tryptophan"))
  }

  test("STOP codon RNA sequence 1") {
    pending
    ProteinTranslation.proteins("UAA") should be(Seq())
  }

  test("STOP codon RNA sequence 2") {
    pending
    ProteinTranslation.proteins("UAG") should be(Seq())
  }

  test("STOP codon RNA sequence 3") {
    pending
    ProteinTranslation.proteins("UGA") should be(Seq())
  }

  test("Translate RNA strand into correct protein list") {
    pending
    ProteinTranslation.proteins("AUGUUUUGG") should be(
      Seq("Methionine", "Phenylalanine", "Tryptophan"))
  }

  test("Translation stops if STOP codon at beginning of sequence") {
    pending
    ProteinTranslation.proteins("UAGUGG") should be(Seq())
  }

  test("Translation stops if STOP codon at end of two-codon sequence") {
    pending
    ProteinTranslation.proteins("UGGUAG") should be(Seq("Tryptophan"))
  }

  test("Translation stops if STOP codon at end of three-codon sequence") {
    pending
    ProteinTranslation.proteins("AUGUUUUAA") should be(
      Seq("Methionine", "Phenylalanine"))
  }

  test("Translation stops if STOP codon in middle of three-codon sequence") {
    pending
    ProteinTranslation.proteins("UGGUAGUGG") should be(Seq("Tryptophan"))
  }

  test("Translation stops if STOP codon in middle of six-codon sequence") {
    pending
    ProteinTranslation.proteins("UGGUGUUAUUAAUGGUUU") should be(
      Seq("Tryptophan", "Cysteine", "Tyrosine"))
  }
}
