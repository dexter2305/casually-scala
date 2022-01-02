package v5

import scala.concurrent.Future

trait UserServiceComponent { requires: UserRepositoryComponent =>

  import domain._

  val userService: UserService

  class UserService {

    def create(user: User): Future[Long] = userRepository.create(user)

    def findAll(): Future[Seq[User]] = userRepository.findAll()

    def findById(id: Long): Future[Option[User]] = userRepository.findById(id)

    def update(user: User): Future[Int] = userRepository.update(user)

    def deleteById(id: Long): Future[Int] = userRepository.deleteById(id)

  }

}
