import org.scalatest.{Matchers, FlatSpec}

class OcrTest extends FlatSpec with Matchers {
  it should "recognize zero" in {
    Ocr(List(" _ "
           , "| |"
           , "|_|"
           , "   ").mkString("\n")).convert should be ("0")
  }

  it should "recognize one" in {
    pending
    Ocr(List("   "
           , "  |"
           , "  |"
           , "   ").mkString("\n")).convert should be ("1")
  }

  it should "recognize two" in {
    pending
    Ocr(List(" _ "
           , " _|"
           , "|_ "
           , "   ").mkString("\n")).convert should be ("2")
  }

  it should "recognize three" in {
    pending
    Ocr(List(" _ "
           , " _|"
           , " _|"
           , "   ").mkString("\n")).convert should be ("3")
  }

  it should "recognize four" in {
    pending
    Ocr(List("   "
           , "|_|"
           , "  |"
           , "   ").mkString("\n")).convert should be ("4")
  }

  it should "recognize five" in {
    pending
    Ocr(List(" _ "
           , "|_ "
           , " _|"
           , "   ").mkString("\n")).convert should be ("5")
  }

  it should "recognize six" in {
    pending
    Ocr(List(" _ "
           , "|_ "
           , "|_|"
           , "   ").mkString("\n")).convert should be ("6")
  }

  it should "recognize seven" in {
    pending
    Ocr(List(" _ "
           , "  |"
           , "  |"
           , "   ").mkString("\n")).convert should be ("7")
  }

  it should "recognize eight" in {
    pending
    Ocr(List(" _ "
           , "|_|"
           , "|_|"
           , "   ").mkString("\n")).convert should be ("8")
  }

  it should "recognize nine" in {
    pending
    Ocr(List(" _ "
           , "|_|"
           , " _|"
           , "   ").mkString("\n")).convert should be ("9")
  }

  it should "recognize garble" in {
    pending
    Ocr(List("   "
           , "| |"
           , "| |"
           , "   ").mkString("\n")).convert should be ("?")
  }

  it should "recognize ten" in {
    pending
    Ocr(List("    _ "
           , "  || |"
           , "  ||_|"
           , "      ").mkString("\n")).convert should be ("10")
  }

  it should "recognize 110101100" in {
    pending
    Ocr(List("       _     _        _  _ "
           , "  |  || |  || |  |  || || |"
           , "  |  ||_|  ||_|  |  ||_||_|"
           , "                           ").mkString("\n")).convert should be ("110101100")
  }

  it should "recognize garbled multi digit ocr" in {
    pending
    Ocr(List("       _     _           _ "
           , "  |  || |  || |     || || |"
           , "  |  | _|  ||_|  |  ||_||_|"
           , "                           ").mkString("\n")).convert should be ("11?10?1?0")
  }

  it should "recognize 1234567890" in {
    pending
    Ocr(List("    _  _     _  _  _  _  _  _ "
           , "  | _| _||_||_ |_   ||_||_|| |"
           , "  ||_  _|  | _||_|  ||_| _||_|"
           , "                              ").mkString("\n")).convert should be ("1234567890")
  }

  it should "recognize 123,456,789" in {
    pending
    Ocr(List("    _  _ "
           , "  | _| _|"
           , "  ||_  _|"
           , "         "
           , "    _  _ "
           , "|_||_ |_ "
           , "  | _||_|"
           , "         "
           , " _  _  _ "
           , "  ||_||_|"
           , "  ||_| _|"
           , "         ").mkString("\n")).convert should be ("123,456,789")
  }
}
