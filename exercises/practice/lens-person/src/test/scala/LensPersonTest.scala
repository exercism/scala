import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import java.time.LocalDate
import LensPerson._

/** @version created manually **/
class LensPersonTest extends AnyFunSuite with Matchers {
  val testPerson =
    Person(
        _name = Name(
            _foreNames = "Jane Joanna",
            _surName = "Doe"),
        _born = Born(
            _bornAt = Address(
                _street = "Longway",
                _houseNumber = 1024,
                _place = "Springfield",
                _country = "United States"),
            _bornOn = toEpochDay(1984, 4, 12)),
        _address = Address(
            _street = "Shortlane",
            _houseNumber = 2,
            _place = "Fallmeadow",
            _country = "Canada"))

  def toEpochDay(year: Int, month: Int, dayOfMonth: Int) =
    LocalDate.of(year, month, dayOfMonth).toEpochDay

  test("bornStreet") {
    bornStreet(testPerson._born) should be ("Longway")
  }

  test("setCurrentStreet") {
    pending
    (setCurrentStreet("Middleroad")(testPerson))._address._street should be ("Middleroad")
  }

  test("setBirthMonth") {
    pending
    setBirthMonth(9)(testPerson)._born._bornOn should be (toEpochDay(1984, 9, 12))
  }

  test("renameStreets birth") {
    pending
    renameStreets(_.toUpperCase)(testPerson)._born._bornAt._street should be ("LONGWAY")
  }

  test("renameStreets current") {
    pending
    renameStreets(_.toUpperCase)(testPerson)._address._street should be ("SHORTLANE")
  }
}
