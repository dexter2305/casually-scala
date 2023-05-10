import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalatest.matchers.must.Matchers
import org.http4s.Request
import org.http4s.Method
import org.http4s.Uri
import v2.helloroute._
import cats.effect.IO
import org.http4s.Response
import cats.effect.unsafe.implicits.global
import org.scalactic.anyvals.PosInt


class HelloRouteSpec extends AnyPropSpec with Matchers with ScalaCheckPropertyChecks {

  override def minSuccessful(value: PosInt): MinSuccessful = MinSuccessful(PosInt(1000))

  property("on '/hello' => return 200, say welcome") {
    forAll { s: String =>
      {
        val hello: Request[IO]     = Request(Method.GET, Uri.unsafeFromString("/hello"))
        val response: Response[IO] = helloRoutes[IO]().orNotFound.run(hello).unsafeRunSync()
        response.status.code must be(200)
        response.as[String].unsafeRunSync() must be("Welcome")
      }
    }
  }
  property("on '/hello/$name' => return 200 with 'hello $name' ") {
    forAll { name: String =>
      {
        val request: Request[IO]   = Request(Method.GET, Uri.unsafeFromString(s"/hello/${Uri.pathEncode(name)}"))
        val response: Response[IO] = helloRoutes[IO]().orNotFound.run(request).unsafeRunSync()
        response.status.code must be(200)
        response.as[String].unsafeRunSync() must be(s"Hello $name")
      }
    }
  }
}
