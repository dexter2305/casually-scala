package v3

import cats.Monad
import cats.effect.Sync
import cats.implicits._

case class User(name: String, email: String)

trait UserRepository[F[_]] {

  def create(user: User): F[Unit]
  def findByEmail(email: String): F[Option[User]]

}

trait UserRepositoryLaws[F[_]] {

  def userRepository: UserRepository[F]
  implicit def F: Monad[F]

  def createIdempotence(user: User): F[Boolean] =
    for {
      _       <- userRepository.create(user)
      result1 <- userRepository.findByEmail(user.email)
      result2 <- userRepository.findByEmail(user.email)
    } yield result1 == result2

  def createPersistence(user: User): F[Boolean] =
    for {
      _      <- userRepository.create(user)
      result <- userRepository.findByEmail(user.email)
    } yield result.contains(user)

}

object UserRepositoryLaws {
  def apply[F[_]: Monad](repo: UserRepository[F]): UserRepositoryLaws[F] = new UserRepositoryLaws[F] {

    val userRepository: UserRepository[F] = repo
    val F: Monad[F]                       = Monad[F]

  }
}

class ListUserRepository[F[_]: Sync] extends UserRepository[F] {

  private var users: List[User] = List.empty

  def create(user: User): F[Unit] = Sync[F].delay {
    users = user :: users
  }

  def findByEmail(email: String): F[Option[User]] = Sync[F].delay {
    users.find(_.email == email)
  }

}
object ListUserRepository {
  def apply[F[_]](implicit S: Sync[F]) = new ListUserRepository[F]
}

/// tests

import cats.effect.IO
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ListUserRepositoryFlatSpec extends AnyFlatSpec with Matchers {

  import cats.effect.unsafe.implicits.global

  val user1 = User("Alice", "alice@example.com")
  val user2 = User("Bob", "bob@example.com")

  "ListUserRepository" should "satisfy the create idempotence law" in {
    val repo = ListUserRepository[IO]
    val laws = UserRepositoryLaws(repo)

    val result = laws.createIdempotence(user1).unsafeRunSync()

    result shouldBe true
  }

  it should "satisfy the create persistence law" in {
    val repo = ListUserRepository[IO]
    val laws = UserRepositoryLaws(repo)

    val result = laws.createPersistence(user1).unsafeRunSync()

    result shouldBe true
  }

  it should "return None when findByEmail is called with an email that does not exist" in {
    val repo = ListUserRepository[IO]

    val result = repo.findByEmail("nonexistent@example.com").unsafeRunSync()

    result shouldBe None
  }

  it should "return the correct user when findByEmail is called with an email that exists" in {
    val repo = ListUserRepository[IO]
    repo.create(user1).unsafeRunSync()
    repo.create(user2).unsafeRunSync()

    val result = repo.findByEmail(user1.email).unsafeRunSync()

    result shouldBe Some(user1)
  }

}

import cats.effect.IO
import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalacheck.Gen
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import cats.effect.IO
import org.scalacheck.Gen
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ListUserRepositoryUnsafePropSpec extends AnyFlatSpec with Matchers with ScalaCheckPropertyChecks {

  import cats.effect.unsafe.implicits.global

  val emailGen = for {
    mailbox <- Gen.alphaNumStr
    domain  <- Gen.alphaLowerStr
  } yield s"$mailbox@$domain.com"

  val userGen: Gen[User] = for {
    name  <- Gen.alphaNumStr
    email <- emailGen
  } yield User(name, email)

  "ListUserRepository" should "satisfy the create idempotence law" in {
    forAll(userGen) { user =>
      val repo = new ListUserRepository[IO]
      val laws = UserRepositoryLaws(repo)

      val result = laws.createIdempotence(user).unsafeRunSync()

      result shouldBe true
    }
  }

  it should "satisfy the create persistence law" in {
    forAll(userGen) { user =>
      val repo = new ListUserRepository[IO]
      val laws = UserRepositoryLaws(repo)

      val result = laws.createPersistence(user).unsafeRunSync()

      result shouldBe true
    }
  }

  it should "return None when findByEmail is called with an email that does not exist" in {
    forAll(emailGen) { email =>
      val repo = new ListUserRepository[IO]

      val result = repo.findByEmail(email).unsafeRunSync()

      result shouldBe None
    }
  }

  it should "return the correct user when findByEmail is called with an email that exists" in {
    forAll(userGen, userGen) { (user1, user2) =>
      whenever(user1.email != user2.email) {
        val repo = new ListUserRepository[IO]
        repo.create(user1).unsafeRunSync()
        repo.create(user2).unsafeRunSync()

        val result = repo.findByEmail(user1.email).unsafeRunSync()

        result shouldBe Some(user1)
      }
    }
  }

}
