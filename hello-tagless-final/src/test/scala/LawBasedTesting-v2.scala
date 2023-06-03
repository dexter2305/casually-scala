package v2

import cats.Monad

package object impl {

  case class User(name: String, email: String)

  trait UserRepository[F[_]] {

    def add(name: String, email: String): F[Unit]
    def findByEmail(email: String): F[Option[User]]

  }
  object UserRepository {
    def apply[F[_]](implicit ev: UserRepository[F]) = ev
  }

  class MapBasedUserRepository[F[_]: cats.effect.Sync] private (initStore: Map[String, User] = Map.empty)
      extends UserRepository[F] {

    import cats.effect.Sync

    private var store                                      = initStore
    override def add(name: String, email: String): F[Unit] = Sync[F].delay {
      store = store + (email -> User(name, email))
    }

    override def findByEmail(email: String): F[Option[User]] = Sync[F].delay(store.find(_._1 == email).map(_._2))

  }
  object MapBasedUserRepository {

    import cats.effect.Sync
    def apply[F[_]: Sync] = new MapBasedUserRepository[F]

  }

  trait UserRepositoryRules[F[_]] {

    import cats.implicits._
    import cats.Monad

    implicit def M: Monad[F]
    def repository: UserRepository[F]

    // rule # 1: user can be found only if the user exists
    def existenceLaw(name: String, email: String) =
      for {
        _        <- repository.add(name, email)
        repoUser <- repository.findByEmail(email)
        _ = println(s"$repoUser and User($name,$email)")
      } yield repoUser.contains(User(name, email))

    // rule # 2: user can not be found if the user does not exist
    def nonExistenceLaw(name: String, email: String) = for {
      nonExistingUser <- repository.findByEmail(email)
    } yield nonExistingUser match {
      case None =>
        true
      case _    =>
        println(nonExistingUser)
        false
    }

  }

}

class UserRepositorySpecSimple extends org.scalatest.flatspec.AnyFlatSpec with org.scalatest.matchers.should.Matchers {

  import impl._
  import cats.effect.IO
  import cats.effect.unsafe.implicits.global

  val userRepository: UserRepository[IO] = MapBasedUserRepository[IO]

  val userRepositoryRules: UserRepositoryRules[IO] = new UserRepositoryRules[IO] {

    implicit override def M: cats.Monad[IO]     = implicitly[cats.effect.MonadCancel[IO, Throwable]]
    override def repository: UserRepository[IO] = userRepository

  }

  "UserRepository" should "satisfy the existence law" in {
    val name  = "John"
    val email = "john@example.com"

    val result = userRepositoryRules.existenceLaw("", "").unsafeRunSync()

    result shouldBe true
  }

  it should "satisfy the non existence law" in {
    val name  = "John"
    val email = "john@test.com"

    val result = userRepositoryRules.nonExistenceLaw(name, email).unsafeRunSync()

    result shouldBe true
  }

}

/**
  * Uses cats-effect-testing. Documentation of cats-effect-testing mentions that the tests are executed synchronously in
  * the backend.
  */

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import impl._

class UserRepositorySpecWithCatsTesting extends AsyncFlatSpec with Matchers with AsyncIOSpec {

  val userRepository: UserRepository[IO] = MapBasedUserRepository[IO]

  val userRepositoryRules: UserRepositoryRules[IO] = new UserRepositoryRules[IO] {

    implicit override def M: cats.Monad[IO]     = implicitly[cats.effect.MonadCancel[IO, Throwable]]
    override def repository: UserRepository[IO] = userRepository

  }

  "UserRepository" should "satisfy the existence law" in {
    val name  = "John"
    val email = "john@example.com"

    userRepositoryRules.existenceLaw(name, email).asserting(result => result shouldBe true)
  }

  it should "satisfy the non-existence law" in {
    val name  = "John"
    val email = "john@test.com"

    userRepositoryRules.nonExistenceLaw(name, email).asserting(result => result shouldBe true)
  }

}

import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import org.scalacheck.Arbitrary
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class UserRepositorySpecWithPropertyChecks extends AnyWordSpec with ScalaCheckPropertyChecks with Matchers {

  import cats.effect.IO
  import cats.effect.MonadCancel
  import cats.effect.unsafe.implicits.global

  implicit val userRepository: UserRepository[IO] = MapBasedUserRepository[IO]
  def laws: UserRepositoryRules[IO]               = new UserRepositoryRules[IO] {

    implicit override def M: Monad[IO] = implicitly[MonadCancel[IO, Throwable]]

    override def repository: UserRepository[IO] = MapBasedUserRepository[IO]

  }

  // val userRepositoryLaws: UserRepositoryLaws[IO]  = UserRepositoryLaws[IO]

  implicit val userArbitrary: Arbitrary[User] = Arbitrary {
    for {
      name  <- Arbitrary.arbitrary[String]
      email <- Arbitrary.arbitrary[String]
    } yield User(name, email)
  }

  "UserRepository" must {
    "satisfy existence law" in {
      forAll((name: String, email: String) => laws.existenceLaw(name, email).unsafeRunSync() should be(true))
    }
  }

}
