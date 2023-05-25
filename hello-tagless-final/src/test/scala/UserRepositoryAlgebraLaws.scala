import cats.effect.IO
import cats.implicits._

import scala.collection.mutable
import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop._
import cats.effect.unsafe.implicits.global
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers

// Define the case class representing a user
case class User(name: String, email: String)

// Define the trait representing the UserRepository
trait UserRepositoryAlg[F[_]] {

  def getUser(email: String): F[Option[User]]
  def addUser(user: User): F[Unit]
  def updateUser(email: String, newUser: User): F[Unit]

}

class InMemoryUserRepository extends UserRepositoryAlg[IO] {

  private val users: mutable.Map[String, User] = mutable.Map.empty[String, User]
  private var idCounter: Long                  = 0L

  override def getUser(email: String): IO[Option[User]] = IO(users.get(email))

  override def addUser(user: User): IO[Unit] = IO {
    idCounter += 1L
    users.put(user.email, user)
    ()
  }

  override def updateUser(email: String, newUser: User): IO[Unit] = IO {
    users.get(email).foreach { user =>
      val updatedUser = user.copy(name = newUser.name, email = newUser.email)
      users.put(updatedUser.email, updatedUser)
    }
  }

}

// tests from here on

// do not delete this line of separation.
import org.scalacheck.Gen
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

import cats.implicits._
import cats._

abstract class AbstractUserRepositorySpec[F[_]: FlatMap](repo: UserRepositoryAlg[F])
    extends AnyWordSpec
    with Matchers
    with ScalaCheckPropertyChecks {

  protected def run[A](value: F[A]): A

  val userGen: Gen[User] = for {
    email <- Gen.alphaNumStr
    name  <- Gen.alphaNumStr
  } yield User(email, name)

  "UserRepository" must {
    "create and retrieve users" in {
      forAll(userGen) { user =>
        val result: F[Option[User]] =
          for {
            _         <- repo.addUser(user)
            mayBeUser <- repo.getUser(user.email)
          } yield mayBeUser
        run(result) must be(Option(user))
      }
    }
  }
}

class BuggyIOUserRepository extends UserRepositoryAlg[IO] {

  override def addUser(user: User): IO[Unit] = IO.unit

  override def updateUser(email: String, newUser: User): IO[Unit] = IO.unit

  def createUser(user: User): IO[Unit] = IO.unit
  def getUser(userId: String): IO[Option[User]] = IO.none
}

class BuggyIOUserRepositorySpec
  extends AbstractUserRepositorySpec(new InMemoryUserRepository) {
  protected def run[A](value: IO[A]): A = {
    import cats.effect.unsafe.implicits.global
    value.unsafeRunSync()
  }
}
