package v1

import org.scalatra.ScalatraServlet
import org.scalatra.Ok
import scala.annotation.meta.param
import org.scalatra.NotFound
import scala.concurrent.Future
import org.scalatra.FutureSupport
import scala.concurrent.ExecutionContext
// JSON-related libraries
import org.json4s.{DefaultFormats, Formats}

// JSON handling support from Scalatra
import org.scalatra.json._
import org.scalatra.Created

class UserController extends ScalatraServlet with FutureSupport with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal
  override implicit protected def executor: ExecutionContext = ExecutionContext.global


  import domain._

  before() {
    contentType = "application/json"
  }  

  get("/") {
    Thread.sleep(1000 * 3)
    Ok(UserData.all)

  }

  get("/:id") {
    UserData.all.find(_.id == params("id").toLong) match {
      case None       => NotFound(s"User id: ${params("id")}")
      case Some(user) => Ok(user)
    }
  }

  delete("/:id") {
    val id = params("id").toLong
    UserData.all.find(_.id == id) match {
      case None        => NotFound(s"User id: $id")
      case Some(value) => UserData.all = UserData.all.filter((_.id != id))
    }
  }

  post("/"){
    val u = parsedBody.extract[User]
    println(s"$u")
    Created(u)
  }

}
