import fs2._

//inifinite stream with 'constant' returns the same element infinite times
val stream = Stream.constant("ping")

stream.take(10).compile.toList

//infinite stream with 'repeat'
val repeatStream = Stream(1, 2, 3).repeat

//infinite stream with 'unfold' - can either be inifinite or finite
val oddints: Stream[Pure, Int]  = Stream.unfold(1)(i => Some((i, i + 2)))
val evenints: Stream[Pure, Int] = Stream.unfold(2)(i => Some((i, i + 2)))
val finite                      = Stream.unfold(1)(i => if (i < 5) Some((i, i + 1)) else None)
oddints.take(10).toList
finite.toList

//zipping two inifinite streams
val zippedStreams: Stream[Pure, (Int, Int)] = oddints.zip(evenints)
zippedStreams.take(10).toList.foreach(println)