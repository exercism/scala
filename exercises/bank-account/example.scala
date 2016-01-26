trait BankAccount {
  def closeAccount(): Unit
  def getBalance: Option[Int]
  def incrementBalance(increment: Int): Option[Int]
}

protected case class Account() extends BankAccount {
  private var balance: Option[Int] = Some(0)

  override def closeAccount(): Unit = {
    this.synchronized {
      balance = None
    }
  }

  override def getBalance: Option[Int] = {
    this.synchronized {
      balance
    }
  }

  override def incrementBalance(increment: Int): Option[Int] = {
    this.synchronized {
      balance match {
        case Some(b) => balance = Some(b + increment)
          balance
        case None => None
      }
    }
  }
}

object BankAccount {
  def apply(): BankAccount = new Account()
}


