package v4

trait AlbumComponent { self: JdbcProfileComponent =>
  import profile.api._
  import domain._

  class AlbumTable(tag: Tag) extends Table[Album](tag, "albums") {

    def id     = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def artist = column[String]("artist")
    def title  = column[String]("title")

    def * = (id, artist, title) <> (Album.tupled, Album.unapply)
  }

  val albums = TableQuery[AlbumTable]


  def create(album: Album) = albums += album
  def findAll() = albums.result

}
