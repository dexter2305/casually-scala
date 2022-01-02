package v5

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.Future

trait InfraServiceComponent {

  val infraService: InfraService
  trait InfraService {

    def createTablesIfNotExists(): Future[Unit]

  }
}

trait InfraServiceComponentWithSlick extends InfraServiceComponent {
  requires: UserRepositoryComponentWithSlick 
    with SlickJdbcProfileComponent 
    with SlickDbComponent 
    with LoggingComponentWithSlf4j =>

  import jdbcProfile.api._

  class InfraServiceWithSlick extends InfraService {
    override def createTablesIfNotExists(): Future[Unit] = db.run(users.schema.createIfNotExists)
    
  }

}
