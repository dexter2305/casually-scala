package v5

trait UserServiceComponent { requires: UserRepositoryComponent =>

  import domain._

  val userService: UserService

  class UserService {

    def create(user: User) = userRepository.create(user)

    def findAll() = userRepository.findAll()

  }

}
