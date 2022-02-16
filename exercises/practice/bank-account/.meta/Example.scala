trait BankAccount {

  def closeAccount(): Unit

  def getBalance: Option[Int]

  def incrementBalance(increment: Int): Option[Int]
}

protected case class Account(var balance: Option[Int] = Some(0)) extends BankAccount {

  private def runThreadSafe[A](block: => A): A = this.synchronized(block)

  override def closeAccount(): Unit = runThreadSafe {
    balance = None
  }

  override def getBalance: Option[Int] = runThreadSafe {
    balance
  }

  override def incrementBalance(increment: Int): Option[Int] = runThreadSafe {
    balance flatMap { amount =>
      balance = Some(amount + increment)
      balance
    }
  }
}

object Bank {
  def openAccount(): BankAccount = Account()
}
