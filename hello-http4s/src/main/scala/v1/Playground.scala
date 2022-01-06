package v1

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.Method
import org.http4s.Request

import org.http4s.implicits._

object Playground extends App {

  val timeRoute: HttpRoutes[IO] = HttpRoutes.of[IO] { case GET -> Root / "time" =>
    Ok("")

  }

  val helloRoute: HttpRoutes[IO] = HttpRoutes.of[IO] { case GET -> Root / "hello" =>
    Ok("Hello, from http4s")
  }

  //testing the routes
  val getHello = Request[IO](Method.GET, uri"/hello")

  val x = timeRoute.run(getHello)

}
