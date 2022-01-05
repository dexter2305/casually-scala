package v1

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.http4s._
import cats.effect._
object Main extends App {

  case class ErrorInfo(message: String)

  val helloEndpoint = endpoint.get
    .in("hello")
    .in(query[Option[String]]("name"))
    .out(stringBody.description("Hello response"))
    .description("Simple Hello world endpoint")

  def helloService(optionalString: Option[String]): IO[Either[Unit, String]] = IO.pure(
    optionalString match {
      case None        => Right("Hello")
      case Some(value) => Right(s"Hello, $value")
    },
  )

  /*
  val helloRoute: HttpRoutes[IO] = Http4sServerInterpreter[IO]().toRoutes(helloEndpoint.serverLogic(helloService _))

  BlazeServerBuilder[IO]
    .withExecutionContext(ExecutionContext.global)
    .withHttpApp(HttpRoutes.of[IO](helloRoute))
    .bindHttp(8080, "localhost")
    */
}

object Endpoints {

  val greetings =
    endpoint.get
      .in("hello")
      .in(query[String]("name"))
      .errorOut(statusCode)
      .out(stringBody)
      .description(
        "Returns a simple JSON object using the provided query parameter 'name' which must not be empty.",
      )

}
