import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class HelloWorldTest extends AnyFunSuite with Matchers:

  test("Say Hi!"):
    HelloWorld.hello() should be("Hello, World!")
