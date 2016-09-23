import java.time.LocalDate

import monocle.PIso
import monocle.macros.GenLens

object LensPerson {
  case class Person(_name: Name, _born: Born, _address: Address)
  val personLens = GenLens[Person]
  val name = personLens(_._name)
  val born = personLens(_._born)
  val address = personLens(_._address)

  case class Name(_foreNames: String /*Space separated*/ , _surName: String)
  val nameLens = GenLens[Name]
  val foreNames = nameLens(_._foreNames)
  val surName = nameLens(_._surName)

  // Value of java.time.LocalDate.toEpochDay
  type EpochDay = Long

  case class Born(_bornAt: Address, _bornOn: EpochDay)
  val bornLens = GenLens[Born]
  val bornAt = bornLens(_._bornAt)
  val bornOn = bornLens(_._bornOn)

  case class Address(_street: String, _houseNumber: Int,
    _place: String /*Village / city*/ , _country: String)
  val street = GenLens[Address](_._street)

  // Valid values of Gregorian are those for which 'java.time.LocalDate.of'
  // returns a valid LocalDate.
  case class Gregorian(_year: Int, _month: Int, _dayOfMonth: Int)
  val gregorianLens = GenLens[Gregorian]
  val dayOfMonth = gregorianLens(_._dayOfMonth)
  val month = gregorianLens(_._month)
  val year = gregorianLens(_._year)

  // Implement these.

  val bornStreet: Born => String =
    bornAt ^|-> street get

  val setCurrentStreet: String => Person => Person =
    address ^|-> street set

  val isoEpocheDayGregorian: PIso[EpochDay, EpochDay, Gregorian, Gregorian] = {
    def epochDayToGregorian(ed: EpochDay): Gregorian = {
      val ld = LocalDate.ofEpochDay(ed)
      Gregorian(ld.getYear, ld.getMonth.getValue, ld.getDayOfMonth)
    }
    def gregorianToEpochDay(g: Gregorian): EpochDay =
      LocalDate.of(g._year, g._month, g._dayOfMonth).toEpochDay

    PIso(epochDayToGregorian _)(gregorianToEpochDay _)
  }

  val setBirthMonth: Int => Person => Person =
    born ^|-> bornOn ^<-> isoEpocheDayGregorian ^|-> month set

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person =
    f => ((born ^|-> bornAt ^|-> street modify f) .compose (address ^|-> street modify f))
}