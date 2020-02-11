import Allergen.Allergen

object Allergies {
  private lazy val allergenList = Allergen.values

  def allergicTo(allergen: Allergen, score: Int): Boolean =
    (allergen.id & score) != 0

  def list(score: Int): List[Allergen] =
    allergenList.filter(a => allergicTo(a, score)).toList
}

object Allergen extends Enumeration {
  type Allergen = Value

  val Eggs = Value(1, "Eggs")
  val Peanuts = Value(2, "Peanuts")
  val Shellfish = Value(4, "Shellfish")
  val Strawberries = Value(8, "Strawberries")
  val Tomatoes = Value(16, "Tomatoes")
  val Chocolate = Value(32, "Chocolate")
  val Pollen = Value(64, "Pollen")
  val Cats = Value(128, "Cats")
}