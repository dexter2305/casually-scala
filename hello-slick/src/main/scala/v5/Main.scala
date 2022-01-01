package v5

import slick.jdbc.JdbcProfile

import slick.jdbc.JdbcBackend
import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile

object Main extends App {


  object UserManagementPostgresApp extends Application {

    lazy val config = DatabaseConfig.forConfig[PostgresProfile]("pg_config")

    override val jdbcProfile: JdbcProfile = config.profile

    override val db: JdbcBackend#DatabaseDef = config.db

  }

  import domain._
  UserManagementPostgresApp.infraService.createTablesIfNotExists()
  UserManagementPostgresApp.userService.create(User(Some(1), "Dexter"))
}