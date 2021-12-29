package two

import slick.basic.DatabaseConfig
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile

object Main {
  def main(args: Array[String]): Unit = {
    val logger = LoggerFactory.getLogger("two")
    val config = DatabaseConfig.forConfig[PostgresProfile]("pg_config")
    logger.info(s"$config")
    val schemas = SlickSchemas(config)
  
  }
}

