package tg.testing.withlaws

import cats.Id
import cats.Monad
case class User(name: String, email: String)
object User {

  import cats.Eq

  implicit def userEq = Eq.fromUniversalEquals[User]

}
trait UserAlgebra[F[_]] {

  def create(name: String, email: String): F[Unit]
  def findByEmail(email: String): F[Option[User]]

}

trait UserAlgebraLaws[F[_]] {

  import cats.Monad, cats.implicits._, cats.laws._

  implicit def M: Monad[F]

  def algebra: UserAlgebra[F]

  def writeAndGet(name: String, email: String) =
    algebra.create(name, email) *> algebra.findByEmail(email) <->
      algebra.create(name, email) *> User(name, email).some.pure[F]

  def findNonExistingEmail(email: String) =
    algebra.findByEmail(email) <-> none[User].pure[F]

  def findByEmailConsistency(name: String, email: String) =
    algebra.create(name, email) *> algebra.findByEmail(email) *> algebra.findByEmail(email) <->
      algebra.create(name, email) *> algebra.findByEmail(email)

}

object UserAlgebraLaws {

  import cats.Monad
  def apply[F[_]](userAlgebra: UserAlgebra[F]) = new UserAlgebraLaws[F] {

    implicit override def M: Monad[F] = Monad[F]

    override def algebra: UserAlgebra[F] = userAlgebra

  }

}

trait UserAlgebraTests[F[_]] extends org.typelevel.discipline.Laws {

  import org.scalacheck.Gen
  import org.scalacheck.Prop._
  import cats.Eq
  import cats.kernel.laws.discipline.catsLawsIsEqToProp

  def laws: UserAlgebraLaws[F]

  def algebra(implicit eqFOptUser: Eq[F[Option[User]]]) = new SimpleRuleSet(
    "UserAlgebra",
    // fixme: could not get this working implemented. also it was too time complex. is it really worth?
    "rule of create and get"                       -> forAll(laws.writeAndGet _),
    "rule of finding non existing email"           -> forAll(laws.findNonExistingEmail _),
    "rule of consistency in finding existing user" -> forAll(laws.findByEmailConsistency _)
  )

  lazy val pairNameEmailGen = for {
    name  <- Gen.alphaStr
    email <- for {
      mailbox <- Gen.alphaNumStr
      domain  <- Gen.alphaLowerChar
    } yield s"$mailbox@$domain.com"
  } yield (name, email)

}
object UserAlgebraTests {

  def apply[F[_]](algebra: UserAlgebra[F])(implicit M: Monad[F]) = new UserAlgebraTests[F] {

    override def laws: UserAlgebraLaws[F] = UserAlgebraLaws(algebra)

  }
}

