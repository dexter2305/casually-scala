package v4

import slick.jdbc.JdbcBackend
import org.slf4j.LoggerFactory

class AlbumService(val dal: DataAccessLayer) {
  import domain._
  import dal.profile.api._
  import dal.albums
  import scala.concurrent.ExecutionContext.Implicits.global

  def loadTestData() = {
    val a1                 = Album(None, "Imagine Dragons", "Believer")
    val a2                 = Album(None, "Maroons", "Memories")
    val a3                 = Album(None, "Ed Sheeran", "Perfect")
    val a4                 = Album(None, "Aurora", "Runaway")
    val a5                 = Album(None, "Tones and I ", "Dance Monkey")
    val a6                 = Album(None, "Bars and Melody", "Hope")
    val loadTestDataAction = dal.albums ++= Seq(a1, a2, a3, a4, a5, a6)
  }

  def add(album: Album) = DBIO.seq(albums += album)

  def findAll() = DBIO.seq(albums.result)

  def findById(id: Long) = albums.filter(_.id === id)

  def deleteById(id: Long) = ???

  def update(album: Album) = ???

}
