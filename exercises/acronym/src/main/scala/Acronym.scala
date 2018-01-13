object Acronym {

  sealed trait Action
  case object Capture extends Action
  case object Scan extends Action

  case class State(tla: String, action: Action)

  def abbreviate(phrase: String): String =
    phrase.foldLeft(State("", Capture))((state, char) =>
      state match {
      case State(tla, Scan) =>
        if(char == ' ') State(tla, Capture)
        else State(tla, Scan)
      case State(tla, Capture) =>
        if(char == ' ') State(tla, Capture)
        else State(tla + char, Scan)
    }).tla
}
