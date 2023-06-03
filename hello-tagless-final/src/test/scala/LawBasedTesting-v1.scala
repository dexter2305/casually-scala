package v1

import org.scalactic.Prettifier
import org.scalactic.source.Position
import org.typelevel.discipline.Laws

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
  def apply[F[_]](userAlgebra: UserAlgebra[F])(implicit ev: Monad[F]) = new UserAlgebraLaws[F] {

    implicit override def M: Monad[F] = ev

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
    "rule of create and get"                       -> forAll(pairNameEmailGen) { case (name, email) =>
      laws.writeAndGet(name, email)
    },
    "rule of finding non existing email"           -> forAll(pairNameEmailGen) { case (_, email) =>
      laws.findNonExistingEmail(email)
    },
    "rule of consistency in finding existing user" -> forAll(pairNameEmailGen) { case (name, email) =>
      laws.findByEmailConsistency(name, email)
    }
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
  def apply[F[_]](userAlgebraLaws: UserAlgebraLaws[F]) = new UserAlgebraTests[F] {
    override def laws: UserAlgebraLaws[F] = userAlgebraLaws
  }
}

class InMemoryUserAlgebra[F[_]] private (var store: Map[String, User])(implicit A: cats.Applicative[F])
    extends UserAlgebra[F] {

  import cats.implicits._

  override def create(name: String, email: String): F[Unit] = (store = store + (email -> User(name, email))).pure[F]
  override def findByEmail(email: String): F[Option[User]]  = store.get(email).pure[F]

}
object InMemoryUserAlgebra {

  import cats.Applicative
  def apply[F[_]](implicit A: Applicative[F]) = new InMemoryUserAlgebra[F](Map.empty[String, User])

}

class UserAlgebraSuite
    extends org.scalatest.wordspec.AnyWordSpec
    with org.typelevel.discipline.scalatest.WordSpecDiscipline
    with org.scalatestplus.scalacheck.Checkers {

  import cats.Id
  import cats.effect.IO
  checkAll("InMemory-Id", UserAlgebraTests[Id](UserAlgebraLaws[Id](InMemoryUserAlgebra[Id])).algebra)
  // checkAll("InMemory-IO", UserAlgebraTests[IO](UserAlgebraLaws[IO](InMemoryUserAlgebra[IO])).algebra)

}

