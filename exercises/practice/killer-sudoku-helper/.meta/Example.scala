import KillerSudokuHelper.MaxDigit

case class KillerSudokuHelper(exclude: Set[Int]):
  private def generateRecursive(currentStartDigit: Int, remainingSum: Int, remainingSize: Int): List[List[Int]] =
    if remainingSize == 1 then
      if currentStartDigit <= remainingSum && remainingSum <= MaxDigit && !exclude.contains(remainingSum) then
        List(List(remainingSum))
      else List()
    else
      val maxPossibleFirstDigit =  MaxDigit - remainingSize + 1
      (currentStartDigit to maxPossibleFirstDigit).toList
        .filterNot(digit => exclude.contains(digit))
        .flatMap(digit => generateRecursive(digit + 1, remainingSum - digit, remainingSize - 1).map(s => digit :: s))

object KillerSudokuHelper:
  val MaxDigit = 9
  def combinations(sum: Int, size: Int, exclude: List[Int] = Nil): List[List[Int]] =
    KillerSudokuHelper(exclude.toSet).generateRecursive(1, sum, size)
