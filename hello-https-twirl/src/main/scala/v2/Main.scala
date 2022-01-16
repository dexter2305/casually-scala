package v2

import org.http4s.blaze.server.BlazeServerBuilder
import cats.effect.IO
import cats.effect.IOApp
import cats.effect.ExitCode

object Main extends IOApp {

  import webroutes._

  val server: IO[Unit] = BlazeServerBuilder[IO]
    .bindHttp(8080, "localhost")
    .withHttpApp(routes[IO]())
    .serve
    .compile
    .drain

  val shutdown = for {
    - <- IO.consoleForIO.println("press Enter to kill")
    _ <- IO.consoleForIO.readLine
  } yield ()

  override def run(args: List[String]): IO[ExitCode] = IO.race(server, shutdown).map(_ => ()).as(ExitCode.Success)

}
