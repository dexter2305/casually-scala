package v1

import org.scalatra.ScalatraServlet
import org.scalatra.Ok

class WelcomeController extends ScalatraServlet {

  get("/:name") {
    val name = params.get("name")
    Ok(s"Welcome, $name")
  }

}
