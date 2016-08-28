import org.scalatest.{FunSuite, Matchers}

class SublistSpecs extends FunSuite with Matchers {
  test("empty lists") {
    Sublist.sublist(List(), List()) should be (Sublist.Equal)
  }

  test("empty is a sublist of anything") {
    Sublist.sublist(List(), List('a', 's', 'd', 'f')) should be (Sublist.Sublist)
  }

  test("anything is a superlist of empty") {
    Sublist.sublist(List('a', 's', 'd', 'f'), List()) should be (Sublist.Superlist)
  }

  test("List(1) is not List(2)") {
    Sublist.sublist(List(1), List(2)) should be (Sublist.Unequal)
  }

  test("compare larger equal lists") {
    val xs = List.fill(1000)("x")
    Sublist.sublist(xs, xs) should be (Sublist.Equal)
  }

  test("sublist at start") {
    Sublist.sublist(List(1, 2, 3), List(1, 2, 3, 4, 5)) should be (Sublist.Sublist)
  }

  test("sublist in middle") {
    Sublist.sublist(List(4, 3, 2), List(5, 4, 3, 2, 1)) should be (Sublist.Sublist)
  }

  test("sublist at end") {
    Sublist.sublist(List(3, 4, 5), List(1, 2, 3, 4, 5)) should be (Sublist.Sublist)
  }

  test("partially matching sublist at start") {
    Sublist.sublist(List(1, 1, 2), List(1, 1, 1, 2)) should be (Sublist.Sublist)
  }

  test("sublist early in huge list") {
    val xs = List.range(1, 1000000)
    Sublist.sublist(List(3, 4, 5), xs) should be (Sublist.Sublist)
  }

  test("huge sublist not in huge list") {
    val l1 = List.range(10, 1000001)
    val l2 = List.range(1, 1000000)
    Sublist.sublist(l1, l2) should be (Sublist.Unequal)
  }

  test("superlist at start") {
    Sublist.sublist(List(1, 2, 3, 4, 5), List(1, 2, 3)) should be (Sublist.Superlist)
  }

  test("superlist in middle") {
    Sublist.sublist(List(5, 4, 3, 2, 1), List(4, 3, 2)) should be (Sublist.Superlist)
  }

  test("superlist at end") {
    Sublist.sublist(List(1, 2, 3, 4, 5), List(3, 4, 5)) should be (Sublist.Superlist)
  }

  test("partially matching superlist at start") {
    Sublist.sublist(List(1, 1, 1, 2), List(1, 1, 2)) should be (Sublist.Superlist)
  }

  test("superlist early in huge list") {
    val l1 = List.range(1, 1000000)
    Sublist.sublist(l1, List(3, 4, 5)) should be (Sublist.Superlist)
  }

  test("recurring values sublist") {
    Sublist.sublist(List(1, 2, 1, 2, 3), List(1, 2, 3, 1, 2, 1, 2, 3, 2, 1)) should be (Sublist.Sublist)
  }

  test("recurring values unequal") {
    Sublist.sublist(List(1, 2, 1, 2, 3), List(1, 2, 3, 1, 2, 3, 2, 3, 2, 1)) should be (Sublist.Unequal)
  }
}
