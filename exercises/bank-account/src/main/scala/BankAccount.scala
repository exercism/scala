trait BankAccount {

  def closeAccount(): Unit

  def getBalance: Option[Int]

  def incrementBalance(increment: Int): Option[Int]
}

protected class Account extends BankAccount {

  private[this] var balance:Option[Int] = Some(0)
  override def closeAccount(): Unit = synchronized {
    balance = None
  }

  override def getBalance: Option[Int] = synchronized {
    balance
  }

  override def incrementBalance(increment: Int): Option[Int] = synchronized {
    val newBalance: Option[Int] = balance.map(_ + increment)
    balance = newBalance
    newBalance
  }
}

object Bank {

  def openAccount(): BankAccount = new Account()
}