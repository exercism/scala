import org.scalatest.{FlatSpec, Matchers}

class SimpleLinkedListTest extends FlatSpec with Matchers {

  it should "handle single item list" in {
    val list = SimpleLinkedList().add(1)
    list.isEmpty should be (false)
    list.value should be (1)
  }

  it should "handle single item has no next item" in {
    pending
    val list = SimpleLinkedList().add(1)
    list.next.isEmpty should be (true)
  }

  it should "handle two item list" in {
    pending
    val list = SimpleLinkedList().add(1).add(2)
    list.value should be (1)
    list.next.value should be(2)
  }

  it should "handle two item list has no next value" in {
    pending
    val list = SimpleLinkedList().add(1).add(2)
    list.next.next.isEmpty should be(true)
  }

  it should "allow creation from an Array" in {
    pending
    val list = SimpleLinkedList(Array(3, 2, 1))
    list.value should be (3)
    list.next.value should be (2)
    list.next.next.value should be (1)
  }

  it should "allow conversion to an Array" in {
    pending
    val list = SimpleLinkedList(Array(3, 2, 1))
    val arr = list.toArray
    arr should be (Array(3, 2, 1))
  }

  it should "handle reverse" in {
    pending
    val list = SimpleLinkedList(Array(1, 2, 3, 4, 5, 6))
    val reversed = list.reverse
    reversed.value should be (6)
    reversed.next.value should be (5)
    reversed.next.next.value should be (4)
    reversed.next.next.next.value should be (3)
    reversed.next.next.next.next.value should be (2)
    reversed.next.next.next.next.next.value should be (1)
  }
}
