import scala.concurrent.duration._

import cats.effect.IO
import cats.effect.kernel.Temporal
import cats.effect.unsafe.implicits.global
import cats.effect.IOApp
import fs2.Stream
object SchedulingWithFS2_Demo extends IOApp.Simple {

  // Output is Unit: Means this stream sleep for 1.second and emit Unit
  val sleepyStream: Stream[IO, Unit] = Stream.sleep[IO](1.second)

  // Output is Nothing. Means this stream sleep for 1.second but emit "Nothing" (literally). Wow, expressiveness !
  val sleepyStream_v2: Stream[IO, Nothing] = Stream.sleep[IO](1.second).drain

  val helloStream: Stream[IO, Unit] = Stream.eval(IO.consoleForIO.println("Hello, there"))

  val sleepyHelloRepeatedStream: Stream[IO, Unit]    = (sleepyStream ++ helloStream).repeat
  val sleepyHelloRepeatedStream_v2: Stream[IO, Unit] = (sleepyStream_v2.drain ++ helloStream).repeat

  val evalWithAwakeEvery          = Stream.awakeEvery[IO](2.second).repeat.take(3)
  val evalEagerEvalWithAwakeEvery = Stream.awakeEvery[IO](1.second).scan(1)((acc, f) => acc + 1).repeat
  override def run: IO[Unit] = {
    val prog = for {
      list <- evalWithAwakeEvery.compile.toList
      _    <- IO.consoleForIO.println(list)
    } yield ()

    IO.consoleForIO.println("program running .. ") >>
      //sleepyStream.compile.drain >>
      //sleepyHelloRepeatedStream_v2.take(5).compile.drain >>
      prog >>
      IO.consoleForIO.println("program ended !")
  }

}
