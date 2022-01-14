package v2

import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import org.http4s.blaze.server.BlazeServerBuilder

object Main extends IOApp {

  import routes._

  val alwaysOnProgram = BlazeServerBuilder[IO]
    .bindHttp(8080, "localhost")
    .withHttpApp(allRoutesComplete)
    .serve
    .compile
    .drain
    .map(_ => IO.never)

  val pressENTER = IO.consoleForIO.println("Press ENTER to shutdown").flatMap(_ => IO.consoleForIO.readLine)

  override def run(args: List[String]): IO[ExitCode] = IO.race(alwaysOnProgram, pressENTER).map(_ => ExitCode.Success)

}
