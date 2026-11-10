import java.time.LocalDate

import monocle.{Iso, Lens}
import monocle.macros.GenLens

object LensPerson:

  case class Person(_name: Name, _born: Born, _address: Address)

  case class Name(_foreNames: String /*Space separated*/, _surName: String)

  // Value of java.time.LocalDate.toEpochDay
  type EpochDay = Long

  case class Born(_bornAt: Address, _bornOn: EpochDay)

  case class Address(_street: String, _houseNumber: Int, _place: String /*Village / city*/, _country: String)

  // Valid values of Gregorian are those for which 'java.time.LocalDate.of'
  // returns a valid LocalDate.
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)

  private val born: Lens[Person, Born]            = GenLens[Person](_._born)
  private val address: Lens[Person, Address]      = GenLens[Person](_._address)
  private val bornAt: Lens[Born, Address]         = GenLens[Born](_._bornAt)
  private val bornOn: Lens[Born, EpochDay]        = GenLens[Born](_._bornOn)
  private val street: Lens[Address, String]       = GenLens[Address](_._street)
  private val month: Lens[Gregorian, Int]         = GenLens[Gregorian](_._month)
  private val gregorian: Iso[EpochDay, Gregorian] = Iso(toGregorian)(toEpochDay)

  private def toGregorian(epochDay: EpochDay): Gregorian =
    val date = LocalDate.ofEpochDay(epochDay)
    Gregorian(date.getYear, date.getMonthValue, date.getDayOfMonth)

  private def toEpochDay(gregorian: Gregorian): EpochDay =
    LocalDate.of(gregorian._year, gregorian._month, gregorian._dayOfMonth).toEpochDay

  val bornStreet: Born => String = bornAt.andThen(street).get

  val setCurrentStreet: String => Person => Person = address.andThen(street).replace

  val setBirthMonth: Int => Person => Person = born.andThen(bornOn).andThen(gregorian).andThen(month).replace

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person =
    f => born.andThen(bornAt).andThen(street).modify(f).andThen(address.andThen(street).modify(f))
