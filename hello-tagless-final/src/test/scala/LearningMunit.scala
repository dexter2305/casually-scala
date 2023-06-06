package lm

import munit._
import org.scalacheck.Prop._

class IntegerSuite2 extends ScalaCheckSuite {

  property("integer identities") {
    forAll { (n: Int) =>
      assertEquals(n + 0, n)
      assertEquals(n * 1, n)
    }
  }

}
import cats.effect.{IO, SyncIO}
import munit.CatsEffectSuite
class ExampleSuite extends CatsEffectSuite {

  test("tests can return IO[Unit] with assertions expressed via a map") {
    IO(42).map(it => assertEquals(it, 42))
  }

  test("alternatively, assertions can be written via assertIO") {
    assertIO(IO(42), 42)
  }

  test("or via assertEquals syntax") {
    IO(42).assertEquals(42)
  }

  test("or via plain assert syntax on IO[Boolean]") {
    IO(true).assert
  }

  test("SyncIO works too") {
    SyncIO(42).assertEquals(42)
  }

  import cats.effect.std.Dispatcher

  val dispatcher = ResourceFixture(Dispatcher.parallel[IO])

  dispatcher.test("resources can be lifted to munit fixtures") { dsp =>
    dsp.unsafeRunAndForget(IO(42))
  }

}

object util {

  import cats.effect.Sync
  import cats.implicits._

  def reverse[F[_]: Sync](s: String): F[String] = Sync[F].delay(s.reverse)

}

class Demo extends CatsEffectSuite with ScalaCheckSuite {

  import util._

  test("reverse(reverse(input)) == input ") {
    val ioResult = reverse[IO]("hello").flatMap(s => reverse[IO](s))
    assertIO(ioResult, "hell1")
  }

  test("reverse should reverse the characters of a string") {
    val input          = "Hello, World!"
    val expectedOutput = "!dlroW ,olleH"

    val result = util.reverse[IO](input).unsafeToFuture()

    result.map { actualOutput =>
      assertEquals(actualOutput, expectedOutput)
    }
  }

  property("simple test with properties") {
    forAll { (n: Int) =>
      assertEquals(n, n)
    }
  }

  // property("reverser[F] should: reverse(reverse(input)) == input") {
  //   forAll { (input: String) =>
  //     reverse[IO](input).flatMap(reverse[IO] _).assertEquals(input)
  //   }
  // }
}

class UtilSuite extends munit.CatsEffectSuite with ScalaCheckEffectSuite {

  import util._
  import org.scalacheck.effect.PropF._

  test("Munit for IO") {
    IO(42).assertEquals(42) // fails as expected
  }

  test("Munit for effectful api") {

    val p = forAllF { (input: String) =>
      val ioString: IO[String] = reverse[IO](input).flatMap(reverse[IO] _)
      ioString.assertEquals(input) // assert for the PropF
    }.check()

    p.map(result => assertEquals(result.passed, true)) // assert for CatsEffectSuite. 
    //todo: Multiple assert statements is overwhelming
  }


  test ("Munit for effectful api") {
    forAllF { (input: String) => 
      reverse[IO](input).flatMap(r => reverse[IO](r)).map(result => assertEquals(result, input))
    }
  }
  test("first PropF test") {
    forAllF { (x: Int) =>
      IO(x).start.flatMap(_.joinWithNever).map(res => assert(res == x))
    }
  }

}
