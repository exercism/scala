import org.scalatest.{Matchers, FlatSpec}

class CustomSetTest extends FlatSpec with Matchers {

  it should "round trip from a List" in {
    val expectedList = List(1, 2, 3)
    val set = CustomSet.fromList(expectedList)
    val list = CustomSet.toList(set)
    list.sorted should be (expectedList)
  }

  it should "round trip from an empty List" in {
    val expectedList = List()
    val set = CustomSet.fromList(expectedList)
    val list = CustomSet.toList(set)
    list should be (expectedList)
  }

  it should "be empty" in {
    CustomSet.empty(CustomSet.fromList(List())) should be (true)
  }

  it should "not be empty" in {
    CustomSet.empty(CustomSet.fromList(List('a'))) should be (false)
  }

  it should "determine singletons" in {
    CustomSet.singleton(CustomSet.fromList(List())) should be (false)
    CustomSet.singleton(CustomSet.fromList(List(1.0))) should be (true)
    CustomSet.singleton(CustomSet.fromList(List(1.0, 2.0))) should be (false)
  }

  it should "determine size" in {
    CustomSet.size(CustomSet.fromList(List())) should be (0)
    CustomSet.size(CustomSet.fromList(List(1.0))) should be (1)
    CustomSet.size(CustomSet.fromList(List(1.0, 2.0))) should be (2)
  }

  it should "determine membership" in {
    CustomSet.member(CustomSet.fromList(List()), 'c') should be (false)
    CustomSet.member(CustomSet.fromList(List('A', 'B', 'C')), 'A') should be (true)
    CustomSet.member(CustomSet.fromList(List('A', 'B', 'C')), 'B') should be (true)
    CustomSet.member(CustomSet.fromList(List('A', 'B', 'C')), 'C') should be (true)
    CustomSet.member(CustomSet.fromList(List('A', 'B', 'C')), 'd') should be (false)
  }

  it should "handle insert" in {
    CustomSet.toList(CustomSet.insert(CustomSet.fromList(List()), 'c')) should be (List('c'))
    CustomSet.toList(CustomSet.insert(CustomSet.fromList(List('c')), 'c')) should be (List('c'))
    CustomSet.toList(CustomSet.insert(CustomSet.fromList(List(2, 3)), 1)).sorted should be (List(1, 2, 3))
    CustomSet.toList(CustomSet.insert(CustomSet.fromList(List(2, 3)), 4)).sorted should be (List(2, 3, 4))
  }

  it should "handle delete" in {
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List()), 'c')) should be (List())
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List('c')), 'c')) should be (List())
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List('a', 'b', 'c')), 'c')).sorted should be (List('a', 'b'))
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List(1, 2, 3)), 1)).sorted should be (List(2, 3))
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List(1, 2, 3)), 2)).sorted should be (List(1, 3))
    CustomSet.toList(CustomSet.delete(CustomSet.fromList(List(1, 2, 3)), 0)).sorted should be (List(1, 2, 3))
  }

  it should "handle union" in {
    CustomSet.toList(CustomSet.union(CustomSet.fromList(List()), CustomSet.fromList(List()))) should be (List())
    CustomSet.toList(CustomSet.union(CustomSet.fromList(List('a')), CustomSet.fromList(List('b', 'c')))).sorted should be (List('a', 'b', 'c'))
    CustomSet.toList(CustomSet.union(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c')))).sorted should be (List('a', 'b', 'c'))
  }

  it should "handle difference" in {
    CustomSet.toList(CustomSet.difference(CustomSet.fromList(List()), CustomSet.fromList(List()))) should be (List())
    CustomSet.toList(CustomSet.difference(CustomSet.fromList(List('a')), CustomSet.fromList(List('b', 'c')))).sorted should be (List('a', 'b', 'c'))
    CustomSet.toList(CustomSet.difference(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c')))).sorted should be (List('a', 'c'))
    CustomSet.toList(CustomSet.difference(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'a')))).sorted should be (List())
  }

  it should "handle intersection" in {
    CustomSet.toList(CustomSet.intersection(CustomSet.fromList(List()), CustomSet.fromList(List()))) should be (List())
    CustomSet.toList(CustomSet.intersection(CustomSet.fromList(List('a')), CustomSet.fromList(List('b', 'c')))).sorted should be (List())
    CustomSet.toList(CustomSet.intersection(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c')))).sorted should be (List('b'))
    CustomSet.toList(CustomSet.intersection(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('a', 'b')))).sorted should be (List('a', 'b'))
  }

  it should "handle isSubSet" in {
    CustomSet.isSubsetOf(CustomSet.fromList(List()), CustomSet.fromList(List())) should be (true)
    CustomSet.isSubsetOf(CustomSet.fromList(List()), CustomSet.fromList(List('b', 'c'))) should be (true)
    CustomSet.isSubsetOf(CustomSet.fromList(List('a')), CustomSet.fromList(List('b', 'c'))) should be (false)
    CustomSet.isSubsetOf(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c'))) should be (false)
    CustomSet.isSubsetOf(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c', 'a'))) should be (true)
    CustomSet.isSubsetOf(CustomSet.fromList(List('a', 'b', 'c', 'd')), CustomSet.fromList(List('b', 'c', 'a'))) should be (false)
  }

  it should "handle isDisjointFrom" in {
    CustomSet.isDisjointFrom(CustomSet.fromList(List()), CustomSet.fromList(List())) should be (true)
    CustomSet.isDisjointFrom(CustomSet.fromList(List()), CustomSet.fromList(List('b', 'c'))) should be (true)
    CustomSet.isDisjointFrom(CustomSet.fromList(List('a')), CustomSet.fromList(List('b', 'c'))) should be (true)
    CustomSet.isDisjointFrom(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c'))) should be (false)
    CustomSet.isDisjointFrom(CustomSet.fromList(List('a', 'b')), CustomSet.fromList(List('b', 'c', 'a'))) should be (false)
  }
}
