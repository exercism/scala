import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class ZipperTest extends AnyFunSuite with Matchers:

  private def leaf(value: Int): Option[BinTree[Int]] = Some(BinTree(value, None, None))

  private def node(value: Int, left: Option[BinTree[Int]], right: Option[BinTree[Int]]): Option[BinTree[Int]] = Some(BinTree(value, left, right))

  test("data is retained"):
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(2, None, leaf(3)), leaf(4))))

  test("left, right and value"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .map(Zipper.value) should be(Some(3))

  test("dead end"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.left) should be(None)

  test("tree from deep focus"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .map(Zipper.toTree) should be(Some(BinTree(1, node(2, None, leaf(3)), leaf(4))))

  test("traversing up from top"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.up) should be(None)

  test("left, right, and up"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.up)
      .flatMap(Zipper.right)
      .flatMap(Zipper.up)
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .map(Zipper.value) should be(Some(3))

  test("test ability to descend multiple levels and return"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .flatMap(Zipper.up)
      .flatMap(Zipper.up)
      .map(Zipper.value) should be(Some(1))

  test("set_value"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .map(Zipper.setValue(5, _))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(5, None, leaf(3)), leaf(4))))

  test("set_value after traversing up"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .flatMap(Zipper.up)
      .map(Zipper.setValue(5, _))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(5, None, leaf(3)), leaf(4))))

  test("set_left with leaf"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .map(Zipper.setLeft(leaf(5), _))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(2, leaf(5), leaf(3)), leaf(4))))

  test("set_right with null"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .map(Zipper.setRight(None, _))
      .map(Zipper.toTree) should be(Some(BinTree(1, leaf(2), leaf(4))))

  test("set_right with subtree"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .map(Zipper.setRight(node(6, leaf(7), leaf(8)), _))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(2, None, leaf(3)), node(6, leaf(7), leaf(8)))))

  test("set_value on deep focus"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.right)
      .map(Zipper.setValue(5, _))
      .map(Zipper.toTree) should be(Some(BinTree(1, node(2, None, leaf(5)), leaf(4))))

  test("different paths to same zipper"):
    pending
    val tree = BinTree(1, node(2, None, leaf(3)), leaf(4))
    Some(Zipper.fromTree(tree))
      .flatMap(Zipper.left)
      .flatMap(Zipper.up)
      .flatMap(Zipper.right) should be(Some(Zipper.fromTree(tree)).flatMap(Zipper.right))
