import org.scalatest.{Matchers, FlatSpec}

class LuhnTest extends FlatSpec with Matchers {
  it should "create checkDigit" in {
    Luhn(34567).checkDigit should equal(7)
    Luhn(91370).checkDigit should equal(0)
    Luhn(0).checkDigit should equal(0)
  }

  it should "create addends" in {
    Luhn(12121).addends should equal(List(1, 4, 1, 4, 1))
    Luhn(8631).addends should equal(List(7, 6, 6, 1))
  }

  it should "create checksum" in {
    //  NOTE: this differs from the ruby and js, the checksum really should
    //        be mod 10 like we are testing here.
    Luhn(4913).checksum should equal(2)
    Luhn(201773).checksum should equal(1)
  }

  it should "check validity" in {
    Luhn(738).isValid should be (false)
    Luhn(8739567).isValid should be (true)
  }

  it should "create luhn values" in {
    Luhn(123).create should be (1230)
    Luhn(873956).create should be (8739567)
    Luhn(837263756).create should be (8372637564L)
  }
}
