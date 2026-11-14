object Strain:
  def keep[A](seq: Seq[A], predicate: A => Boolean): Seq[A] =
    seq.foldRight(List.empty[A])((item, kept) => if predicate(item) then item :: kept else kept)

  def discard[A](seq: Seq[A], predicate: A => Boolean): Seq[A] = keep(seq, item => !predicate(item))
