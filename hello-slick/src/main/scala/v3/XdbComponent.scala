package v3

import slick.jdbc.JdbcProfile
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile
import slick.jdbc.PostgresProfile

// this could not be used as the trait is typed. 
// may be typed trait can be fixed but this is getting complicated for small use case. 
// dropping typed trait approach for DbComponent

trait XdbComponent[T <: JdbcProfile] {

  val config: DatabaseConfig[T]

  val profile = config.profile

  import profile.api._
  val db : Database = config.db

}

class PGXDBComponent extends XdbComponent[PostgresProfile] {

  override val config: DatabaseConfig[PostgresProfile] = DatabaseConfig.forConfig[PostgresProfile]("h2_mem")

}

class H2XDbComponent extends XdbComponent[H2Profile] {

  override val config: DatabaseConfig[H2Profile] = DatabaseConfig.forConfig[H2Profile]("h2")


}