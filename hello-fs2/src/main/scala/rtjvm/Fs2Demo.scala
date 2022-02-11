package rtjvm

import cats.effect.IOApp
import cats.effect.IO

object Fs2Demo extends IOApp.Simple {

  case class Actor(id: Int, firstname: String, lastname: String)
  object data {
    // Justice League
    val henryCavil: Actor = Actor(0, "Henry", "Cavill")
    val galGodot: Actor   = Actor(1, "Gal", "Godot")
    val ezraMiller: Actor = Actor(2, "Ezra", "Miller")
    val benFisher: Actor  = Actor(3, "Ben", "Fisher")
    val rayHardy: Actor   = Actor(4, "Ray", "Hardy")
    val jasonMomoa: Actor = Actor(5, "Jason", "Momoa")

    // Avengers
    val scarlettJohansson: Actor = Actor(6, "Scarlett", "Johansson")
    val robertDowneyJr: Actor    = Actor(7, "Robert", "Downey Jr.")
    val chrisEvans: Actor        = Actor(8, "Chris", "Evans")
    val markRuffalo: Actor       = Actor(9, "Mark", "Ruffalo")
    val chrisHemsworth: Actor    = Actor(10, "Chris", "Hemsworth")
    val jeremyRenner: Actor      = Actor(11, "Jeremy", "Renner")
    val tomHolland: Actor        = Actor(13, "Tom", "Holland")
    val tobeyMaguire: Actor      = Actor(14, "Tobey", "Maguire")
    val andrewGarfield: Actor    = Actor(15, "Andrew", "Garfield")
  }

  implicit class IOAppOps[A](ioa: IO[A]) {

    def debug: IO[A] = ioa.map { ioa =>
      println(s"[${Thread.currentThread().getName()}] - $ioa")
      ioa
    }
  }

  import fs2._
  import data._
  // purestreams
  //  finite
  val tomHollandStream: Stream[Pure, Actor] = Stream.emit(tomHolland)
  val spiderMenStream: Stream[Pure, Actor]  = Stream.emits(Seq(tobeyMaguire, andrewGarfield, tomHolland))
  val jlActorsStream: Stream[Pure, Actor]   = Stream.emits(List(henryCavil, galGodot, ezraMiller, benFisher, rayHardy, jasonMomoa))

  //  infinite
  val jlActorsInfStream: Stream[Pure, Actor] = jlActorsStream.repeat

  // stream => std lib
  val someJlActors = jlActorsInfStream.take(5).toList

  // effectful stream
  val savingTomHollandToDb = Stream.eval {
    IO {
      println(s"saving tom holland to db")
      tomHolland
    }
  }

  val x: IO[Unit] = savingTomHollandToDb.compile.drain

  val avengersActors = Stream.chunk(
    Chunk.array(
      Array(scarlettJohansson, robertDowneyJr, chrisEvans, markRuffalo, chrisHemsworth, jeremyRenner, tomHolland, tobeyMaguire, andrewGarfield),
    ),
  )

  // transformations
  val allActors: Stream[Pure, Actor] = jlActorsStream ++ avengersActors

  // flatmap
  val a: Stream[IO, Unit] = jlActorsStream.flatMap { actor =>
    Stream.eval(IO(println(s"jl actor: $actor")))
  }

  // evalMap = flatMap + eval (above)
  val a1: Stream[IO, Unit] = jlActorsStream.evalMap(actor => IO(println(s"jl actor (eval map): $actor")))

  // evalTap
  val a2: Stream[IO, Actor] = jlActorsStream.evalTap(actor => IO(println(s"hello, $actor")))

  override def run: IO[Unit] = {
    //IO(someJlActors).debug.map(_ => ())
    
    a2.compile.toList.map(_ => ())
  }
}
