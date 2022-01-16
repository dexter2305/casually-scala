package v2

import org.http4s.dsl.Http4sDsl
import cats.Monad
import org.http4s.HttpRoutes
import org.http4s.twirl._
object userreg {

  def userregRoutes[F[_]: Monad] = {
    val dsl = Http4sDsl[F]
    import dsl._

    HttpRoutes.of[F] {

      case req @ GET -> Root / "register"  => Ok(html.userreg())
      case req @ POST -> Root / "register" => Created("user created - let's just assume")
    }
  }
}
