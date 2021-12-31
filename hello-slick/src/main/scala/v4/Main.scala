package v4
import org.slf4j.LoggerFactory
import scala.util._
import slick.jdbc.PostgresProfile
import slick.jdbc.JdbcBackend
import slick.basic.DatabaseConfig

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

object Main {

  val logger = LoggerFactory.getLogger("")
  import logger._
  def main(args: Array[String]): Unit = {

    Try(DatabaseConfig.forConfig[PostgresProfile]("pg_config")) match {

      case Failure(exception) => error(exception.getMessage())
      case Success(config)    =>
        info(s"slick profile : ${config.profileName}")
      //run(new DataAccessLayer(config.profile), config.db)
    }
  }

  def run(dal: DataAccessLayer, db: JdbcBackend#DatabaseDef) = {

    import dal.profile.api._
    import scala.concurrent.ExecutionContext.Implicits.global
    import domain._

    val dbio = for {

      _      <- dal.createIfNotExists()
      id     <- dal.albums += Album(id = None, artist = "Maroon 5", title = "Girls like you")
      //id     <- dal.create(Album(id = None, artist = "Jesse Glynne", title = "I will be there"))
      albums <- dal.albums.result
      _ = albums.foreach(println(_))

    } yield albums

    val f = db.run(dbio).andThen(_ => db.close())
    Await.result(f, Duration.Inf)

  }

}
