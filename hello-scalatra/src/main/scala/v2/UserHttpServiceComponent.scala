package v2

import org.scalatra.json.JacksonJsonSupport
import org.scalatra.FutureSupport
import org.scalatra.ScalatraServlet
import org.json4s.Formats
import scala.concurrent.ExecutionContext
import org.scalatra.Ok
import org.scalatra.Created
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default
import org.json4s.DefaultFormats

trait UserHttpServiceComponent { requires: ExecutionContextComponent => 


  val userHttpService: UserHttpService = new UserHttpService

  class UserHttpService extends ScalatraServlet with JacksonJsonSupport with FutureSupport {

    override implicit protected def jsonFormats: Formats = DefaultFormats.withBigDecimal

    override implicit protected def executor: ExecutionContext = appExecContext

    get("/users") { Ok("???") }
    get("/users/:id") { Ok(s"${params("id")} ???") }
    post("/users") { Created("???")}
  }

}
