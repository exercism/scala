enum Allergen:
  case Eggs, Peanuts, Shellfish, Strawberries, Tomatoes, Chocolate, Pollen, Cats

  def score: Int = 1 << ordinal

object Allergies:
  def allergicTo(allergen: Allergen, score: Int): Boolean =
    (score & allergen.score) != 0

  def list(score: Int): List[Allergen] =
    Allergen.values.filter(allergicTo(_, score)).toList
