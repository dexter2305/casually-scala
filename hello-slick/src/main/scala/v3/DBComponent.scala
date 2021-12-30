package v3

import slick.jdbc.JdbcProfile
import slick.basic.DatabaseConfig

trait DBComponent {

  val profile: JdbcProfile

  import profile.api._

  val db: Database

}
