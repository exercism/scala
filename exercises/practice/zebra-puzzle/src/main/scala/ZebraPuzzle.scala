object ZebraPuzzle:

  enum Resident:
    case Englishman, Spaniard, Ukrainian, Norwegian, Japanese

  export Resident.*

  case class Solution(waterDrinker: Resident, zebraOwner: Resident)

  lazy val solve: Solution = ???
