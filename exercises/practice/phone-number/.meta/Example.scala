object PhoneNumber:

  private val Nanp = """1?([2-9]\d{2}[2-9]\d{6})""".r

  def clean(phoneNumber: String): Option[String] =
    phoneNumber.filter(_.isDigit) match
      case Nanp(number) => Some(number)
      case _            => None
