import org.scalatest.{Matchers, FlatSpec}

class DequeTest extends FlatSpec with Matchers {

  it should "handle push then pop" in {
    val deque = Deque[Char]()
    deque.push('a')
    deque.push('b')
    deque.pop should be (Some('b'))
    deque.pop should be (Some('a'))
//    linkedList.pop should be (None)
  }

  it should "handle push then shift" in {
    val deque = Deque[Char]()
    deque.push('a')
    deque.push('b')
    deque.shift should be (Some('a'))
    deque.shift should be (Some('b'))
//    linkedList.shift should be (None)
  }

  it should "handle unshift then shift" in {
    val deque = Deque[Char]()
    deque.unshift('a')
    deque.unshift('b')
    deque.shift should be (Some('b'))
    deque.shift should be (Some('a'))
  }

  it should "handle unshift then pop" in {
    val deque = Deque[Char]()
    deque.unshift('a')
    deque.unshift('b')
    deque.pop should be (Some('a'))
    deque.pop should be (Some('b'))
  }

  it should "handle complex interaction" in {
    val deque = Deque[Int]()
    deque.push(1)
    deque.push(2)
    deque.pop should be (Some(2))
    deque.push(3)
    deque.unshift(4)
    deque.push(5)
    deque.shift should be (Some(4))
    deque.pop should be (Some(5))
    deque.pop should be (Some(3))
  }
}
