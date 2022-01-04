package v5

import slick.jdbc.JdbcProfile
import slick.jdbc.JdbcBackend

trait ComponentRegistry extends UserServiceComponent
                  with InfraServiceComponentWithSlick
                  with UserRepositoryComponentWithSlick 
                  with SlickJdbcProfileComponent 
                  with SlickDbComponent
                  with LoggingComponentWithSlf4j {
 
  override val userService: UserService = new UserService

  override val userRepository: UserRepository = new UserRepositoryWithSlick

  override val infraService = new InfraServiceWithSlick
}

trait ProductionApp extends ComponentRegistry