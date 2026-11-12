import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class AllergiesTest extends AnyFunSuite with Matchers:

  test("testing for eggs allergy - not allergic to anything"):
    Allergies.allergicTo(Allergen.Eggs, 0) should be(false)

  test("testing for eggs allergy - allergic only to eggs"):
    pending
    Allergies.allergicTo(Allergen.Eggs, 1) should be(true)

  test("testing for eggs allergy - allergic to eggs and something else"):
    pending
    Allergies.allergicTo(Allergen.Eggs, 3) should be(true)

  test("testing for eggs allergy - allergic to something, but not eggs"):
    pending
    Allergies.allergicTo(Allergen.Eggs, 2) should be(false)

  test("testing for eggs allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Eggs, 255) should be(true)

  test("testing for peanuts allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Peanuts, 0) should be(false)

  test("testing for peanuts allergy - allergic only to peanuts"):
    pending
    Allergies.allergicTo(Allergen.Peanuts, 2) should be(true)

  test("testing for peanuts allergy - allergic to peanuts and something else"):
    pending
    Allergies.allergicTo(Allergen.Peanuts, 7) should be(true)

  test("testing for peanuts allergy - allergic to something, but not peanuts"):
    pending
    Allergies.allergicTo(Allergen.Peanuts, 5) should be(false)

  test("testing for peanuts allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Peanuts, 255) should be(true)

  test("testing for shellfish allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Shellfish, 0) should be(false)

  test("testing for shellfish allergy - allergic only to shellfish"):
    pending
    Allergies.allergicTo(Allergen.Shellfish, 4) should be(true)

  test("testing for shellfish allergy - allergic to shellfish and something else"):
    pending
    Allergies.allergicTo(Allergen.Shellfish, 14) should be(true)

  test("testing for shellfish allergy - allergic to something, but not shellfish"):
    pending
    Allergies.allergicTo(Allergen.Shellfish, 10) should be(false)

  test("testing for shellfish allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Shellfish, 255) should be(true)

  test("testing for strawberries allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Strawberries, 0) should be(false)

  test("testing for strawberries allergy - allergic only to strawberries"):
    pending
    Allergies.allergicTo(Allergen.Strawberries, 8) should be(true)

  test("testing for strawberries allergy - allergic to strawberries and something else"):
    pending
    Allergies.allergicTo(Allergen.Strawberries, 28) should be(true)

  test("testing for strawberries allergy - allergic to something, but not strawberries"):
    pending
    Allergies.allergicTo(Allergen.Strawberries, 20) should be(false)

  test("testing for strawberries allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Strawberries, 255) should be(true)

  test("testing for tomatoes allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Tomatoes, 0) should be(false)

  test("testing for tomatoes allergy - allergic only to tomatoes"):
    pending
    Allergies.allergicTo(Allergen.Tomatoes, 16) should be(true)

  test("testing for tomatoes allergy - allergic to tomatoes and something else"):
    pending
    Allergies.allergicTo(Allergen.Tomatoes, 56) should be(true)

  test("testing for tomatoes allergy - allergic to something, but not tomatoes"):
    pending
    Allergies.allergicTo(Allergen.Tomatoes, 40) should be(false)

  test("testing for tomatoes allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Tomatoes, 255) should be(true)

  test("testing for chocolate allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Chocolate, 0) should be(false)

  test("testing for chocolate allergy - allergic only to chocolate"):
    pending
    Allergies.allergicTo(Allergen.Chocolate, 32) should be(true)

  test("testing for chocolate allergy - allergic to chocolate and something else"):
    pending
    Allergies.allergicTo(Allergen.Chocolate, 112) should be(true)

  test("testing for chocolate allergy - allergic to something, but not chocolate"):
    pending
    Allergies.allergicTo(Allergen.Chocolate, 80) should be(false)

  test("testing for chocolate allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Chocolate, 255) should be(true)

  test("testing for pollen allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Pollen, 0) should be(false)

  test("testing for pollen allergy - allergic only to pollen"):
    pending
    Allergies.allergicTo(Allergen.Pollen, 64) should be(true)

  test("testing for pollen allergy - allergic to pollen and something else"):
    pending
    Allergies.allergicTo(Allergen.Pollen, 224) should be(true)

  test("testing for pollen allergy - allergic to something, but not pollen"):
    pending
    Allergies.allergicTo(Allergen.Pollen, 160) should be(false)

  test("testing for pollen allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Pollen, 255) should be(true)

  test("testing for cats allergy - not allergic to anything"):
    pending
    Allergies.allergicTo(Allergen.Cats, 0) should be(false)

  test("testing for cats allergy - allergic only to cats"):
    pending
    Allergies.allergicTo(Allergen.Cats, 128) should be(true)

  test("testing for cats allergy - allergic to cats and something else"):
    pending
    Allergies.allergicTo(Allergen.Cats, 192) should be(true)

  test("testing for cats allergy - allergic to something, but not cats"):
    pending
    Allergies.allergicTo(Allergen.Cats, 64) should be(false)

  test("testing for cats allergy - allergic to everything"):
    pending
    Allergies.allergicTo(Allergen.Cats, 255) should be(true)

  test("list when: - no allergies"):
    pending
    Allergies.list(0) should be(List())

  test("list when: - just eggs"):
    pending
    Allergies.list(1) should be(List(Allergen.Eggs))

  test("list when: - just peanuts"):
    pending
    Allergies.list(2) should be(List(Allergen.Peanuts))

  test("list when: - just strawberries"):
    pending
    Allergies.list(8) should be(List(Allergen.Strawberries))

  test("list when: - eggs and peanuts"):
    pending
    Allergies.list(3) should be(List(Allergen.Eggs, Allergen.Peanuts))

  test("list when: - more than eggs but not peanuts"):
    pending
    Allergies.list(5) should be(List(Allergen.Eggs, Allergen.Shellfish))

  test("list when: - lots of stuff"):
    pending
    Allergies.list(248) should be(List(Allergen.Strawberries, Allergen.Tomatoes, Allergen.Chocolate, Allergen.Pollen, Allergen.Cats))

  test("list when: - everything"):
    pending
    Allergies.list(255) should be(
      List(Allergen.Eggs, Allergen.Peanuts, Allergen.Shellfish, Allergen.Strawberries, Allergen.Tomatoes, Allergen.Chocolate, Allergen.Pollen, Allergen.Cats),
    )

  test("list when: - no allergen score parts"):
    pending
    Allergies.list(509) should be(
      List(Allergen.Eggs, Allergen.Shellfish, Allergen.Strawberries, Allergen.Tomatoes, Allergen.Chocolate, Allergen.Pollen, Allergen.Cats),
    )

  test("list when: - no allergen score parts without highest valid score"):
    pending
    Allergies.list(257) should be(List(Allergen.Eggs))
