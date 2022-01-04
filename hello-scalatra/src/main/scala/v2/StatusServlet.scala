package v2

import org.scalatra.ScalatraServlet
import org.scalatra.Ok

class StatusServlet extends ScalatraServlet {

  get("/") { Ok }

}
