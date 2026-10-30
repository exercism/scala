import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class KindergartenGardenTest extends AnyFunSuite with Matchers {

  test("partial garden - garden with single student") {
    Garden.defaultGarden("RC\nGG").plants("Alice") should be(
      List(Plant.Radishes, Plant.Clover, Plant.Grass, Plant.Grass))
  }

  test("partial garden - different garden with single student") {
    pending
    Garden.defaultGarden("VC\nRC").plants("Alice") should be(
      List(Plant.Violets, Plant.Clover, Plant.Radishes, Plant.Clover))
  }

  test("partial garden - garden with two students") {
    pending
    Garden.defaultGarden("VVCG\nVVRC").plants("Bob") should be(
      List(Plant.Clover, Plant.Grass, Plant.Radishes, Plant.Clover))
  }

  test(
    "partial garden - multiple students for the same garden with three students - second student's garden") {
    pending
    Garden.defaultGarden("VVCCGG\nVVCCGG").plants("Bob") should be(
      List(Plant.Clover, Plant.Clover, Plant.Clover, Plant.Clover))
  }

  test(
    "partial garden - multiple students for the same garden with three students - third student's garden") {
    pending
    Garden.defaultGarden("VVCCGG\nVVCCGG").plants("Charlie") should be(
      List(Plant.Grass, Plant.Grass, Plant.Grass, Plant.Grass))
  }

  test("full garden - for Alice, first student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Alice") should be(
      List(Plant.Violets, Plant.Radishes, Plant.Violets, Plant.Radishes))
  }

  test("full garden - for Bob, second student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Bob") should be(
      List(Plant.Clover, Plant.Grass, Plant.Clover, Plant.Clover))
  }

  test("full garden - for Charlie") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Charlie") should be(
      List(Plant.Violets, Plant.Violets, Plant.Clover, Plant.Grass))
  }

  test("full garden - for David") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("David") should be(
      List(Plant.Radishes, Plant.Violets, Plant.Clover, Plant.Radishes))
  }

  test("full garden - for Eve") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Eve") should be(
      List(Plant.Clover, Plant.Grass, Plant.Radishes, Plant.Grass))
  }

  test("full garden - for Fred") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Fred") should be(
      List(Plant.Grass, Plant.Clover, Plant.Violets, Plant.Clover))
  }

  test("full garden - for Ginny") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Ginny") should be(
      List(Plant.Clover, Plant.Grass, Plant.Grass, Plant.Clover))
  }

  test("full garden - for Harriet") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Harriet") should be(
      List(Plant.Violets, Plant.Radishes, Plant.Radishes, Plant.Violets))
  }

  test("full garden - for Ileana") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Ileana") should be(
      List(Plant.Grass, Plant.Clover, Plant.Violets, Plant.Clover))
  }

  test("full garden - for Joseph") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Joseph") should be(
      List(Plant.Violets, Plant.Clover, Plant.Violets, Plant.Grass))
  }

  test("full garden - for Kincaid, second to last student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Kincaid") should be(
      List(Plant.Grass, Plant.Clover, Plant.Clover, Plant.Grass))
  }

  test("full garden - for Larry, last student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Larry") should be(
      List(Plant.Grass, Plant.Violets, Plant.Clover, Plant.Violets))
  }
}
