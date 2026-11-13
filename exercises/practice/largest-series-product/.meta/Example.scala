object Series:

  def largestProduct(span: Int, digits: String): Option[Int] =
    if span < 0 || span > digits.length || !digits.forall(_.isDigit) then None
    else if span == 0 then Some(1)
    else Some(digits.map(_.asDigit).sliding(span).map(_.product).max)
