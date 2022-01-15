package v2

import cats._
import cats.effect._
import cats.implicits._

import org.http4s.EntityEncoder
import org.http4s.circe._
import io.circe.generic.auto._
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import org.http4s.EntityDecoder
import io.circe.Decoder
import io.circe.HCursor

object userroutes {

  @deprecated("prefer using consolidated routes under routes.allRoutes")
  def allRoutesComplete[F[_]: Concurrent] = userRoutes[F].orNotFound

  def userRoutes[F[_]: Concurrent]: HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl._
    import repository._
    import domain._

    implicit val usersEntityEncoder: EntityEncoder[F, Seq[User]] = jsonEncoderOf[F, Seq[User]]
    implicit val userEntityEncoder: EntityEncoder[F, User]       = jsonEncoderOf[F, User]
    implicit val userDecoder: Decoder[User]                      = new Decoder[User] {
      override def apply(c: HCursor): Decoder.Result[User] = for {
        name <- c.get[String]("name")
      } yield User(name)
    }
    implicit val userEntityDecoder: EntityDecoder[F, User]       = jsonOf[F, User]

    HttpRoutes.of[F] {
      case GET -> Root / "users"        => Ok(userRepository.findAll())
      case GET -> Root / "users" / id   => {
        userRepository.findById(id) match {
          case Some(user) => Ok(user)
          case None       => NotFound(s"User id $id not found")
        }
      }
      case req @ POST -> Root / "users" =>
        for {
          user     <- req.as[User]
          _        = userRepository.add(user)
          response <- Created(s"Created $user")
        } yield response
    }
  }
}
