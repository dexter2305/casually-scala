package v5

import slick.jdbc.JdbcProfile

import slick.jdbc.JdbcBackend
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main extends App {

  object UserManagementPostgresApp extends ProductionApp {

    lazy val config = DatabaseConfig.forConfig[PostgresProfile]("pg_config")

    override val jdbcProfile: JdbcProfile = config.profile

    override val db: JdbcBackend#DatabaseDef = config.db

  }

  import domain._
  import scala.concurrent.ExecutionContext.Implicits.global

  // sample invokes
  val all = for {
    _                    <- UserManagementPostgresApp.infraService.createTablesIfNotExists()
    id                   <- UserManagementPostgresApp.userService.create(User(name = "naveen"))
    foundUser            <- UserManagementPostgresApp.userService.findById(id)
    allUsers             <- UserManagementPostgresApp.userService.findAll()
    _                    <- UserManagementPostgresApp.userService.deleteById(id)
    foundUserAfterDelete <- UserManagementPostgresApp.userService.findById(id)

  } yield (id, foundUser, foundUserAfterDelete, allUsers)

  // sample tests
  all onComplete {
    case Failure(exception)                                                   => println(s"error: $exception")
    case Success((id, foundUser, foundUserAfterDelete, allUsersBeforeDelete)) =>
      assert(id > 0, "Minumum sequence is > 0")
      foundUser match {
        case None        => assert(false, "Just inserted user missing in by 'findById'")
        case Some(value) => assert(value.name == "naveen", "Test user name is mismatched")
      }
      allUsersBeforeDelete.find(u => u.name == "naveen" && u.id == id) match {
        case None => assert(false, s"User just inserted is missing in list ${allUsersBeforeDelete.map(_.name).mkString("-")}")
        case Some(value) => assert(true)
      }
      foundUserAfterDelete match {
        case None => assert(true, "User not expected after delete.")
        case Some(value) => assert(false, s"User not expcted after delete but found $value")
      }
  }
  Await.result(all, Duration.Inf)

}
