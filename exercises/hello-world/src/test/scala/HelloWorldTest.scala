import org.scalatest.{Matchers, FunSuite}

class HelloWorldTest extends FunSuite with Matchers {
  test("Without name") {
    HelloWorld.hello() should be ("Hello, World!")
  }

  test("with name") {
    pending
    HelloWorld.hello("Jane") should be ("Hello, Jane!")
  }

  test("with umlaut name") {
    pending
    HelloWorld.hello("Jürgen") should be ("Hello, Jürgen!")
  }
}
