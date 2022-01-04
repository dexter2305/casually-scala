package v3

import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.FutureSupport
import org.json4s.Formats
import scala.concurrent.ExecutionContext
import org.json4s.DefaultFormats
import org.scalatra.Ok

class UserController(userRepository: UserRepository) extends ScalatraServlet with JacksonJsonSupport with FutureSupport {

  override implicit protected def jsonFormats: Formats = DefaultFormats.withBigDecimal

  override implicit protected def executor: ExecutionContext = ExecutionContext.Implicits.global

  get("/users")(Ok(userRepository.findAll()))

}
