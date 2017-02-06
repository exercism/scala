object Strain {
  def keep[A](traversable: Traversable[A], func: A => Boolean): Traversable[A]
    = for (a <- traversable if func(a)) yield a

  def discard[A](traversable: Traversable[A], func: A => Boolean): Traversable[A]
    = for (a <- traversable if !func(a)) yield a
}
