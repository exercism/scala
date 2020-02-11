import org.scalatest.{Matchers, FunSuite}

/** @version 1.2.0 */
class SecretHandshakeTest extends FunSuite with Matchers {

  test("wink for 1") {
    SecretHandshake.commands(1) should be (List("wink"))
  }

  test("double blink for 10") {
    pending
    SecretHandshake.commands(2) should be (List("double blink"))
  }

  test("close your eyes for 100") {
    pending
    SecretHandshake.commands(4) should be (List("close your eyes"))
  }

  test("jump for 1000") {
    pending
    SecretHandshake.commands(8) should be (List("jump"))
  }

  test("combine two actions") {
    pending
    SecretHandshake.commands(3) should be (List("wink", "double blink"))
  }

  test("reverse two actions") {
    pending
    SecretHandshake.commands(19) should be (List("double blink", "wink"))
  }

  test("reversing one action gives the same action") {
    pending
    SecretHandshake.commands(24) should be (List("jump"))
  }

  test("reversing no actions still gives no actions") {
    pending
    SecretHandshake.commands(16) should be (List())
  }

  test("all possible actions") {
    pending
    SecretHandshake.commands(15) should be (List("wink", "double blink", "close your eyes", "jump"))
  }

  test("reverse all possible actions") {
    pending
    SecretHandshake.commands(31) should be (List("jump", "close your eyes", "double blink", "wink"))
  }

  test("do nothing for zero") {
    pending
    SecretHandshake.commands(0) should be (List())
  }
}
