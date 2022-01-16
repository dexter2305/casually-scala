package v2

import org.http4s.server.Router
import cats.Monad
import org.http4s.HttpRoutes

object webroutes {
  import userreg._

  def routes[F[_]: Monad]() = Router(
    "/" -> userregRoutes[F],
  ).orNotFound

}
