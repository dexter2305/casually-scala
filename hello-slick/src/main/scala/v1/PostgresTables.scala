package v1
import slick.jdbc.PostgresProfile.api._
import domain._
import slick.lifted.ProvenShape

object PostgresTables {

  class AlbumTable(tag: Tag) extends Table[Album](tag, "albums") {
    def id    = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def artist = column[String]("artist")
    def year = column[Int]("year")
    override def * : ProvenShape[Album] = (id, title, artist, year) <> (Album.tupled, Album.unapply)
  }
  lazy val albums = TableQuery[AlbumTable]
}
