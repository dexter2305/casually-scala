package v1

import scala.concurrent.Future
import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import cats.effect.kernel.Clock

object CatsWithFuture extends IOApp {

  object domain {
    case class User(id: Long, name: String)
  }

  object repository {
    import domain._
    import scala.collection.mutable.Map
    import scala.concurrent.ExecutionContext.Implicits.global

    val usersDb: Map[Int, User] = List("Naveen", "Dexter", "Nashter", "Ryuk", "Nightfury", "Toothless").zipWithIndex.map { case (name, id) =>
      (id -> User(id, name))
    }.foldLeft(Map.empty[Int, User]) { case (acc, row) => acc += row }

    def findAllUsers(): Future[Seq[User]] = ???

    def countUsers(): Future[Int] = Future {
      println("2. users-db: finding size of user db")
      Thread.sleep(5 * 1000)
      usersDb.size
    }

  }

  import repository._
  import domain._
  val program = for {
    _         <- IO.delay(println("1. Getting users count from db (it is a future)"))
    userCount <- IO.fromFuture(IO(countUsers()))
    dt        <- IO(java.time.LocalDateTime.now())
    _         <- IO(println(s"3. $dt - Number of users in db: $userCount"))
    _         <- IO(println(s"4. ======================================="))
  } yield userCount

  val pressENTER = for {
    _ <- IO.consoleForIO.println("Press ENTER to terminate")
    _ <- IO.consoleForIO.readLine

  } yield ()

  override def run(args: List[String]): IO[ExitCode] = IO
    .race(program.foreverM, pressENTER)
    .onCancel(IO(println(">> Program completed possibly !!")))
    .as(ExitCode.Success)
}
