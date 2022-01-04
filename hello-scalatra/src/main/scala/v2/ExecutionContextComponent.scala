package v2

import scala.concurrent.ExecutionContext

trait ExecutionContextComponent {
  val appExecContext: ExecutionContext 
}
