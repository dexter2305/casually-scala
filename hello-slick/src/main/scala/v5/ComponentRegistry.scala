package v5

import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend

object ComponentRegistry extends UserServiceComponent with UserRepositoryComponentWithSlick with SlickJdbcProfileComponent with SlickDbComponent {

  override val db: JdbcBackend#DatabaseDef = ???

  override val jdbcProfile: JdbcProfile = ???

  override val userRepository: UserRepository = new UserRepositoryWithSlick()

  override val userService: UserService = new UserService()

}

trait Application extends UserServiceComponent
                  with InfraServiceComponentWithSlick
                  with UserRepositoryComponentWithSlick 
                  with SlickJdbcProfileComponent 
                  with SlickDbComponent
                  with LoggingComponentWithSlf4j {
 
  override val userService: UserService = new UserService

  override val userRepository: UserRepository = new UserRepositoryWithSlick

  override val infraService = new InfraServiceWithSlick
}
