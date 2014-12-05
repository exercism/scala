import org.scalatest.{Matchers, FunSuite}

class GardenTest extends FunSuite with Matchers {
  test("missing child tests") {
    Garden.defaultGarden("RC\nGG").getPlants("Potter") should
      equal(List.empty)
  }

  test("alice tests") {
    Garden.defaultGarden("RC\nGG").getPlants("Alice") should
      equal(List(Plant.Radishes, Plant.Clover, Plant.Grass, Plant.Grass))
    Garden.defaultGarden("VC\nRC").getPlants("Alice") should
      equal(List(Plant.Violets, Plant.Clover, Plant.Radishes, Plant.Clover))
  }

  test("small garden") {
    Garden.defaultGarden("VVCG\nVVRC").getPlants("Bob") should
      equal(List(Plant.Clover, Plant.Grass, Plant.Radishes, Plant.Clover))
  }

  test("medium garden") {
    val garden = Garden.defaultGarden("VVCCGG\nVVCCGG")
    garden.getPlants("Bob") should
      equal(List(Plant.Clover, Plant.Clover, Plant.Clover, Plant.Clover))
    garden.getPlants("Charlie") should
      equal(List(Plant.Grass, Plant.Grass, Plant.Grass, Plant.Grass))
  }

  test("full garden") {
    val garden = Garden.defaultGarden("VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV")
    garden.getPlants("Alice") should
      equal(List(Plant.Violets, Plant.Radishes, Plant.Violets, Plant.Radishes))
    garden.getPlants("Bob") should
      equal(List(Plant.Clover, Plant.Grass, Plant.Clover, Plant.Clover))
    garden.getPlants("David") should
      equal(List(Plant.Radishes, Plant.Violets, Plant.Clover, Plant.Radishes))
    garden.getPlants("Eve") should
      equal(List(Plant.Clover, Plant.Grass, Plant.Radishes, Plant.Grass))
    garden.getPlants("Fred") should
      equal(List(Plant.Grass, Plant.Clover, Plant.Violets, Plant.Clover))
    garden.getPlants("Ginny") should
      equal(List(Plant.Clover, Plant.Grass, Plant.Grass, Plant.Clover))
    garden.getPlants("Harriet") should
      equal(List(Plant.Violets, Plant.Radishes, Plant.Radishes, Plant.Violets))
    garden.getPlants("Ileana") should
      equal(List(Plant.Grass, Plant.Clover, Plant.Violets, Plant.Clover))
    garden.getPlants("Joseph") should
      equal(List(Plant.Violets, Plant.Clover, Plant.Violets, Plant.Grass))
    garden.getPlants("Kincaid") should
      equal(List(Plant.Grass, Plant.Clover, Plant.Clover, Plant.Grass))
    garden.getPlants("Larry") should
      equal(List(Plant.Grass, Plant.Violets, Plant.Clover, Plant.Violets))
  }

  test("surprise garden") {
    val garden = Garden(List("Samantha", "Patricia", "Xander", "Roger"), "VCRRGVRG\nRVGCCGCV")
    garden.getPlants("Patricia") should
      equal(List(Plant.Violets, Plant.Clover, Plant.Radishes, Plant.Violets))
    garden.getPlants("Roger") should
      equal(List(Plant.Radishes, Plant.Radishes, Plant.Grass, Plant.Clover))
    garden.getPlants("Samantha") should
      equal(List(Plant.Grass, Plant.Violets, Plant.Clover, Plant.Grass))
    garden.getPlants("Xander") should
      equal(List(Plant.Radishes, Plant.Grass, Plant.Clover, Plant.Violets))
  }
}
