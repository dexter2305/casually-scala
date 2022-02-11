import fs2._

// pure streams
Stream.empty
Stream(42).toList
Stream.emit(42).toList
Stream(1, 2, 3).toList
Stream.emits(List(1, 2, 3))
Stream.range(0, 5).intersperse(42).toList

// effectful streams
import cats.effect.IO
import cats.effect.unsafe.implicits.global

Stream.eval(IO(println("Hello, world")))

val y = IO {
  (0 to 10).toList
}

val effectfulStream = Stream.eval(IO {
  println("meaning of life")
  (0 to 10).toList
})

effectfulStream.compile.toList.unsafeRunSync()
effectfulStream.compile.to(Array).unsafeRunSync()
effectfulStream.compile.drain.unsafeRunSync()

// chunks
import fs2.Chunk
Stream.chunk(Chunk.seq(Seq(1, 2, 3, 4)))

// stream operations
// ops: ++, map, flatMap, handleErrorWith, brancket
Stream(1, 2, 3) ++ Stream.emit(42)
Stream('a', 'b', 'c').map(_.toInt).toList
Stream(1, 2, 3, 4).flatMap(n => Stream.eval(IO(println(s"value: $n"))))
Stream(1, 2, 3, 4).flatMap(n => Stream.emits(List(n, n * n)))

// error handling
// using try-catch
// explicitly raising through Stream api
val explicitException = Stream(1,2,3) ++ Stream.raiseError[IO](new RuntimeException("Just like that !"))
try explicitException.compile.toList.unsafeRunSync()
catch { case e: Throwable => println(e.getMessage) }
// implcitly throw exeption in pure code
val implicitException = Stream(1, 2, 3) ++ Stream(throw new Exception("just stop")) ++ Stream.emit(42).repeat
try implicitException.toList
catch { case ex: Exception => println(s"error: ${ex.getMessage()}") }

// implicitly throw exeption in effect
val implicitExceptionFromEff = Stream.emits(List(1, 2, 3)) ++ Stream.eval(IO(new Exception("exception from effect"))) ++ Stream.emit(42)
try implicitExceptionFromEff.compile.toList.unsafeRunSync()
catch { case e: Exception => println(s"error: ${e.getMessage()}") }

// using handleErrorWith
explicitException.handleErrorWith(ex => 
  Stream(println(s"err: $ex"))).compile.toList.unsafeRunSync()

(Stream(1,2,3).covary[IO] ++ Stream.raiseError[IO](new Exception("bad")) ++ Stream(42))
  .handleErrorWith(e => Stream.emit(-1))
  .compile.toList.unsafeRunSync()

  