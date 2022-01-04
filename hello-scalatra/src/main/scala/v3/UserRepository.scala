package v3
import domain._
import scala.concurrent.Future

trait UserRepository {
  def create(user: User): Future[Long]
  def findAll(): Future[Seq[User]]
  def findById(id: Long): Future[Option[User]]
  def delete(id: Long): Future[Unit]
  def update(user: User): Future[Unit]
}

class UserRepositoryInMemory extends UserRepository {

  override def create(user: User): Future[Long] = ???

  override def findAll(): Future[Seq[User]] = ???

  override def findById(id: Long): Future[Option[User]] = ???

  override def delete(id: Long): Future[Unit] = ???

  override def update(user: User): Future[Unit] = ???

}
