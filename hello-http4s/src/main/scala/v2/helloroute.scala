package v2

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import cats.Monad

object helloroute {

  def helloRoutes[F[_]: Monad]() = {
    val dsl = Http4sDsl[F]
    import dsl._

    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name => Ok(s"Hello $name")
      case GET -> Root / "hello"        => Ok(s"Welcome")
    }

  }
}
