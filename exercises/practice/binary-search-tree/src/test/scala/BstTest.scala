import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class BstTest extends AnyFunSuite with Matchers:

  test("data is retained"):
    val tree = Bst(4)
    tree.value should be(4)
    tree.left should be(None)
    tree.right should be(None)

  test("insert data at proper node - smaller number at left node"):
    pending
    val tree = Bst(4).insert(2)
    tree.value should be(4)
    tree.left.get.value should be(2)
    tree.left.get.left should be(None)
    tree.left.get.right should be(None)
    tree.right should be(None)

  test("insert data at proper node - same number at left node"):
    pending
    val tree = Bst(4).insert(4)
    tree.value should be(4)
    tree.left.get.value should be(4)
    tree.left.get.left should be(None)
    tree.left.get.right should be(None)
    tree.right should be(None)

  test("insert data at proper node - greater number at right node"):
    pending
    val tree = Bst(4).insert(5)
    tree.value should be(4)
    tree.left should be(None)
    tree.right.get.value should be(5)
    tree.right.get.left should be(None)
    tree.right.get.right should be(None)

  test("can create complex tree"):
    pending
    val tree = Bst.fromList(List(4, 2, 6, 1, 3, 5, 7))
    tree.value should be(4)
    tree.left.get.value should be(2)
    tree.left.get.left.get.value should be(1)
    tree.left.get.left.get.left should be(None)
    tree.left.get.left.get.right should be(None)
    tree.left.get.right.get.value should be(3)
    tree.left.get.right.get.left should be(None)
    tree.left.get.right.get.right should be(None)
    tree.right.get.value should be(6)
    tree.right.get.left.get.value should be(5)
    tree.right.get.left.get.left should be(None)
    tree.right.get.left.get.right should be(None)
    tree.right.get.right.get.value should be(7)
    tree.right.get.right.get.left should be(None)
    tree.right.get.right.get.right should be(None)

  test("can sort data - can sort single number"):
    pending
    Bst.toList(Bst(2)) should be(List(2))

  test("can sort data - can sort if second number is smaller than first"):
    pending
    Bst.toList(Bst(2).insert(1)) should be(List(1, 2))

  test("can sort data - can sort if second number is same as first"):
    pending
    Bst.toList(Bst(2).insert(2)) should be(List(2, 2))

  test("can sort data - can sort if second number is greater than first"):
    pending
    Bst.toList(Bst(2).insert(3)) should be(List(2, 3))

  test("can sort data - can sort complex tree"):
    pending
    Bst.toList(Bst.fromList(List(2, 1, 3, 6, 7, 5))) should be(List(1, 2, 3, 5, 6, 7))

  // Not in the canonical data: the tree is generic, so it must work for other ordered types too.

  test("data is retained - chars"):
    pending
    Bst('d').value should be('d')

  test("insert data at proper node - smaller char at left node"):
    pending
    Bst('d').insert('a').left.get.value should be('a')

  test("can sort data - can sort complex tree of chars"):
    pending
    Bst.toList(Bst.fromList(List('d', 'b', 'a', 'c', 'f', 'g', 'e'))) should be(List('a', 'b', 'c', 'd', 'e', 'f', 'g'))
