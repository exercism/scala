object PhoneNumber {
  type Parts = (String, String, String)

  private[this] val AreaCodeLength = 3
  private[this] val PrefixLength = 3
  private[this] val LineNumberLength = 4

  private[this] val PhoneNumberPattern = {
    def part(length: Int) = s"(\\d{$length})"
    s"""1?${part(AreaCodeLength)}${part(PrefixLength)}${part(LineNumberLength)}""".r
  }

  def clean(phoneNumber: String): Option[String] = {
    phoneNumber filter (_.isDigit) match {
      case PhoneNumberPattern(areaCode, prefix, lineNumber) => {
        val areaCodeFirst = areaCode.head.asDigit
        val prefixFirst = prefix.head.asDigit
        if (areaCodeFirst >= 2 && prefixFirst >= 2)
          Option(areaCode + prefix + lineNumber)
        else
          None
      }
      case _ => None
    }
  }
}
