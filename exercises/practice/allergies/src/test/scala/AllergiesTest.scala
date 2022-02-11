import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class AllergiesTest extends AnyFunSuite with Matchers {

  test("Allergen.Peanuts - no allergies means not allergic") {
    Allergies.allergicTo(Allergen.Peanuts, 0) should be(false)
  }

  test("Allergen.Cats - no allergies means not allergic") {
    pending
    Allergies.allergicTo(Allergen.Cats, 0) should be(false)
  }

  test("Allergen.Strawberries - no allergies means not allergic") {
    pending
    Allergies.allergicTo(Allergen.Strawberries, 0) should be(false)
  }

  test("Allergen.Eggs - is allergic to eggs") {
    pending
    Allergies.allergicTo(Allergen.Eggs, 1) should be(true)
  }

  test("Allergen.Eggs - allergic to eggs in addition to other stuff") {
    pending
    Allergies.allergicTo(Allergen.Eggs, 5) should be(true)
  }

  test("Allergen.Shellfish - allergic to eggs in addition to other stuff") {
    pending
    Allergies.allergicTo(Allergen.Shellfish, 5) should be(true)
  }

  test("Allergen.Strawberries - allergic to eggs in addition to other stuff") {
    pending
    Allergies.allergicTo(Allergen.Strawberries, 5) should be(false)
  }

  test("Allergen.Eggs - allergic to strawberries but not peanuts") {
    pending
    Allergies.allergicTo(Allergen.Eggs, 9) should be(true)
  }

  test("Allergen.Peanuts - allergic to strawberries but not peanuts") {
    pending
    Allergies.allergicTo(Allergen.Peanuts, 9) should be(false)
  }

  test("Allergen.Shellfish - allergic to strawberries but not peanuts") {
    pending
    Allergies.allergicTo(Allergen.Shellfish, 9) should be(false)
  }

  test("Allergen.Strawberries - allergic to strawberries but not peanuts") {
    pending
    Allergies.allergicTo(Allergen.Strawberries, 9) should be(true)
  }

  test("no allergies at all") {
    pending
    Allergies.list(0) should be(List())
  }

  test("allergic to just eggs") {
    pending
    Allergies.list(1) should be(List(Allergen.Eggs))
  }

  test("allergic to just peanuts") {
    pending
    Allergies.list(2) should be(List(Allergen.Peanuts))
  }

  test("allergic to just strawberries") {
    pending
    Allergies.list(8) should be(List(Allergen.Strawberries))
  }

  test("allergic to eggs and peanuts") {
    pending
    Allergies.list(3) should be(List(Allergen.Eggs, Allergen.Peanuts))
  }

  test("allergic to more than eggs but not peanuts") {
    pending
    Allergies.list(5) should be(List(Allergen.Eggs, Allergen.Shellfish))
  }

  test("allergic to lots of stuff") {
    pending
    Allergies.list(248) should be(
      List(Allergen.Strawberries,
           Allergen.Tomatoes,
           Allergen.Chocolate,
           Allergen.Pollen,
           Allergen.Cats))
  }

  test("allergic to everything") {
    pending
    Allergies.list(255) should be(
      List(Allergen.Eggs,
           Allergen.Peanuts,
           Allergen.Shellfish,
           Allergen.Strawberries,
           Allergen.Tomatoes,
           Allergen.Chocolate,
           Allergen.Pollen,
           Allergen.Cats))
  }

  test("ignore non allergen score parts") {
    pending
    Allergies.list(509) should be(
      List(Allergen.Eggs,
           Allergen.Shellfish,
           Allergen.Strawberries,
           Allergen.Tomatoes,
           Allergen.Chocolate,
           Allergen.Pollen,
           Allergen.Cats))
  }
}
