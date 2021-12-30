package v3

import slick.jdbc.JdbcProfile

import slick.jdbc.JdbcBackend
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile

class H2DBComponent extends DBComponent {

  val config = DatabaseConfig.forConfig[H2Profile]("h2_mem_config")

  override val profile: JdbcProfile = config.profile

  override val db: JdbcBackend#DatabaseDef = config.db

}
