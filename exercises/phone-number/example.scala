import PhoneNumber._

class PhoneNumber(phoneNumber: String) {

  lazy val parts: Option[Parts] = parse(phoneNumber)

  lazy val number: Option[String] =
    parts map { case (areaCode, prefix, lineNumber) => s"$areaCode$prefix$lineNumber" }

  lazy val areaCode: Option[String] = parts map (_._1)

  lazy val prettyPrint: Option[String]  = 
    parts map { case (areaCode, prefix, lineNumber) => s"($areaCode) $prefix-$lineNumber" }
}

object PhoneNumber {
  type Parts = (String, String, String)

  private[this] val AreaCodeLength = 3
  private[this] val PrefixLength = 3
  private[this] val LineNumberLength = 4

  private[this] val PhoneNumberPattern = {
    def part(length: Int) = s"(\\d{$length})"
    s"""1?${part(AreaCodeLength)}${part(PrefixLength)}${part(LineNumberLength)}""".r
  }

  def parse(phoneNumber: String): Option[Parts] = {
    val digits = phoneNumber filter (_.isDigit)
    digits match {
      case PhoneNumberPattern(areaCode, prefix, lineNumber) =>
        Some((areaCode, prefix, lineNumber))
      case _ => None
    }
  }
}
