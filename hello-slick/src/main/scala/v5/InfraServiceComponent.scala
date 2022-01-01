package v5

import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait InfraServiceComponent {

  val infraService: InfraService
  trait InfraService {

    def createTablesIfNotExists(): Unit

  }
}

trait InfraServiceComponentWithSlick extends InfraServiceComponent {
  requires: UserRepositoryComponentWithSlick 
    with SlickJdbcProfileComponent 
    with SlickDbComponent 
    with LoggingComponentWithSlf4j =>

  import jdbcProfile.api._

  class InfraServiceWithSlick extends InfraService {

    override def createTablesIfNotExists(): Unit = {
      logger.info(s"creating schemas")
      Await.result(db.run(users.schema.createIfNotExists), Duration.Inf)
      logger.info(s"schemas created")
    }

  }

}
