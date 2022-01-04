package v2

import scala.concurrent.ExecutionContext
import org.eclipse.jetty.server.Server
import org.slf4j.LoggerFactory
import scala.io.StdIn

object Main extends App {

  Application.start()

}

object Application extends HttpServiceContext {

  val logger                                    = LoggerFactory.getLogger(this.getClass())
  import logger._
  override val appExecContext: ExecutionContext = ExecutionContext.Implicits.global

  def start(): Unit = {
    val server = new Server(8080)
    server.setHandler(serverContext)
    server.start()
    info(s"Press ENTER to shutdown")
    StdIn.readLine()
    info(s"Shutdown event received. Shutting down.")
    server.stop()

  }

}
