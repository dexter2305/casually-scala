package v5
import domain._
import slick.lifted.ProvenShape

trait UserRepositoryComponent {

  val userRepository: UserRepository

  trait UserRepository {

    def create(user: User): Unit
    def findAll(): Unit
    def findById(id: Long): Unit
    def deleteById(id: Long): Unit
    def update(user: User): Unit

  }
}

trait UserRepositoryComponentWithSlick extends UserRepositoryComponent { self: SlickJdbcProfileComponent with SlickDbComponent =>

  import domain._
  import jdbcProfile.api._

  class UserTable(tag: Tag) extends Table[User](tag, "users") {

    def id   = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")

    def * = (id, name) <> (User.tupled, User.unapply)
  }

  val users = TableQuery[UserTable]

  class UserRepositoryWithSlick extends UserRepository {


    import scala.concurrent._
    import scala.concurrent.duration._

    override def create(user: User): Unit = {
      val f = db.run(users += user)
      Await.result(f, Duration.Inf)
    }

    override def findAll(): Unit = db.run(users.result)

    override def findById(id: Long): Unit = ???

    override def deleteById(id: Long): Unit = ???

    override def update(user: User): Unit = ???

  }

}
