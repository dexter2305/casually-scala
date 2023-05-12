package testtypes

import org.scalatest.concurrent.TimeLimitedTests
import org.scalatest.matchers.must.Matchers
import org.scalatest.time.Millis
import org.scalatest.time.Span
import org.scalatest.wordspec.AnyWordSpec

abstract class UnitTestWordSpec extends AnyWordSpec with Matchers with TimeLimitedTests {

  override def timeLimit: Span = Span(700, Millis)
}
