object AllYourBase:
  def rebase(inputBase: Int, inputDigits: List[Int], outputBase: Int): Option[List[Int]] =
    if inputBase < 2 || outputBase < 2 then None
    else if inputDigits.exists(digit => digit < 0 || digit >= inputBase) then None
    else Some(toDigits(inputDigits.foldLeft(0)(_ * inputBase + _), outputBase))

  private def toDigits(number: Int, base: Int): List[Int] =
    if number == 0 then List(0)
    else List.unfold(number)(n => Option.when(n > 0)((n % base, n / base))).reverse
