import org.scalatest.{FunSuite, Matchers}

class ProteinTranslationTest extends FunSuite with Matchers {
  test("Identifies methionine codon") {
    ProteinTranslation.translate("AUG") should be(Some(Seq("Methionine")))
  }

  test("Identifies phenylalanine codons") {
    pending
    ProteinTranslation.translate("UUU") should be(Some(Seq("Phenylalanine")))
    ProteinTranslation.translate("UUC") should be(Some(Seq("Phenylalanine")))
  }

  test("Identifies leucine codons") {
    pending
    ProteinTranslation.translate("UUA") should be(Some(Seq("Leucine")))
    ProteinTranslation.translate("UUG") should be(Some(Seq("Leucine")))
  }

  test("Identifies serine codons") {
    pending
    ProteinTranslation.translate("UCU") should be(Some(Seq("Serine")))
    ProteinTranslation.translate("UCC") should be(Some(Seq("Serine")))
    ProteinTranslation.translate("UCA") should be(Some(Seq("Serine")))
    ProteinTranslation.translate("UCG") should be(Some(Seq("Serine")))
  }

  test("Identifies tyrosine codons") {
    pending
    ProteinTranslation.translate("UAU") should be(Some(Seq("Tyrosine")))
    ProteinTranslation.translate("UAC") should be(Some(Seq("Tyrosine")))
  }

  test("Identifies cysteine codons") {
    pending
    ProteinTranslation.translate("UGU") should be(Some(Seq("Cysteine")))
    ProteinTranslation.translate("UGU") should be(Some(Seq("Cysteine")))
  }

  test("Identifies tryptophan codons") {
    pending
    ProteinTranslation.translate("UGG") should be(Some(Seq("Tryptophan")))
    ProteinTranslation.translate("UGG") should be(Some(Seq("Tryptophan")))
  }

  test("Translate RNA strand into correct protein") {
    pending
    ProteinTranslation.translate("AUGUUUUGG") should be(Some(Seq("Methionine", "Phenylalanine", "Tryptophan")))
  }

  test("Stops translation if stop codon is present") {
    pending
    ProteinTranslation.translate("AUGUUUUAA") should be(Some(Seq("Methionine", "Phenylalanine")))
  }

  test("Stops translation of longest strand") {
    pending
    ProteinTranslation.translate("UGGUGUUAUUAAUGGUUU") should be(Some(Seq("Tryptophan", "Cysteine", "Tyrosine")))
  }

  test("Return NONE for invalid Codon") {
    pending
    ProteinTranslation.translate("CARROT") should be(None)
  }
}