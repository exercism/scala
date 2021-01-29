case class ComplexNumber(real: Double = 0, imaginary: Double = 0) {
  def +(other: ComplexNumber): ComplexNumber =
    ComplexNumber(real + other.real, imaginary + other.imaginary)

  def -(other: ComplexNumber): ComplexNumber =
    ComplexNumber(real - other.real, imaginary - other.imaginary)

  def *(other: ComplexNumber): ComplexNumber =
    ComplexNumber(real * other.real - imaginary * other.imaginary,
      real * other.imaginary + imaginary * other.real)

  def *(factor: Double): ComplexNumber =
    ComplexNumber(factor * real, factor * imaginary)

  def /(other: ComplexNumber): ComplexNumber =
    ComplexNumber((real * other.real + imaginary * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary),
      (imaginary * other.real - real * other.imaginary) / (other.real * other.real + other.imaginary * other.imaginary))

  lazy val abs: Double =
    math.sqrt(real * real + imaginary * imaginary)

  lazy val conjugate: ComplexNumber =  ComplexNumber(real, -1 * imaginary)
}

object ComplexNumber {
  def exp(exponent: ComplexNumber): ComplexNumber =
    ComplexNumber(Math.cos(exponent.imaginary), Math.sin(exponent.imaginary)) * Math.exp(exponent.real)
}
