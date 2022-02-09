import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.3.0 */
class ComplexNumberTest extends AnyFunSuite with Matchers {

  private val equalityEpsilon = 1e-15

  private def assertEquals(c1: ComplexNumber, c2: ComplexNumber) {
    c1.real should be (c2.real +- equalityEpsilon)
    c1.imaginary should be (c2.imaginary +- equalityEpsilon)
  }

  test("Real part of a purely real number") {
    ComplexNumber(real = 1).real should be (1.0 +- equalityEpsilon)
  }

  test("Real part of a purely imaginary number") {
    pending
    ComplexNumber(real = 0).real should be (0.0 +- equalityEpsilon)
  }

  test("Real part of a number with real and imaginary part") {
    pending
    ComplexNumber(real = 1).real should be (1.0 +- equalityEpsilon)
  }

  test("Imaginary part of a purely real number") {
    pending
    ComplexNumber(imaginary = 0).imaginary should be (0.0 +- equalityEpsilon)
  }

  test("Imaginary part of a purely imaginary number") {
    pending
    ComplexNumber(imaginary = 1).imaginary should be (1.0 +- equalityEpsilon)
  }

  test("Imaginary part of a number with real and imaginary part") {
    pending
    ComplexNumber(imaginary = 2).imaginary should be (2.0 +- equalityEpsilon)
  }

  test("Imaginary unit") {
    pending
    val result = ComplexNumber(0, 1) * ComplexNumber(0, 1)
    assertEquals(result, ComplexNumber(-1, 0))
  }

  test("Add purely real numbers") {
    pending
    val result = ComplexNumber(1, 0) + ComplexNumber(2, 0)
    assertEquals(result, ComplexNumber(3, 0))
  }

  test("Add purely imaginary numbers") {
    pending
    val result = ComplexNumber(0, 1) + ComplexNumber(0, 2)
    assertEquals(result, ComplexNumber(0, 3))
  }

  test("Add numbers with real and imaginary part") {
    pending
    val result = ComplexNumber(1, 2) + ComplexNumber(3, 4)
    assertEquals(result, ComplexNumber(4, 6))
  }

  test("Subtract purely real numbers") {
    pending
    val result = ComplexNumber(1, 0) - ComplexNumber(2, 0)
    assertEquals(result, ComplexNumber(-1, 0))
  }

  test("Subtract purely imaginary numbers") {
    pending
    val result = ComplexNumber(0, 1) - ComplexNumber(0, 2)
    assertEquals(result, ComplexNumber(0, -1))
  }

  test("Subtract numbers with real and imaginary part") {
    pending
    val result = ComplexNumber(1, 2) - ComplexNumber(3, 4)
    assertEquals(result, ComplexNumber(-2, -2))
  }

  test("Multiply purely real numbers") {
    pending
    val result = ComplexNumber(1, 0) * ComplexNumber(2, 0)
    assertEquals(result, ComplexNumber(2, 0))
  }

  test("Multiply purely imaginary numbers") {
    pending
    val result = ComplexNumber(0, 1) * ComplexNumber(0, 2)
    assertEquals(result, ComplexNumber(-2, 0))
  }

  test("Multiply numbers with real and imaginary part") {
    pending
    val result = ComplexNumber(1, 2) * ComplexNumber(3, 4)
    assertEquals(result, ComplexNumber(-5, 10))
  }

  test("Divide purely real numbers") {
    pending
    val result = ComplexNumber(1, 0) / ComplexNumber(2, 0)
    assertEquals(result, ComplexNumber(0.5, 0))
  }

  test("Divide purely imaginary numbers") {
    pending
    val result = ComplexNumber(0, 1) / ComplexNumber(0, 2)
    assertEquals(result, ComplexNumber(0.5, 0))
  }

  test("Divide numbers with real and imaginary part") {
    pending
    val result = ComplexNumber(1, 2) / ComplexNumber(3, 4)
    assertEquals(result, ComplexNumber(0.44, 0.08))
  }

  test("Absolute value of a positive purely real number") {
    pending
    ComplexNumber(5, 0).abs should be (5.0 +- equalityEpsilon)
  }

  test("Absolute value of a negative purely real number") {
    pending
    ComplexNumber(-5, 0).abs should be (5.0 +- equalityEpsilon)
  }

  test("Absolute value of a purely imaginary number with positive imaginary part") {
    pending
    ComplexNumber(0, 5).abs should be (5.0 +- equalityEpsilon)
  }

  test("Absolute value of a purely imaginary number with negative imaginary part") {
    pending
    ComplexNumber(0, -5).abs should be (5.0 +- equalityEpsilon)
  }

  test("Absolute value of a number with real and imaginary part") {
    pending
    ComplexNumber(3, 4).abs should be (5.0 +- equalityEpsilon)
  }

  test("Conjugate a purely real number") {
    pending
    val result = ComplexNumber(5, 0).conjugate
    assertEquals(result, ComplexNumber(5, 0))
  }

  test("Conjugate a purely imaginary number") {
    pending
    val result = ComplexNumber(0, 5).conjugate
    assertEquals(result, ComplexNumber(0, -5))
  }

  test("Conjugate a number with real and imaginary part") {
    pending
    val result = ComplexNumber(1, 1).conjugate
    assertEquals(result, ComplexNumber(1, -1))
  }

  test("Euler's identity/formula") {
    pending
    val result = ComplexNumber.exp(ComplexNumber(0, math.Pi))
    assertEquals(result, ComplexNumber(-1, 0))
  }

  test("Exponential of 0") {
    pending
    val result = ComplexNumber.exp(ComplexNumber(0, 0))
    assertEquals(result, ComplexNumber(1, 0))
  }

  test("Exponential of a purely real number") {
    pending
    val result = ComplexNumber.exp(ComplexNumber(1, 0))
    assertEquals(result, ComplexNumber(math.E, 0))
  }

  test("Exponential of a number with real and imaginary part") {
    pending
    val result = ComplexNumber.exp(ComplexNumber(math.log(2), math.Pi))
    assertEquals(result, ComplexNumber(-2, 0))
  }
}
