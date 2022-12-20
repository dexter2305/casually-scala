package rtjvm

import cats._
import cats.effect._
import cats.implicits._
import org.http4s.circe._
import org.http4s._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.dsl._
import org.http4s.dsl.impl._
import org.http4s.headers._
import org.http4s.implicits._
import org.http4s.server._
import org.http4s.blaze.server.BlazeServerBuilder
import org.typelevel.ci.CIString

import java.time.Year
import java.util.UUID
import scala.collection.mutable
import scala.util.Try


object Http4sTutorial extends IOApp {
  /*
    requirements:
      - GET all movies for a given director optionally under a given year
        - GET /movies?director=Night%20Shyamalan&year=2003
      - GET all actors of a movie
      - POST (add) a new director

    tech stack:
      - http4s
      - circe
      - cats-effect
      - cats library
      - blaze-server
   */

  /*
    - reference:
        - Request => F[Response] ||| HttpRoutes[F]
   */
  override def run(args: List[String]): IO[ExitCode] = {

    val Apis = Router(
      "/api"      -> movieRoutes[IO],
      "api/admin" -> directorRoutes[IO],
    )

    val server = BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(allRoutesComplete) // alternative: 'Apis' constructed by Router
      .serve
      .compile
      .drain

    val stopServer = for {
      _ <- IO.consoleForIO.println("Press ENTER to shutdown")
      _ <- IO.consoleForIO.readLine
    } yield ()

    val app = IO.race(server, stopServer).onCancel(IO.consoleForIO.println("Server shutdown complete"))

    app.as(ExitCode.Success)
  }

  object domain {
    type Actor = String
    case class Movie(id: String, name: String, actors: List[Actor], director: String, year: Int)
    case class Director(firstname: String, lastname: String) {
      override def toString = s"$firstname $lastname"
    }
    case class DirectorDetails(firstname: String, lastname: String, genre: String)
  }

  import domain._
  import repository._
  //Decoder for year without validation is replaced by Decoder with validator
  //implicit val yearQueryParamDecoder: QueryParamDecoder[Year] = QueryParamDecoder[Int].map(yearAsInt => Year.of(yearAsInt))

  //codecs used for native types
  implicit val yearQueryParamValidatedDecoder: QueryParamDecoder[Year] = QueryParamDecoder[Int].emap { yearAsInt =>
    Try(Year.of(yearAsInt)).toEither
      .leftMap(e => ParseFailure(e.getMessage(), e.getCause().getMessage()))
  }
  object OptionalYearQueryParamMatcher      extends OptionalQueryParamDecoderMatcher[Year]("year")
  object OptionYearValidatedQueryParamMatch extends OptionalValidatingQueryParamDecoderMatcher[Year]("year")
  object DirectorQueryParamMatcher          extends QueryParamDecoderMatcher[String]("director")
  object DirectorPathExtractor {
    def unapply(name: String): Option[Director] =
      Try {
        val tokens = name.split(" ")
        Director(tokens(0), tokens(1))
      }.toOption

  }

  // routes
  def movieRoutes[F[_]: Monad] = {
    val dsl = Http4sDsl[F]
    import dsl._
    import java.time.Year

    HttpRoutes.of[F] {
      case GET -> Root / "movies" :? DirectorQueryParamMatcher(director) +& OptionYearValidatedQueryParamMatch(optionalYear) =>
        val moviesByDirector = findMoviesByDirector(directorName = director)
        optionalYear match {
          case Some(validatedYear) =>
            validatedYear.fold(
              _ => BadRequest("Error parsing year"),
              year => Ok(moviesByDirector.filter(_.year == year.getValue).asJson),
            )
          case None                => Ok(moviesByDirector.asJson)
        }

      case GET -> Root / "movies" / UUIDVar(movieId) / "actors" =>
        findMoviesById(movieId.toString()).map(_.actors) match {
          case Some(actors) => Ok(actors.asJson)
          case None         => NotFound(s"Movie id '$movieId' not found.")
        }

    }
  }

  def directorRoutes[F[_]: Concurrent] = {
    val dsl                                                   = Http4sDsl[F]
    import dsl._
    implicit val directorDecorder: EntityDecoder[F, Director] = jsonOf[F, Director]
    
    
    HttpRoutes.of[F] {

      case GET -> Root / "directors" / DirectorPathExtractor(director) =>
        directorDb.get(director) match {
          case None                => NotFound(s"Director '$director' not found.")
          case Some(directorValue) => Ok(directorValue.asJson)
        }


      case GET -> Root / "directors"                                   => {
        println(s"invoked get directors")
        Ok(directorDb.keySet.map(_.toString()).asJson)
      }


      case req @ POST -> Root / "directors"                            =>
        for {
          director <- req.as[Director]
          // _ = IO.consoleForIO.println(s"received $director")
          res <- Ok()
        } yield res
    }
    
  }

  def allRoutes[F[_]: Concurrent]: HttpRoutes[F] = movieRoutes[F] <+> directorRoutes[F]

  def allRoutesComplete[F[_]: Concurrent]: HttpApp[F] = allRoutes[F].orNotFound

  object repository {

    import domain._

    val sampleMovies: Seq[Movie] = Seq(
      Movie("0", "Iron Man", List("Robert Downey Jr", "Gwyneth Paltrow", "Jeff Bridges"), "Jon Favreau", 2008),
      Movie("1", "Village", List("Bryce Dallas Howard", "Joaquin Phoenix"), "Night Shyamalan", 2004),
      Movie("2", "Joker", List("Joaquin Phoenix"), "Todd Philips", 2019),
    )

    val directorDb: Map[Director, DirectorDetails] = Map(Director("Night", "Shyamalan") -> DirectorDetails("Night", "Shyamalan", "thriller"))

    val moviesDb: Map[String, Movie] = sampleMovies.map(m => (m.id -> m)).toMap

    def findMoviesById(id: String) = moviesDb.get(id)

    def findMoviesByDirector(directorName: String): List[Movie] = moviesDb.values.filter(mv => mv.director == directorName).toList
  }

}
