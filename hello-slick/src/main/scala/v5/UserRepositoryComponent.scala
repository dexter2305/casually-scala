package v5
import domain._
import slick.lifted.ProvenShape
import scala.concurrent.Future

trait UserRepositoryComponent {

  val userRepository: UserRepository

  trait UserRepository {

    def create(user: User): Future[Long]
    def findAll(): Future[Seq[User]]
    def findById(id: Long): Future[Option[User]]
    def deleteById(id: Long): Future[Int]
    def update(user: User): Future[Int]

  }
}

trait UserRepositoryComponentWithSlick extends UserRepositoryComponent { self: SlickJdbcProfileComponent with SlickDbComponent =>

  import domain._
  import jdbcProfile.api._

  class UserTable(tag: Tag) extends Table[User](tag, "users") {

    def id   = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[UserTable]

  class UserRepositoryWithSlick extends UserRepository {

    import scala.concurrent._
    import scala.concurrent.duration._

    override def create(user: User): Future[Long] = {
      val query = users returning users.map(_.id) += user
      db.run(query)
      
    }

    override def findAll(): Future[Seq[User]] = db.run(users.result)

    override def findById(id: Long): Future[Option[User]] = db.run(users.filter(_.id === id).result.headOption)

    override def deleteById(id: Long): Future[Int] = db.run(users.filter(_.id === id).delete)

    override def update(user: User): Future[Int] = db.run(users.filter(_.id === user.id).update(user))

  }

}
