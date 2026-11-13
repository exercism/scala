import scala.annotation.tailrec

// A binary tree.
case class BinTree[A](value: A, left: Option[BinTree[A]], right: Option[BinTree[A]])

/** The way back up: which side we came down, the parent's value and the other subtree. */
enum Crumb[A]:
  case LeftCrumb(value: A, right: Option[BinTree[A]])
  case RightCrumb(value: A, left: Option[BinTree[A]])

import Crumb.*

// Implementation after http://learnyouahaskell.com/zippers
object Zipper:
  // A zipper for a binary tree: the focus and the crumbs back to the root.
  type Zipper[A] = (BinTree[A], List[Crumb[A]])

  // Get a zipper focussed on the root node.
  def fromTree[A](bt: BinTree[A]): Zipper[A] = (bt, Nil)

  // Get the complete tree from a zipper.
  @tailrec
  def toTree[A](zipper: Zipper[A]): BinTree[A] = up(zipper) match
    case Some(parent) => toTree(parent)
    case None         => zipper._1

  // Get the value of the focus node.
  def value[A](zipper: Zipper[A]): A = zipper._1.value

  // Get the left child of the focus node, if any.
  def left[A](zipper: Zipper[A]): Option[Zipper[A]] =
    val (BinTree(x, l, r), crumbs) = zipper
    l.map((_, LeftCrumb(x, r) :: crumbs))

  // Get the right child of the focus node, if any.
  def right[A](zipper: Zipper[A]): Option[Zipper[A]] =
    val (BinTree(x, l, r), crumbs) = zipper
    r.map((_, RightCrumb(x, l) :: crumbs))

  // Get the parent of the focus node, if any.
  def up[A](zipper: Zipper[A]): Option[Zipper[A]] = zipper match
    case (t, LeftCrumb(x, r) :: crumbs)  => Some((BinTree(x, Some(t), r), crumbs))
    case (t, RightCrumb(x, l) :: crumbs) => Some((BinTree(x, l, Some(t)), crumbs))
    case (_, Nil)                        => None

  // Set the value of the focus node.
  def setValue[A](v: A, zipper: Zipper[A]): Zipper[A] = (zipper._1.copy(value = v), zipper._2)

  // Replace a left child tree.
  def setLeft[A](l: Option[BinTree[A]], zipper: Zipper[A]): Zipper[A] = (zipper._1.copy(left = l), zipper._2)

  // Replace a right child tree.
  def setRight[A](r: Option[BinTree[A]], zipper: Zipper[A]): Zipper[A] = (zipper._1.copy(right = r), zipper._2)
