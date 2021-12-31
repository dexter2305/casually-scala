package v4

import slick.jdbc.JdbcBackend

class InfrastructureService(val dal: DataAccessLayer) {

  import dal.profile.api._

  def createTables(): DBIOAction[Unit, NoStream, Effect.Schema] = dal.albums.schema.createIfNotExists
  
}
