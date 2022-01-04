package v2

import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import org.eclipse.jetty.util.component
import org.slf4j.LoggerFactory
import org.scalatra.servlet.ScalatraListener
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

trait ScalatraBootstrapComponent { requires: UserHttpServiceComponent =>

  val bootStrapName = new ScalatraBootstrap().getClass().getCanonicalName()
  class ScalatraBootstrap extends LifeCycle {

    override def init(context: ServletContext): Unit = {
      context.mount(new StatusServlet, "/status")
      context.mount(userHttpService, "/users/")
    }
  }
}

trait ScalatraWebContextComponent { requires: ScalatraBootstrapComponent => 

  lazy val serverContext = ScalatraWebContext.create()

  object ScalatraWebContext {
    def create(): WebAppContext = {
      val context = new WebAppContext
      context.setResourceBase("")
      context.setContextPath("/")
      context.setInitParameter(ScalatraListener.LifeCycleKey, bootStrapName)
      context.addEventListener(new ScalatraListener)
      context
    }
  }
}