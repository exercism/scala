import org.scalatest.concurrent.{IntegrationPatience, Conductors}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version created manually **/
class BankAccountTest extends AnyFunSuite with Matchers with Conductors with IntegrationPatience {
  test("open account") {
    Bank.openAccount().getBalance should be (Some(0))
  }

  test("incrementing and checking balance") {
    pending
    val acct = Bank.openAccount()
    acct.getBalance should be (Some(0))
    acct.incrementBalance(10) should be (Some(10))
    acct.getBalance should be (Some(10))
    acct.incrementBalance(10) should be (Some(20))
    acct.getBalance should be (Some(20))
  }

  test("closed account should hold no balance") {
    pending
    val acct = Bank.openAccount()
    acct.closeAccount()
    acct.incrementBalance(10)
    acct.incrementBalance(10)
    acct.getBalance should be (None)
  }

  test("incrementing balance from multiple threads") {
    pending
    val conductor = new Conductor
    import conductor._

    val acct = Bank.openAccount()

    threadNamed("t1") {
      acct.incrementBalance(10)
      acct.getBalance should be (Some(10))
      beat should be (1)
      waitForBeat(2)
      acct.getBalance should be (Some(15))
    }

    threadNamed("t2") {
      waitForBeat(1)
      acct.getBalance should be (Some(10))
      acct.incrementBalance(5)
      acct.getBalance should be (Some(15))
      beat should be (2)
    }
  }

  test("incrementing balance from multiple threads - concurrent updates") {
    pending
    val conductor = new Conductor
    import conductor._

    val acct = Bank.openAccount()

    threadNamed("t1") {
      for (a <- 1 to 10)
        acct.incrementBalance(10)
    }

    threadNamed("t2") {
      for (a <- 1 to 10)
        acct.incrementBalance(5)
    }

    whenFinished {
      acct.getBalance should be (Some(150))
    }
  }
}
