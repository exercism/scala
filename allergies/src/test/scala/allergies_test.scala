import org.scalatest.{Matchers, FlatSpec}

class AllergiesTest extends FlatSpec with Matchers {
  it should "handle no allergies means not allergic" in {
    Allergies().isAllergicTo(Allergen.Peanuts, 0) should equal (false)
    Allergies().isAllergicTo(Allergen.Cats, 0) should equal (false)
    Allergies().isAllergicTo(Allergen.Strawberries, 0) should equal (false)
  }

  it should "handle is allergic to eggs" in {
    Allergies().isAllergicTo(Allergen.Eggs, 1) should equal (true)
  }

  it should "handle is allergic to eggs in addition to other stuff" in {
    Allergies().isAllergicTo(Allergen.Eggs, 5) should equal (true)
  }

  it should "handle no allergies" in {
    Allergies().allergies(0) should equal (List())
  }

  it should "handle allergic to just eggs" in {
    Allergies().allergies(1) should equal (List(Allergen.Eggs))
  }

  it should "handle allergic to just peanuts" in {
    Allergies().allergies(2) should equal (List(Allergen.Peanuts))
  }

  it should "handle allergic to just strawberries" in {
    Allergies().allergies(8) should equal (List(Allergen.Strawberries))
  }

  it should "handle allergic to eggs and peanuts" in {
    Allergies().allergies(3) should equal (List(Allergen.Eggs, Allergen.Peanuts))
  }

  it should "handle allergic to more than eggs but not peanuts" in {
    Allergies().allergies(5) should equal (List(Allergen.Eggs, Allergen.Shellfish))
  }

  it should "handle allergic to lots of stuff" in {
    Allergies().allergies(248) should equal (List(Allergen.Strawberries, Allergen.Tomatoes,
      Allergen.Chocolate, Allergen.Pollen, Allergen.Cats))
  }

  it should "handle allergic to everything" in {
    Allergies().allergies(255) should equal (List(Allergen.Eggs, Allergen.Peanuts,
      Allergen.Shellfish, Allergen.Strawberries, Allergen.Tomatoes,
      Allergen.Chocolate, Allergen.Pollen, Allergen.Cats))
  }

  it should "ignore non allergen score parts" in {
    Allergies().allergies(509) should equal (List(Allergen.Eggs,
      Allergen.Shellfish, Allergen.Strawberries, Allergen.Tomatoes,
      Allergen.Chocolate, Allergen.Pollen, Allergen.Cats))
  }
}
