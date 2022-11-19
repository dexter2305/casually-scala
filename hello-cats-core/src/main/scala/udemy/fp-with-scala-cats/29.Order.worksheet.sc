import cats._
import cats.implicits._
case class Account(id: Long, number: String, balance: Double, owner: String)

object Account {

  implicit def orderByNumber(implicit oString: Order[String]) = Order.from[Account]((a, b) => oString.compare(a.number, b.number))

  object Instances {
    implicit def orderByBalance(implicit oDouble: Order[Double]) = Order.from[Account]((a, b) => oDouble.compare(a.balance, b.balance))
    implicit def orderById(implicit oLong: Order[Long]) = Order.by[Account, Long](account => account.id)
  }
}

val account2 = Account(2, "200", 10, "ryuk")
val account1 = Account(1, "100", 100, "dexter")


Order.compare(account1, account1)

def sort[A](as: List[A])(implicit orderA: Order[A]) = as.sorted(orderA.toOrdering)

import Account.Instances.orderByBalance
sort(List(account1, account2))