import org.scalatest.{Matchers, FunSuite}

class CollatzConjectureTest extends FunSuite with Matchers {

  test("zero steps for one") {
    pending
    CollatzConjecture.collatz(1) should be (0)
  }

  test("divide if even") {
    pending
    CollatzConjecture.collatz(16) should be (4)
  }

  test("even and odd steps") {
    pending
    CollatzConjecture.collatz(12) should be (9)
  }

  test("Large number of even and odd steps") {
    pending
    CollatzConjecture.collatz(1000000) should be (152)
  }

  test("Large number of even and odd steps") {
    pending
    CollatzConjecture.collatz(1000000) should be (152)
  }
