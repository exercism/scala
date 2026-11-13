object Bob:
  def response(statement: String): String =
    val trimmed  = statement.trim
    val silent   = trimmed.isEmpty
    val question = trimmed.endsWith("?")
    val shouting = trimmed.exists(_.isLetter) && !trimmed.exists(_.isLower)

    if silent then "Fine. Be that way!"
    else if shouting && question then "Calm down, I know what I'm doing!"
    else if shouting then "Whoa, chill out!"
    else if question then "Sure."
    else "Whatever."
