object Yacht {

  sealed trait Category
  case object Ones extends Category
  case object Twos extends Category
  case object Threes extends Category
  case object Fours extends Category
  case object Fives extends Category
  case object Sixes extends Category
  case object FullHouse extends Category
  case object FourOfAKind extends Category
  case object LittleStraight extends Category
  case object BigStraight extends Category
  case object Choice extends Category
  case object Yacht extends Category

  sealed trait Die
  case object One extends Die
  case object Two extends Die
  case object Three extends Die
  case object Four extends Die
  case object Five extends Die
  case object Six extends Die

  def score(category: Category, dice: List[Die]): Int = ???
}