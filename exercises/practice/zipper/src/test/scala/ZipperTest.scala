import org.scalatest.{FunSuite, Matchers}

/** @version created manually **/
class ZipperTest extends FunSuite with Matchers {
  def empty[A]: Option[BinTree[A]] = None

  def bt[A](v: A, l: Option[BinTree[A]], r: Option[BinTree[A]]): Option[BinTree[A]] =
    Some(BinTree(v, l, r))

  def leaf[A](v: A): Option[BinTree[A]] =
    Some(BinTree(v, None, None))

  val t1: BinTree[Int] = BinTree(1, bt(2, empty,   leaf(3)), leaf(4))
  val t2: BinTree[Int] = BinTree(1, bt(5, empty,   leaf(3)), leaf(4))
  val t3: BinTree[Int] = BinTree(1, bt(2, leaf(5), leaf(3)), leaf(4))
  val t4: BinTree[Int] = BinTree(1, leaf(2),                 leaf(4))

  def fromSome[T](o: Option[T]) = o.get

  val z = Zipper

  test("data is retained") {
   z.toTree(z.fromTree(t1)) should be (t1)
  }

  test("left, right and value") {
    pending
    z.value(fromSome(z.right(fromSome(z.left(z.fromTree(t1)))))) should be (3)
  }

  test("dead end") {
    pending
    (z.left(fromSome(z.left(z.fromTree(t1))))) should be (None)
  }

  test("tree from deep focus") {
    pending
    z.toTree(fromSome(z.right(fromSome(z.left(z.fromTree(t1)))))) should be (t1)
  }

  test("setValue") {
    pending
    z.toTree(z.setValue(5, (fromSome(z.left(z.fromTree(t1)))))) should be (t2)
  }

  test("setLeft with Some") {
    pending
    z.toTree(z.setLeft(Some(BinTree(5, None, None)),
        (fromSome(z.left(z.fromTree(t1)))))) should be (t3)
  }

  test("setRight with None") {
    pending
    z.toTree(z.setRight(None, (fromSome(z.left(z.fromTree(t1)))))) should be (t4)
  }

  test("different paths to same zipper") {
    pending
    z.right(fromSome(z.up(fromSome(z.left(z.fromTree(t1)))))) should be
      (z.right(z.fromTree(t1)))
  }
}

