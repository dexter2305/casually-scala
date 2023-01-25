package object exploreWithTapir {

  object domain {
    case class User(id: Int, name: String)
  }

  object endpoints {
    import sttp.tapir._
    import sttp.tapir.json.circe._
    import sttp.tapir.generic.auto._
    import io.circe.generic.auto._
    import domain._

    object UserEndpoints {
      private lazy val userEndpoint = infallibleEndpoint.in("users")

      val userPost =
        userEndpoint.post
          .in(jsonBody[User])
          .errorOut(stringBody)
          .out(stringBody)

      val userFind =
        userEndpoint.get
          .in(path[Int]("id"))
          .errorOut(stringBody)
          .out(jsonBody[Option[User]])

      val combined = Seq(userPost, userFind)
    }

  }

  object routes {

    import cats._, cats.implicits._, cats.effect._
    class UserRoute[F[_]: Async] private {
      import org.http4s._
      import domain._, endpoints._
      import sttp.tapir.server.http4s.Http4sServerInterpreter

      def userPost: HttpRoutes[F] =
        Http4sServerInterpreter[F]()
          .toRoutes(UserEndpoints.userPost.serverLogic[F] { user =>
            s"$user added".asRight[String].pure[F]
          })

      def userFind = Http4sServerInterpreter[F]().toRoutes(UserEndpoints.userFind.serverLogic[F] { id =>
        if (id % 3 == 0) {
          s"Id: $id not found".asLeft[Option[User]].pure[F]
        } else {
          User(id, "Name").some.asRight[String].pure[F]
        }
      })

      def combined = userPost <+> userFind
    }
    object UserRoute                     {
      def routes[F[_]: Async] = new UserRoute[F]().combined
    }

    object DocRoute {
      import sttp.tapir.server.http4s.Http4sServerInterpreter
      import sttp.tapir.swagger.bundle.SwaggerInterpreter
      import cats.effect._
      def route[F[_]: Async]() = Http4sServerInterpreter[F]().toRoutes(
        SwaggerInterpreter.apply().fromEndpoints[F](endpoints.UserEndpoints.combined.toList, "Playing with Tapir", "1.0"),
      )
    }

    def combined[F[_]: Async] = UserRoute.routes[F] <+> DocRoute.route[F]()
  }

  object server {
    import cats.effect._
    import org.http4s.blaze.server._
    def makeServer[F[_]: Async] =
      BlazeServerBuilder[F]
        .bindHttp(9090, "0.0.0.0")
        .withHttpApp(routes.combined[F].orNotFound)
        .resource
  }
}

import exploreWithTapir._
import cats.effect._
object TapirApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    server
      .makeServer[IO]
      .use { _ =>
        IO {
          println("Press Enter to terminate")
          scala.io.StdIn.readLine()
        }.as(ExitCode.Success)
      }
}
