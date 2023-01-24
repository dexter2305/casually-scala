import cats._, cats.data._, cats.implicits._, cats.effect._
import doobie._, doobie.implicits._
import cats.effect.unsafe.implicits.global
import org.checkerframework.checker.units.qual.m

object DoobieApp extends IOApp.Simple {

  implicit val han = LogHandler.jdkLogHandler
  val driver       = "org.postgresql.Driver"
  val url          = "jdbc:postgresql:myimdb"
  val user         = "docker"
  val pass         = "docker"

  val xa: Transactor[IO] = Transactor.fromDriverManager[IO](driver, url, user, pass)
  val p1                 = 42.pure[ConnectionIO]
  val p2                 = sql"select 42".query[Int].unique
  val p3                 = sql"select name from actors".query[String].to[List]
  val p4                 = sql"select name from actors".query[String].stream.take(1).compile.toList

  val eow = for {
    // _      <- p1
    // _      <- p2
    actors <- p3
    _ = actors.sorted.foreach(println)
    actorsStream <- p4
    _ = actorsStream.foreach(println(_))
  } yield ()

  override def run: IO[Unit] = eow.transact(xa)

}
