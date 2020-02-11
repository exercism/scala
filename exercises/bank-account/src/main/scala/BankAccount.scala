trait BankAccount {

  def closeAccount(): Unit

  def getBalance: Option[Int]

  def incrementBalance(increment: Int): Option[Int]
}

object Bank {
  def openAccount(): BankAccount = ???
}

