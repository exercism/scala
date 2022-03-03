object ZebraPuzzle {

  trait HousePart

  sealed trait Color extends HousePart
  case object Red extends Color
  case object Green extends Color
  case object Yellow extends Color
  case object Blue extends Color
  case object Ivory extends Color
  object Color { val values: List[Color] = List(Red, Green, Yellow, Blue, Ivory) }

  sealed trait Resident extends HousePart
  case object Englishman extends Resident
  case object Spaniard extends Resident
  case object Ukrainian extends Resident
  case object Norwegian extends Resident
  case object Japanese extends Resident
  object Resident {
    val values: List[Resident] =
      List(Englishman, Spaniard, Ukrainian, Norwegian, Japanese)
  }

  sealed trait Pet extends HousePart
  case object Dog extends Pet
  case object Snails extends Pet
  case object Fox extends Pet
  case object Horse extends Pet
  case object Zebra extends Pet
  object Pet { val values: List[Pet] = List(Dog, Snails, Fox, Horse, Zebra) }

  sealed trait Beverage extends HousePart
  case object Coffee extends Beverage
  case object Tea extends Beverage
  case object Milk extends Beverage
  case object OrangeJuice extends Beverage
  case object Water extends Beverage
  object Beverage {
    val values: List[Beverage] = List(Coffee, Tea, Milk, OrangeJuice, Water)
  }

  sealed trait Cigarette extends HousePart
  case object OldGold extends Cigarette
  case object Kools extends Cigarette
  case object Chesterfields extends Cigarette
  case object LuckyStrike extends Cigarette
  case object Parliaments extends Cigarette
  object Cigarette {
    val values: List[Cigarette] = List(OldGold, Kools, Chesterfields, LuckyStrike, Parliaments)
  }

  sealed trait Position extends HousePart { def fromEnum: Int }
  case object One extends Position { override val fromEnum = 0 }
  case object Two extends Position { override val fromEnum = 1 }
  case object Three extends Position { override val fromEnum = 2 }
  case object Four extends Position { override val fromEnum = 3 }
  case object Five extends Position { override val fromEnum = 4 }
  object Position { val values: List[Position] = List(One, Two, Three, Four, Five) }

  case class House(position: Position, color: Color, resident : Resident,
      beverage: Beverage, cigarette: Cigarette, pet: Pet)
  {
    def toTheRight(other: House): Boolean = 
      this.position.fromEnum == other.position.fromEnum + 1

    def nextTo(other: House): Boolean =
      math.abs(this.position.fromEnum - other.position.fromEnum) == 1
  }

  case class Solution(waterDrinker: Resident, zebraOwner: Resident)

  lazy val solve: Solution = {
    def residentWith(what: House => HousePart, value: HousePart): Resident =
      houseWith(what, value, fiveHouses).resident

    val waterDrinker = residentWith((_.beverage), Water)
    val zebraOwner = residentWith((_.pet), Zebra)

    Solution(waterDrinker = waterDrinker, zebraOwner = zebraOwner)
  }

  lazy val fiveHouses: List[House] = {
    def housesAtPosition(position: Position): List[House] =
      validHouses filter (_.position == position)

    val candidates = for {
      one   <- housesAtPosition(One)
      two   <- housesAtPosition(Two)   if uniqueHouses(List(one, two))
      three <- housesAtPosition(Three) if uniqueHouses(List(one, two, three))
      four  <- housesAtPosition(Four)  if uniqueHouses(List(one, two, three, four))
      five  <- housesAtPosition(Five)  if uniqueHouses(List(one, two, three, four, five))
      candidates = List(one, two, three, four, five) if validPositions(candidates)
    } yield candidates

    candidates.head
  }

  private lazy val validHouses: List[House] =
    for {
      position <- Position.values
      color <- Color.values
      resident <- Resident.values
      beverage <- Beverage.values
      cigarette <- Cigarette.values
      pet <- Pet.values
      house = House(position, color, resident, beverage, cigarette, pet) if validHouse(house)
    } yield house

  def validHouse(house: House): Boolean = {
    val House(position, color, resident, beverage, cigarette, pet) = house
    List(
      (color == Red, resident == Englishman),
      (resident == Spaniard, pet == Dog),
      (color == Green, beverage == Coffee),
      (resident == Ukrainian, beverage == Tea),
      (cigarette == OldGold, pet == Snails),
      (color == Yellow, cigarette == Kools),
      (position == Three, beverage == Milk),
      (position == One, resident == Norwegian),
      (beverage == OrangeJuice, cigarette == LuckyStrike),
      (resident == Japanese, cigarette == Parliaments)) forall (iff _).tupled
  }

  private def iff(p1: Boolean, p2: Boolean): Boolean =
    (p1, p2) match {
      case (true, true) => true
      case (false, false) => true
      case _ => false
    }

  private def uniqueHouses(houses: List[House]): Boolean = {
    def unique(what: House => HousePart): Boolean =
      (houses map what).distinct.length == houses.length

    unique (_.color) && unique (_.resident) && unique (_.beverage) &&
    unique (_.cigarette) && unique (_.pet)
  }

  private def houseWith(what: House => HousePart, value: HousePart, houses: List[House]): House =
    houses find (what(_) == value) getOrElse(throw new Exception(s"could not find house with $value"))

  private def validPositions(houses: List[House]): Boolean = {
    def _houseWith(what: House => HousePart, value: HousePart): House =
      houseWith(what, value, houses)

    (_houseWith(_.color,  Green) toTheRight _houseWith(_.color, Ivory)) &&
    (_houseWith(_.cigarette, Chesterfields) nextTo _houseWith(_.pet, Fox)) &&
    (_houseWith(_.cigarette, Kools) nextTo _houseWith(_.pet, Horse)) &&
    (_houseWith(_.resident, Norwegian) nextTo _houseWith(_.color, Blue))
  }
}

