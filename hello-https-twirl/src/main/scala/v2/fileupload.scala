package v2

import cats.effect.IO
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.multipart.{Multipart, Part}
import org.http4s.MediaType._
import org.http4s.headers._
import cats.Monad
import org.http4s.twirl._
import cats.effect.kernel.Async
import fs2.text._
object fileupload {

  def fileupload[F[_]: Async]: HttpRoutes[F] = {

    val dsl = Http4sDsl[F]
    import dsl._
    import org.http4s.server._
    HttpRoutes.of[F] {

      case req @ GET -> Root / "select"  => Ok(html.fileupload())
      case req @ POST -> Root / "upload" =>
        req.decode[Multipart[F]] { m =>
          m.parts.find(_.name == Some("dataFile")) match {
            case None       => BadRequest(s"Not file")
            case Some(part) =>
              Ok(part.body.through(utf8.decode))
          }
        }
    }
  }
}
