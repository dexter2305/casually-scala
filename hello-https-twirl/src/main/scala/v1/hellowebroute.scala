package v1

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import cats.Monad
import org.http4s.twirl._
import java.time.LocalDateTime

object hellowebroute {

  def helloWebRoute[F[_]: Monad](): HttpRoutes[F] = {
    val dsl = Http4sDsl[F]
    import dsl._

    HttpRoutes.of[F] {
      case req @ GET -> Root    => Ok(html.hello())
      case GET -> Root / "time" => Ok(html.time(LocalDateTime.now().toString()))
    }
  }
}
