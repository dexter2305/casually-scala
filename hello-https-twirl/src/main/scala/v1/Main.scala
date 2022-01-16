package v1

import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import org.http4s.blaze.server.BlazeServerBuilder
import java.net.InetAddress
import org.http4s.server.Router

object Main extends IOApp {

  import hellowebroute._

  val webRoutes = Router {
    "/app" -> helloWebRoute[IO]()
  }.orNotFound

  val program = BlazeServerBuilder[IO]
    .bindHttp(
      port = 9090,
      host = InetAddress.getLocalHost().getHostName(),
    )
    //.bindHttp(8080, "localhost")
    .withHttpApp(webRoutes)
    .serve
    .compile
    .drain

  val pressENTER = {
    for {
      _ <- IO.consoleForIO.println("Press ENTER to shutdown")
      _ <- IO.consoleForIO.readLine
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] = IO.race(program, pressENTER).map(_ => ()).as(ExitCode.Success)

}
