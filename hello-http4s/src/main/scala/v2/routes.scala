package v2

import cats._
import cats.effect._
import cats.implicits._
import org.http4s.server.Router

object routes {
  import userroute._

  def allRoutes[F[_]: Concurrent] = Router {
    "/" -> userRoutes[F]
  }.orNotFound

}
