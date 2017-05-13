/**
 * Copyright 2017 © Denmar Technical Services Inc
 * The U.S. Government has unlimited rights per DFAR 252.227-7014, all other
 * rights reserved.
 * <p>
 * WARNING - This software contains Technical Data whose export is restricted by
 * the Arms Export Control Act (Title 22, U.S.C., Sec 2751, et seq.) or the
 * Export Administration Act of 1979, as amended (Title 50, U.S. C. App. 2401
 * et seq.). Violations of these export laws are subject to severe criminal
 * penalties.
 */

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

  def translate(input: String): Option[Seq[String]] = {
    @tailrec
    def translate_internal(acc: Seq[String], strs: Seq[String]): Option[Seq[String]] = {
      strs match {
        case x::xs => translations get x match {
          case Some(codon) => if ("STOP".equals(codon)) Some(acc.reverse) else translate_internal(codon +: acc, xs)
          case _ => None
        }
        case _ => Some(acc.reverse)
      }
    }

    translate_internal(Seq(), input.grouped(3).toList)
  }
}
