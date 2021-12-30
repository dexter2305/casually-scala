package v1

import slick.basic.DatabaseConfig
import slick.jdbc.PostgresProfile
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api._

import domain._
import PostgresTables._
import slick.jdbc.JdbcBackend
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import slick.jdbc.H2Profile

// ** exploring slick **

object Main extends App {

  val logger = LoggerFactory.getLogger(Main.getClass())

  val createDb = sqlu"""create database albums;"""

  val create = albums.schema.createIfNotExists

  val inserts = albums ++= Seq(
    Album(0, "recovery", "eminem", 2010),
    Album(0, "folklore", "taylor swift", 2020),
  )

  val selects = albums.result

  def exec[T](db: JdbcBackend#DatabaseDef, action: DBIO[T]): T = Await.result(db.run(action), Duration.Inf)


  

  // testing begins
  val h2config = DatabaseConfig.forConfig[H2Profile]("h2_mem_config")
  val pgConfig = DatabaseConfig.forConfig[PostgresProfile]("pg_config")

  logger.info(s"Slick profile(s) loaded")
  
  val config = h2config
  val dbFromConfig = config.db

  logger.info("using Db from DatabaseConfig.forConfig().db")
  //exec(dbFromConfig, createDb)
  exec(dbFromConfig, create)
  exec(dbFromConfig, inserts)
  val l1 = exec(dbFromConfig, selects)
  dbFromConfig.shutdown
  logger.info(s"n(albums): ${l1.size}")

  val db = Database.forConfig("pg_props")
  logger.info(s"using Db from Database.forConfig()")
  logger.info(s"${db.source.toString()}")
  exec(db, create)
  exec(db, inserts)
  val l2 = exec(db, selects)
  logger.info(s"n(albums): ${l2.size}")
  logger.info("completed")
  db.shutdown
}
