import Allergen.Allergen

object Allergies {
  def isAllergicTo(allergen: Allergen, score: Int): Boolean = ???

  def allergies(score: Int): List[Allergen] = ???
}

object Allergen extends Enumeration {
  type Allergen = ???
}