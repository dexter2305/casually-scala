package  v1

import org.scalatra.LifeCycle
import resources.logger._
import javax.servlet.ServletContext


class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext): Unit = {

    val paramEnumations = context.getInitParameterNames()
    context.mount(new WelcomeController, "/welcome/*")
    context mount(new UserController, "/users/*")
    info(s"bootstrap completed")
  }

}