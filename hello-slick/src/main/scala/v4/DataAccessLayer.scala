package v4

import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend

class DataAccessLayer(val profile: JdbcProfile) extends AlbumComponent with JdbcProfileComponent {

  import profile.api._

  def createIfNotExists() = albums.schema.createIfNotExists
  
}
