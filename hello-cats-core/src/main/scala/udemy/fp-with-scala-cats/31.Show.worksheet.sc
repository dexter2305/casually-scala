import scala.util.Random
import cats._
import cats.implicits._

case class Account(id: Long, number: String, balance: Double, owner: String)
object Account {

  implicit def show = Show.fromToString[Account]

  object Instances {
    implicit val byOwnerWithAccountNumberAndBalance: Show[Account] = Show.show[Account](account => s"${account.owner} - ${account.number} - ${account.balance}")
    implicit val byOwnername: Show[Account] = Show.show[Account](a => s"This account belongs to ${a.owner}")
  }
}

val account = Account(1, "142-1918", 1000, "Dexter")

Show[Account].show(account)
import Account.Instances.byOwnername
Show[Account].show(account)
account.show

val r = Random
r.nextInt (40) 