package basetypes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers

abstract class UnitTest(component: String) extends AnyFlatSpec with Matchers {
  def this() = {
    this("")
  }
}
