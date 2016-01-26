import org.scalatest.{Matchers, FunSuite}

class SecretHandshakeTest extends FunSuite with Matchers {
  test("1 to wink") {
    SecretHandshake.handshake(1) should be (List("wink"))
    SecretHandshake.handshake("1") should be (List("wink"))
  }

  test("10 to double blink") {
    SecretHandshake.handshake(2) should be (List("double blink"))
    SecretHandshake.handshake("10") should be (List("double blink"))
  }

  test("100 to close your eyes") {
    SecretHandshake.handshake(4) should be (List("close your eyes"))
    SecretHandshake.handshake("100") should be (List("close your eyes"))
  }

  test("1000 to jump") {
    SecretHandshake.handshake(8) should be (List("jump"))
    SecretHandshake.handshake("1000") should be (List("jump"))
  }

  test("11 to wink and double blink") {
    SecretHandshake.handshake(3) should be (List("wink", "double blink"))
    SecretHandshake.handshake("11") should be (List("wink", "double blink"))
  }

  test("10011 to double blink and wink") {
    SecretHandshake.handshake(19) should be (List("double blink", "wink"))
    SecretHandshake.handshake("10011") should be (List("double blink", "wink"))
  }

  test("11111 to jump, close your eyes, double blink, and wink") {
    SecretHandshake.handshake(31) should be (List("jump", "close your eyes", "double blink", "wink"))
    SecretHandshake.handshake("11111") should be (List("jump", "close your eyes", "double blink", "wink"))
  }

  test("zero") {
    SecretHandshake.handshake(0) should be (List())
    SecretHandshake.handshake("0") should be (List())
  }

  test("gibberish") {
    SecretHandshake.handshake("piggies") should be (List())
  }

  test("partial gibberish") {
    SecretHandshake.handshake("1piggies") should be (List())
  }
}
