import org.scalatest.{FunSuite, Matchers}

class SublistSpecs extends FunSuite with Matchers {
  test("empty lists") {
    Sublist.sublist(List(), List()) should be (Sublist.Equal)
  }

  test("empty is a sublist of anything") {
    pending
    Sublist.sublist(List(), List('a', 's', 'd', 'f')) should be (Sublist.Sublist)
  }

  test("anything is a superlist of empty") {
    pending
    Sublist.sublist(List('a', 's', 'd', 'f'), List()) should be (Sublist.Superlist)
  }

  test("List(1) is not List(2)") {
    pending
    Sublist.sublist(List(1), List(2)) should be (Sublist.Unequal)
  }

  test("compare larger equal lists") {
    pending
    val xs = List.fill(1000)("x")
    Sublist.sublist(xs, xs) should be (Sublist.Equal)
  }

  test("sublist at start") {
    pending
    Sublist.sublist(List(1, 2, 3), List(1, 2, 3, 4, 5)) should be (Sublist.Sublist)
  }

  test("sublist in middle") {
    pending
    Sublist.sublist(List(4, 3, 2), List(5, 4, 3, 2, 1)) should be (Sublist.Sublist)
  }

  test("sublist at end") {
    pending
    Sublist.sublist(List(3, 4, 5), List(1, 2, 3, 4, 5)) should be (Sublist.Sublist)
  }

  test("partially matching sublist at start") {
    pending
    Sublist.sublist(List(1, 1, 2), List(1, 1, 1, 2)) should be (Sublist.Sublist)
  }

  test("sublist early in huge list") {
    pending
    val xs = List.range(1, 1000000)
    Sublist.sublist(List(3, 4, 5), xs) should be (Sublist.Sublist)
  }

  test("huge sublist not in huge list") {
    pending
    val l1 = List.range(10, 1000001)
    val l2 = List.range(1, 1000000)
    Sublist.sublist(l1, l2) should be (Sublist.Unequal)
  }

  test("superlist at start") {
    pending
    Sublist.sublist(List(1, 2, 3, 4, 5), List(1, 2, 3)) should be (Sublist.Superlist)
  }

  test("superlist in middle") {
    pending
    Sublist.sublist(List(5, 4, 3, 2, 1), List(4, 3, 2)) should be (Sublist.Superlist)
  }

  test("superlist at end") {
    pending
    Sublist.sublist(List(1, 2, 3, 4, 5), List(3, 4, 5)) should be (Sublist.Superlist)
  }

  test("partially matching superlist at start") {
    pending
    Sublist.sublist(List(1, 1, 1, 2), List(1, 1, 2)) should be (Sublist.Superlist)
  }

  test("superlist early in huge list") {
    pending
    val l1 = List.range(1, 1000000)
    Sublist.sublist(l1, List(3, 4, 5)) should be (Sublist.Superlist)
  }

  test("recurring values sublist") {
    pending
    Sublist.sublist(List(1, 2, 1, 2, 3), List(1, 2, 3, 1, 2, 1, 2, 3, 2, 1)) should be (Sublist.Sublist)
  }

  test("recurring values unequal") {
    pending
    Sublist.sublist(List(1, 2, 1, 2, 3), List(1, 2, 3, 1, 2, 3, 2, 3, 2, 1)) should be (Sublist.Unequal)
  }
}
