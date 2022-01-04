package v2

import scala.concurrent.ExecutionContext

class HttpServiceRegistry(val appExecContext: ExecutionContext) extends UserHttpServiceComponent with ExecutionContextComponent

