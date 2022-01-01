package v5

import org.slf4j.LoggerFactory
import org.slf4j.Logger

trait LoggingComponentWithSlf4j {

  val logger: Logger = LoggerFactory.getLogger("")
  
}