import java.time.LocalDate

object LensPerson {
  case class Person(_name: Name, _born: Born, _address: Address)

  case class Name(_foreNames: String /*Space separated*/ , _surName: String)

  // Value of java.time.LocalDate.toEpochDay
  type EpochDay = Long

  case class Born(_bornAt: Address, _bornOn: EpochDay)

  case class Address(_street: String, _houseNumber: Int,
    _place: String /*Village / city*/ , _country: String)

  // Valid values of Gregorian are those for which 'java.time.LocalDate.of'
  // returns a valid LocalDate.
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)

  // Implement these.

  val bornStreet: Born => String = ???

  val setCurrentStreet: String => Person => Person = ???

  val setBirthMonth: Int => Person => Person = ???

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person = ???
}
