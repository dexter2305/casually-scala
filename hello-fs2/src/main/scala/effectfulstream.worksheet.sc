import fs2._
import cats.effect.IO
import cats.effect.unsafe.implicits.global
val names                         = Stream("Max", "Sergio", "Lewis")
def getAge(name: String): IO[Int] = IO.delay(name.length())


//evalFilter: similar to filter but effectful, hence with IO.
names.evalFilter(s => IO.delay(s.length() < 5))

//evalMap: similar to Map but effectful, hence with IO
val agestream = names.evalMap(getAge)
//compile fs2 streams to different datastructures using compile - similar to pure stream
agestream.compile.toList.unsafeRunSync()


//evalTap
names.evalTap(name => IO(println(s"received: $name")))
