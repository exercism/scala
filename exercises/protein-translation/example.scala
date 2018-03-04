import scala.annotation.tailrec

object ProteinTranslation {

  private val translations = Map(
    "AUG" -> "Methionine",
    "UUU" -> "Phenylalanine",
    "UUC" -> "Phenylalanine",
    "UUA" -> "Leucine",
    "UUG" -> "Leucine",
    "UCU" -> "Serine",
    "UCC" -> "Serine",
    "UCA" -> "Serine",
    "UCG" -> "Serine",
    "UAU" -> "Tyrosine",
    "UAC" -> "Tyrosine",
    "UGU" -> "Cysteine",
    "UGC" -> "Cysteine",
    "UGG" -> "Tryptophan",
    "UAA" -> "STOP",
    "UAG" -> "STOP",
    "UGA" -> "STOP")

  /**
    * Note that this solution ignores invalid Codons.
    */
  def proteins(input: String): Seq[String] = {
    @tailrec
    def proteins_internal(acc: Seq[String], strs: Seq[String]): Seq[String] = {
      strs match {
        case x::xs => translations get x match {
          case Some(codon) => if ("STOP".equals(codon)) acc.reverse else proteins_internal(codon +: acc, xs)
          case _ => proteins_internal(acc, xs)
        }
        case _ => acc.reverse
      }
    }

    proteins_internal(Seq(), input.grouped(3).toList)
  }
}
