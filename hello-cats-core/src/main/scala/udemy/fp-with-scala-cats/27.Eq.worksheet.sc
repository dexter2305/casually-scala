import cats._
import cats.implicits._

case class Account(id: Long, number: Long, balance: Double, owner: String)
object Account {

  implicit val universalEq = Eq.fromUniversalEquals[Account]

  object instances {

    //FIX: Do not use '==' with cats. Prefer using Eq[A].eqv for the type to be compared.
    implicit val eqAccountById1 = Eq.instance[Account]((a, b) => a.id == b.id)

    //Preferred
    
    //Compare by id
    implicit val eqAccountById2                            = Eq.instance[Account]((a, b) => Eq[Long].eqv(a.id, b.id))
    implicit def eqAccountById3(implicit eqLong: Eq[Long]) = Eq.instance[Account]((a, b) => eqLong.eqv(a.id, b.id))
    implicit def eqAccountById4(implicit eqLong: Eq[Long]) = Eq.by[Account, Long](account => account.id)
    
    //Compare by account number
    implicit def eqAccountByNumber(implicit eqLong: Eq[Long]) = Eq.by[Account, Long](_.number)

  }
}

val account_1 = Account(1, 10, 1000, "dexter")
val account_2 = Account(1, 20, 2000, "light")

Eq.eqv(account_1, account_2)
//import Account.instances.eqAccountById2

Eq.eqv(account_1, account_2)
import Account.instances.eqAccountByNumber
Eq.eqv(account_1, account_1)

account_1 === account_2.copy(number = account_1.number)