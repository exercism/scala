import scala.language.postfixOps
import java.time.LocalDate
import LensPerson._

trait ToBeImplemented {

  // Implement these.

  val bornStreet: Born => String

  val setCurrentStreet: String => Person => Person

  val setBirthMonth: Int => Person => Person

  // Transform both birth and current street names.
  val renameStreets: (String => String) => Person => Person
}

object LensPerson extends ToBeImplemented with MonocleSolution /* with ScalazSolution */ {

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
}

trait MonocleSolution extends ToBeImplemented {
  import monocle.PIso
  import monocle.macros.GenLens

  // Person Lenses
  val personLens = GenLens[Person]
  val name = personLens(_._name)
  val born = personLens(_._born)
  val address = personLens(_._address)

  // Name Lenses
  val nameLens = GenLens[Name]
  val foreNames = nameLens(_._foreNames)
  val surName = nameLens(_._surName)

  // Born Lenses
  val bornLens = GenLens[Born]
  val bornAt = bornLens(_._bornAt)
  val bornOn = bornLens(_._bornOn)

  // Address Lenses
  val street = GenLens[Address](_._street)

  // Gregorian Lenses
  val gregorianLens = GenLens[Gregorian]
  val dayOfMonth = gregorianLens(_._dayOfMonth)
  val month = gregorianLens(_._month)
  val year = gregorianLens(_._year)

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

  val renameStreets: (String => String) => Person => Person =
    f => ((born ^|-> bornAt ^|-> street modify f) .compose (address ^|-> street modify f))
}

trait ScalazSolution extends ToBeImplemented {
  import scalaz.Lens

  // Person Lenses
  val name = Lens.lensu[Person, Name](
      (person, name) => person.copy(_name = name), (_._name))
  val born = Lens.lensu[Person, Born](
      (person, born) => person.copy(_born = born), (_._born))
  val address = Lens.lensu[Person, Address](
      (person, address) => person.copy(_address = address), (_._address))

  // Name Lenses
  val foreNames = Lens.lensu[Name, String](
      (name, foreNames) => name.copy(_foreNames = foreNames), (_._foreNames))
  val surName = Lens.lensu[Name, String](
      (name, surName) => name.copy(_surName = surName), (_._surName))

  // Born Lenses
  val bornAt = Lens.lensu[Born, Address](
      (born, address) => born.copy(_bornAt = address), (_._bornAt))
  val bornOn = Lens.lensu[Born, EpochDay](
      (born, epochDay) => born.copy(_bornOn = epochDay), (_._bornOn))

  // Address Lenses
  val street = Lens.lensu[Address, String](
      (address, street) => address.copy(_street = street), (_._street))

  // Gregorian Lenses
  val dayOfMonth = Lens.lensu[Gregorian, Int](
      (gregorian, dayOfMonth) => gregorian.copy(_dayOfMonth = dayOfMonth), (_._dayOfMonth))
  val month = Lens.lensu[Gregorian, Int](
      (gregorian, month) => gregorian.copy(_month = month), (_._month))
  val year = Lens.lensu[Gregorian, Int](
      (gregorian, year) => gregorian.copy(_year = year), (_._year))


  val bornStreet: Born => String =
    bornAt >=> street get

  val setCurrentStreet: String => Person => Person =
    s => address >=> street set(_, s)

  def epochDayToGregorian(ed: EpochDay): Gregorian = {
    val ld = LocalDate.ofEpochDay(ed)
    Gregorian(ld.getYear, ld.getMonth.getValue, ld.getDayOfMonth)
  }
  def gregorianToEpochDay(g: Gregorian): EpochDay =
    LocalDate.of(g._year, g._month, g._dayOfMonth).toEpochDay

  val setBirthMonth: Int => Person => Person =
    m => born >=> bornOn =>= setMonth(m)

  val setMonth: Int => EpochDay => EpochDay =
    newMonth => oldEpochDay => {
      val Gregorian(year, _, day) = epochDayToGregorian(oldEpochDay)
      val newGregorian = Gregorian(year, newMonth, day)
      gregorianToEpochDay(newGregorian)
    }

  val renameStreets: (String => String) => Person => Person =
    f => ((born >=> bornAt >=> street =>= f) .compose (address >=> street =>= f))
}
