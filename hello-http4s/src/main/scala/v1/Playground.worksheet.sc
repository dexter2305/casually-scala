import cats.effect._
import org.http4s._
import org.http4s.implicits._
import org.http4s.dsl.io._
import cats.effect.unsafe.implicits.global
//production endpoint
val helloRoute = HttpRoutes.of[IO] {
  case GET -> Root / "users" / id => Ok("")
}

//get request to test
val get1User = Request[IO](Method.GET, uri"/users/1")

val resp = helloRoute.orNotFound.run(get1User)

resp.unsafeRunSync()


