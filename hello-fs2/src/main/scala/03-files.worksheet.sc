import fs2.Chunk
import fs2.Stream
import fs2.io
import fs2.io.file.Files
import fs2.io.file.Flags
import fs2.io.file.Path
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import fs2.text._
import java.nio.file.Paths

val sourcefilePath: String       = "./data/sales-data.csv"

//val byteStream: Stream[IO, Byte] = Files[IO].readAll(path = Path(sourcefilePath))
val byteStream: Stream[IO, Byte] = Files[IO].readAll(Path(sourcefilePath), 4096, Flags.Read)
//val byteStream = Files[IO].readAll(Paths.get(sourcefilePath), 1000)

// looks for every byte - poor efficiency
val effSize: IO[Int] = byteStream.compile.fold(0)((acc, b) => acc + 1)
effSize.unsafeRunSync()

// # chunks
val chunkedSize: IO[Int] = byteStream.chunks.compile.fold(0)((acc, chunk) => acc + chunk.size)
chunkedSize.unsafeRunSync()

// # pipes
// pipe: utf8.decode = Byte => String
val stringStream: Stream[IO, String] = byteStream.through(utf8.decode)

val few = stringStream.take(2)

val x = few.compile.toList


x.unsafeRunSync()
x.unsafeRunSync().head.length()

// pipe: lines = arbitrary string => string with line breaks
val lineStream: Stream[IO, String] = byteStream.through(utf8.decode).through(lines)
lineStream.compile.toList.unsafeRunSync().size

