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
  
  object Die {
    def eval(d: Die): Int = d match {
      case One => 1
      case Two => 2
      case Three => 3
      case Four => 4
      case Five => 5
      case Six => 6
    }
  }

  private def diceWithCount(dice: List[Die]): List[(Die, Int)] =
    dice.groupBy(identity).mapValues(_.size).toList.sortBy(-_._2)

  private def valueScore(value: Die, dice: List[Die]): Int = {
    val count = dice.count(_ == value)
    count * Die.eval(value)
  }

  private def fullHouseScore(dice: List[Die]): Int = diceWithCount(dice) match {
    case List((_, 3), (_, 2)) => dice.sumBy(Die.eval)
    case _ => 0
  }

  private def fourOfAKindScore(dice: List[Die]): Int = diceWithCount(dice) match {
    case List((d, 4), _*) => Die.eval(d) * 4
    case List((d, 5)) => Die.eval(d) * 4
    case _ => 0
  }

  private def littleStraightScore(dice: List[Die]): Int =
    if (dice.sorted == List(One, Two, Three, Four, Five)) 30 else 0

  private def bigStraightScore(dice: List[Die]): Int =
    if (dice.sorted == List(Two, Three, Four, Five, Six)) 30 else 0

  private def choiceScore(dice: List[Die]): Int = dice.sumBy(Die.eval)

  private def yachtScore(dice: List[Die]): Int =
    if (dice.distinct.size == 1) 50 else 0

  def score(category: Category, dice: List[Die]): Int = category match {
    case Ones => valueScore(One, dice)
    case Twos => valueScore(Two, dice)
    case Threes => valueScore(Three, dice)
    case Fours => valueScore(Four, dice)
    case Fives => valueScore(Five, dice)
    case Sixes => valueScore(Six, dice)
    case FullHouse => fullHouseScore(dice)
    case FourOfAKind => fourOfAKindScore(dice)
    case LittleStraight => littleStraightScore(dice)
    case BigStraight => bigStraightScore(dice)
    case Choice => choiceScore(dice)
    case Yacht => yachtScore(dice)
  }
}