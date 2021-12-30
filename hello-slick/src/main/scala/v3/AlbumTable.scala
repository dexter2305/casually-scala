package v3

import slick.lifted.ProvenShape

trait AlbumTable { this: DBComponent =>

  import domain._
  import profile.api._

  class AlbumTable(tag: Tag) extends Table[Album](tag, "albums") {

    def id                              = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def artist                          = column[String]("artist")
    def title                           = column[String]("title")
    def year                            = column[Int]("year")
    override def * : ProvenShape[Album] = (id, artist, title, year) <> (Album.tupled, Album.unapply)

  }

  protected val albums = TableQuery[AlbumTable]

  protected val albumTableAutoInc = albums returning albums.map(_.id)

}
