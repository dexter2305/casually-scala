package v1

import org.slf4j.LoggerFactory
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener
import org.eclipse.jetty.server.Server
import org.slf4j.Logger
import scala.io.StdIn
import scribe.Level

object Main extends App {

  import resources.logger._

  val context = new WebAppContext
  context.setResourceBase("src/main/webapp/WEB-INF")
  context.setContextPath("/")
  context.setInitParameter("org.scalatra.LifeCycle", "v1.ScalatraBootstrap")
  context.addEventListener(new ScalatraListener)
  context.setInitParameter("APPLICATION-TEXT", "SOME-RANDOM-VALUE")
  val server  = new Server(8080)
  info(s"starting server on 8080")
  server.start()
  info(s"Press any key to shutdown")
  StdIn.readLine()
  server.stop()
  info(s"Server stopped ..")
}

object resources {

  lazy val logger = LoggerFactory.getLogger("v1 app")
  scribe.Logger.root.withMinimumLevel(Level.Trace).replace()

}
