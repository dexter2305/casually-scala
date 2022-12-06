import org.typelevel.log4cats.LoggerFactory
import cats.Monad
import cats.implicits._
import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import org.typelevel.log4cats.slf4j.Slf4jFactory

object App extends IOApp {

  // any api that requires Logging.
  def compute[F[_]: LoggerFactory: Monad](args: String): F[Unit] = {
    val logger = LoggerFactory[F].getLogger
    for {
      _ <- logger.info(s"About to log")
      _ <- logger.info(s"args passed: $args")
    } yield ()
  }

  override def run(args: List[String]): IO[ExitCode] = {
    implicit val logfactory = Slf4jFactory[IO]
    (compute[IO]("Hello log4 cats") *> ComputeService[IO].compute("test")).as(ExitCode.Success)

  }

  class ComputeService[F[_]: LoggerFactory: Monad] private {
    val logger = LoggerFactory[F].getLogger

    def compute(t: String): F[Unit] = for {
      _ <- logger.info("start")
    } yield ()
  }
  object ComputeService                                    {

    def apply[F[_]: LoggerFactory: Monad] = new ComputeService[F]
  }

}
