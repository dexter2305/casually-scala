package v2

import org.http4s.server.Router
import cats.Monad
import org.http4s.HttpRoutes
import cats.effect.kernel.Async

object webroutes {
  import userreg._
  import fileupload._

  def routes[F[_]: Async]() = Router(
    "/" -> userregRoutes[F],
    "/" -> fileupload[F]
  ).orNotFound

}
