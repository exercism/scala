import scala.annotation.tailrec

object Strain {
  def keep[A](seq: Seq[A], func: A => Boolean): Seq[A] =
    recurse(seq, func, List()).reverse

  def discard[A](seq: Seq[A], func: A => Boolean): Seq[A] = {
    val notFunc: A => Boolean = a => !func(a)
    recurse(seq, notFunc, List()).reverse
  }

  @tailrec
  private def recurse[A](seq: Seq[A], func: A => Boolean, acc: Seq[A]): Seq[A] =
    seq match {
      case Nil => acc
      case x::xs =>
        if (func(x))
          recurse(xs, func, x +: acc)
        else
          recurse(xs, func, acc)
    }
}
