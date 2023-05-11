package basetypes

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._
import org.scalatest.time.Millis
import org.scalatest.time.Millisecond
import org.scalatest.time.Span

abstract class UnitTestFlatSpec(component: String) extends AnyFlatSpec with must.Matchers with TimeLimitedTests {

  def timeLimit: Span = Span(700, Millis)

  def this() = {
    this("")
  }
}
