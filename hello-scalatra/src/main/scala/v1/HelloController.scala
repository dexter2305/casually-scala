package v1

import org.scalatra.ScalatraServlet
import org.scalatra.Ok

class HelloController extends ScalatraServlet {



  get() {
    Ok { "hello" }
  }


}