import fs2._

val purestream: Stream[Pure, Int] = Stream(1,2,3,4)

Stream.emit(42)
Stream.emits(List(1,2,3))

purestream.toList
purestream.compile.toList

val a = Stream("Max", "Verstappen")
val b = Stream("Sergio", "Perez")

val bothVector = (a ++ b).compile.toVector