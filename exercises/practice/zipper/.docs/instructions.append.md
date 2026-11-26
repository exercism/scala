# Instructions append

## Track specific instructions

The instructions use a rose tree as an example.
On this track the zipper is for a binary tree: a `BinTree` has a value, an optional left child and an optional right child.
The operations are the ones in the stub (`fromTree`, `toTree`, `value`, `left`, `right`, `up`, `setValue`, `setLeft` and `setRight`), so there is no `prev`, `next`, `insert_before`, `insert_after` or `delete` to implement.

The stub does not compile as it is, because the type `Zipper[A]` is missing.
Designing that type is the heart of the exercise.
You can define it as a type alias inside `object Zipper`, where the comment `??? Zipper[A] ???` is, or as a case class, either inside the object or next to it.

Whatever you choose, two zippers that focus on the same node of the same tree must be equal, however they got there: the tests compare zippers with each other.
Case classes, tuples and immutable collections are compared by their contents.
An ordinary `class` is not, unless you write `equals` yourself.
