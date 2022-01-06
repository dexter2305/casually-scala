package v1

import org.http4s.HttpRoutes
import cats.effect.IO
import org.http4s.dsl.io._
import cats.data.Kleisli
import org.http4s.Request
import org.http4s.Response

object UserRoutes {

  final case class User(id: Long = 0, name: String)

  object UserRepository {
    private val users: Map[Long, User] = List("raya", "light", "ryuk", "L", "dexter").zipWithIndex.map { case (name, id) => User(id, name) }
      .map(u => (u.id -> u))
      .toMap

    def findById(id: Long): Option[User] = ???
    def findAll(): Seq[User] = users.values.toList
  }

  lazy val resourceName = "users"

  lazy val userRoutes: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case GET -> Root / resourceName => Ok()
      case GET -> Root / resourceName / LongVar(id)    => Ok("???")
      case DELETE -> Root / resourceName / id => NoContent()
      case POST -> Root / resourceName        => Ok(s"post request received with ")
    }
    .orNotFound
}
