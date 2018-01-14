object Acronym {

  sealed trait Action
  case object Capture extends Action
  case object Scan extends Action

  case class State(tla: String, action: Action)

  val separators = Set(' ', '-')

  def abbreviate(phrase: String): String =
    phrase.foldLeft(State("", Capture))((state, char) =>
      state match {
      case State(tla, Scan) =>
        if(separators.contains(char)) State(tla, Capture)
        else State(tla, Scan)
      case State(tla, Capture) =>
        if(separators.contains(char)) State(tla, Capture)
        else State(tla + char.toUpper, Scan)
    }).tla
}
