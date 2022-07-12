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

  val server = new Server(8080)
  val context = new WebAppContext
  context.setContextPath("/")
  context.setResourceBase(".")
  context.setInitParameter(ScalatraListener.LifeCycleKey, "v1.ScalatraBootstrap")
  context.addEventListener(new ScalatraListener)
  server.setHandler(context)
  server.start()
  info(s"Press ENTER to shutdown")
  StdIn.readLine()
  info(s"Shutdown event received. Shutting down, gracefully !")
  server.stop()
}

object resources {

  lazy val logger = LoggerFactory.getLogger("v1 app")
  scribe.Logger.root.withMinimumLevel(Level.Trace).replace()

}
