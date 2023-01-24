import cats._, cats.implicits._, cats.data._, cats.effect._
import doobie._, doobie.implicits._
import cats.effect.unsafe.implicits.global

val driver = "org.postgresql.Driver"
val url    = "jdbc:postgresql:myimdb"
val user   = "docker"
val pass   = "docker"

val xa: Transactor[IO] = Transactor.fromDriverManager[IO](driver, url, user, pass)

val p1 = sql"select 42".query[Int].unique
p1.transact(xa).unsafeRunSync()
