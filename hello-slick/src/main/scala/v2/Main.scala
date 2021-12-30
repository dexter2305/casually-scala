package v2

import slick.basic.DatabaseConfig
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile

// ** approach dropped ** 
// pros-cons
// a - got stuck with exposing the TableQuery instance with Companion. 
// b + db is managed at the end of the world
// c - Gets complicated with multiple tables to work together like joins 

object Main {
  def main(args: Array[String]): Unit = {
    val logger = LoggerFactory.getLogger("two")
    val config = DatabaseConfig.forConfig[PostgresProfile]("pg_config")
    logger.info(s"$config")
    val schemas = SlickSchemas(config)
    config.db.close()
  }
}

