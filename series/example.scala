object Series {
  def slices(n: Int, s: String): Seq[Seq[Int]] =
    s.sliding(n).map(seq => seq.map(c => c.asDigit)).toSeq
}
