object Lasagna:
  def expectedMinutesInOven: Int = 40

  def remainingMinutesInOven(actualMinutesInOven: Int): Int =
    expectedMinutesInOven - actualMinutesInOven

  def preparationTimeInMinutes(numberOfLayers: Int): Int = numberOfLayers * 2

  def elapsedTimeInMinutes(numberOfLayers: Int, actualMinutesInOven: Int): Int =
    preparationTimeInMinutes(numberOfLayers) + actualMinutesInOven
