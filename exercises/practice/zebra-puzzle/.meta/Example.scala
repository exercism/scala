object ZebraPuzzle:

  sealed trait HousePart

  enum Color extends HousePart:
    case Red, Green, Yellow, Blue, Ivory

  enum Resident extends HousePart:
    case Englishman, Spaniard, Ukrainian, Norwegian, Japanese

  enum Pet extends HousePart:
    case Dog, Snails, Fox, Horse, Zebra

  enum Beverage extends HousePart:
    case Coffee, Tea, Milk, OrangeJuice, Water

  enum Cigarette extends HousePart:
    case OldGold, Kools, Chesterfields, LuckyStrike, Parliaments

  enum Position extends HousePart:
    case One, Two, Three, Four, Five

  export Resident.*

  import Color.*, Pet.*, Beverage.*, Cigarette.*, Position.*

  case class House(position: Position, color: Color, resident: Resident, beverage: Beverage, cigarette: Cigarette, pet: Pet):
    infix def toTheRightOf(other: House): Boolean = position.ordinal == other.position.ordinal + 1

    infix def nextTo(other: House): Boolean = math.abs(position.ordinal - other.position.ordinal) == 1

  case class Solution(waterDrinker: Resident, zebraOwner: Resident)

  lazy val solve: Solution =
    Solution(
      waterDrinker = houseWith(_.beverage, Water, fiveHouses).resident,
      zebraOwner = houseWith(_.pet, Zebra, fiveHouses).resident,
    )

  private lazy val fiveHouses: List[House] =
    def housesAt(position: Position): List[House] = validHouses.filter(_.position == position)

    val candidates =
      for
        one   <- housesAt(One)
        two   <- housesAt(Two) if uniqueHouses(List(one, two))
        three <- housesAt(Three) if uniqueHouses(List(one, two, three))
        four  <- housesAt(Four) if uniqueHouses(List(one, two, three, four))
        five  <- housesAt(Five) if uniqueHouses(List(one, two, three, four, five))
        houses = List(one, two, three, four, five) if validPositions(houses)
      yield houses

    candidates.head

  private lazy val validHouses: List[House] =
    for
      position  <- Position.values.toList
      color     <- Color.values.toList
      resident  <- Resident.values.toList
      beverage  <- Beverage.values.toList
      cigarette <- Cigarette.values.toList
      pet       <- Pet.values.toList
      house      = House(position, color, resident, beverage, cigarette, pet) if validHouse(house)
    yield house

  // The rules about a single house: each pair holds both or neither.
  private def validHouse(house: House): Boolean =
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
      (resident == Japanese, cigarette == Parliaments),
    ).forall(_ == _)

  private def uniqueHouses(houses: List[House]): Boolean =
    def unique(part: House => HousePart): Boolean = houses.map(part).distinct.length == houses.length

    unique(_.color) && unique(_.resident) && unique(_.beverage) && unique(_.cigarette) && unique(_.pet)

  private def houseWith(part: House => HousePart, value: HousePart, houses: List[House]): House =
    houses.find(part(_) == value).getOrElse(throw IllegalStateException(s"could not find house with $value"))

  // The rules about neighbouring houses.
  private def validPositions(houses: List[House]): Boolean =
    def withPart(part: House => HousePart, value: HousePart): House = houseWith(part, value, houses)

    (withPart(_.color, Green) toTheRightOf withPart(_.color, Ivory)) &&
    (withPart(_.cigarette, Chesterfields) nextTo withPart(_.pet, Fox)) &&
    (withPart(_.cigarette, Kools) nextTo withPart(_.pet, Horse)) &&
    (withPart(_.resident, Norwegian) nextTo withPart(_.color, Blue))
