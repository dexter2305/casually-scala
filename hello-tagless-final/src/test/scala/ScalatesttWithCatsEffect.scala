package learn

import cats.effect.IO
import cats.effect.kernel.Sync
import cats.effect.testing.scalatest.AsyncIOSpec
import org.scalacheck.Prop
import org.scalatest.matchers._
import org.scalatest._
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class DemoAsyncWordSpec extends wordspec.AsyncWordSpec with AsyncIOSpec {

  "Async test" must {
    "evaluate Future " in {

      scala.concurrent.Future(42).map(n => assert(n === 42))
    }
    "evaluate IO because of AsyncIOSpec" in {
      IO(42).asserting(r => assert(r == 42))
    }
    // "eval IO with properties" in ???

  }

}
object util {

  def stringReverser(string: String): String            = string.reverse
  def stringReverserF[F[_]: Sync](s: String): F[String] = Sync[F].delay(s.reverse)

}

// propspec

class SamplePropSpec extends propspec.AnyPropSpec with ScalaCheckPropertyChecks {

  import cats.effect.unsafe.implicits.global
  import org.scalacheck.Gen
  property("equi-length between original and reversed") {
    forAll { (s: String) =>
      assert(util.stringReverser(s).length() == s.length())
    }
  }

  property("F: equi-length between original and reversed") {
    forAll("s") { (s: String) =>
      util.stringReverserF[IO](s).map(e => assert(s.length == s.length())).unsafeRunSync()
    }
  }

}

// flatspecs

class SampleFlatSpec extends flatspec.AnyFlatSpec with ScalaCheckPropertyChecks with must.Matchers {

  behavior of "FlatSpec + Property based testing"
  "String reverser" must "satisfy consistency in length between original and reversed" in {
    forAll { (input: String) =>
      util.stringReverser(input).length() must be(input.length)
    }
  }
  it must "reverse(reverse(input)) must be input" in {
    forAll { (s: String) =>
      util.stringReverser(util.stringReverser(s)) must be(s)
    }
  }

}

class SampleAsyncFlatSpec extends flatspec.AsyncFlatSpec with AsyncIOSpec with ScalaCheckPropertyChecks {

  import util._

  "String reverser[F]" must "satisfy consistency with length between original and reversed" in {
    forAll { (s: String) =>
      val rio = stringReverserF[IO](s).map(r => assert(r.length() == s.length()))
      rio.unsafeRunSync()
    }
  }

}

// wordspec

class SampleWordSpec
    extends wordspec.AnyWordSpec
    with must.Matchers
    with org.scalatestplus.scalacheck.ScalaCheckPropertyChecks {

  import org.scalacheck.Prop._

  "String reverser: WordSpec + Property based testing" must {
    "satisfy consistency of length between original and reversed string" in {
      forAll { (s: String) =>
        util.stringReverser(s).length() must be(s.length())
      }
    }
    "reverse(reverse(input)) must be input" in {
      forAll { (s: String) =>
        util.stringReverser(util.stringReverser(s)) must be(s)
      }
    }
  }

}

class SampleAsyncWordSpec extends wordspec.AnyWordSpec with org.scalatestplus.scalacheck.ScalaCheckPropertyChecks {

  import util._
  import cats.effect.unsafe.implicits.global
  "String reverser[F] with property based tests" must {
    "satisfy consistency with length in original and reversed string" in {
      forAll { (s: String) =>
        val ioAssertion = stringReverserF[IO](s).map(r => assert(s.length() == r.length() ))
        ioAssertion.unsafeRunSync()
      }
    }
  }

}
