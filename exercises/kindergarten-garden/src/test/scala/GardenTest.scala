import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class KindergartenGardenTest extends FunSuite with Matchers {

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
    "multiple students for the same garden with three students, partial garden - second student's garden") {
    pending
    Garden.defaultGarden("VVCCGG\nVVCCGG").plants("Bob") should be(
      List(Plant.Clover, Plant.Clover, Plant.Clover, Plant.Clover))
  }

  test(
    "multiple students for the same garden with three students, partial garden - third student's garden") {
    pending
    Garden.defaultGarden("VVCCGG\nVVCCGG").plants("Charlie") should be(
      List(Plant.Grass, Plant.Grass, Plant.Grass, Plant.Grass))
  }

  test("full garden - first student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Alice") should be(
      List(Plant.Violets, Plant.Radishes, Plant.Violets, Plant.Radishes))
  }

  test("full garden - second student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Bob") should be(
      List(Plant.Clover, Plant.Grass, Plant.Clover, Plant.Clover))
  }

  test("full garden - second to last student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Kincaid") should be(
      List(Plant.Grass, Plant.Clover, Plant.Clover, Plant.Grass))
  }

  test("full garden - last student's garden") {
    pending
    Garden
      .defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
      .plants("Larry") should be(
      List(Plant.Grass, Plant.Violets, Plant.Clover, Plant.Violets))
  }

  test("non-alphabetical student list - first student's garden") {
    pending
    Garden(List("Samantha", "Patricia", "Xander", "Roger"),
           "VCRRGVRG\nRVGCCGCV").plants("Patricia") should be(
      List(Plant.Violets, Plant.Clover, Plant.Radishes, Plant.Violets))
  }

  test("non-alphabetical student list - second student's garden") {
    pending
    Garden(List("Samantha", "Patricia", "Xander", "Roger"),
           "VCRRGVRG\nRVGCCGCV").plants("Roger") should be(
      List(Plant.Radishes, Plant.Radishes, Plant.Grass, Plant.Clover))
  }

  test("non-alphabetical student list - third student's garden") {
    pending
    Garden(List("Samantha", "Patricia", "Xander", "Roger"),
           "VCRRGVRG\nRVGCCGCV").plants("Samantha") should be(
      List(Plant.Grass, Plant.Violets, Plant.Clover, Plant.Grass))
  }

  test("non-alphabetical student list - fourth (last) student's garden") {
    pending
    Garden(List("Samantha", "Patricia", "Xander", "Roger"),
           "VCRRGVRG\nRVGCCGCV").plants("Xander") should be(
      List(Plant.Radishes, Plant.Grass, Plant.Clover, Plant.Violets))
  }
}
