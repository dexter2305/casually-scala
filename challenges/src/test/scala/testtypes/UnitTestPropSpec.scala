package testtypes

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.matchers._
import org.scalatest.propspec.AnyPropSpec
import org.scalatest.time.Millis
import org.scalatest.time.Span
import org.scalatest.prop.Configuration

abstract class UnitTestPropSpec(component: String) extends AnyPropSpec with must.Matchers with TimeLimitedTests {

  def this() = {
    this("")
  }

  override def timeLimit: Span = Span(700, Millis)
}
