package v2

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile
import slick.lifted.ProvenShape


class SlickSchemas[P <: JdbcProfile] private (val x: DatabaseConfig[P]) {

  import x.profile.api._
  import domain._

  class UserTable(tag: Tag) extends Table[User](tag, "user") {

    def id                             = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name                           = column[String]("name", O.Length(50))
    override def * : ProvenShape[User] = (id, name) <> (User.tupled, User.unapply)

  }
  lazy val users = TableQuery[UserTable]
}


object SlickSchemas                                                     {

  def apply[P <: JdbcProfile](profile: DatabaseConfig[P]) = new SlickSchemas(profile)

}
