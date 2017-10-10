import org.scalatest.{Matchers, FunSuite}

class CollatzConjectureTest extends FunSuite with Matchers {

  test("zero steps for one") {
    CollatzConjecture.collatz(1) should be (Some(0))
  }

  test("divide if even") {
    pending
    CollatzConjecture.collatz(16) should be (Some(4))
  }

  test("even and odd steps") {
    pending
    CollatzConjecture.collatz(12) should be (Some(9))
  }

  test("Large number of even and odd steps") {
    pending
    CollatzConjecture.collatz(1000000) should be (Some(152))
  }

  test("zero is an error") {
    pending
    CollatzConjecture.collatz(0) should be (None)
  }

  test("negative value is an error") {
    pending
    CollatzConjecture.collatz(-15) should be (None)
  }
}
