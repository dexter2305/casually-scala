package rtjvm

import scala.util.Random
import scala.io.StdIn

object Main extends App {

  /*

    Properties of effect

    - describe the type of computation to be performed
    - describe the type of VALUE returned by the computation
    - construction of effect should be separated from execution of effect

    Monads/Properties | Type of computation  | Type of return value | Construction & Execution decoupled
    ____________________________________________________________________________________________________
    Option(42)        |  YES (may be absent) | YES                  | Side effect are not required. Option is pure effect
    Future            |  YES (asyc - nature) | YES                  | No, Execution is required. Future is impure effect
    MyIO              | Generic but yes      | YES                  | Yes. Uses call by name

   */

  case class MyIO[A](unsafeRun: () => A) {

    def map[B](f: A => B): MyIO[B] = MyIO(() => f(unsafeRun()))

    def flatMap[B](f: A => MyIO[B]): MyIO[B] = MyIO(() => f(unsafeRun()).unsafeRun())
  }

  // exercises
  // 1. IO that returns current Systemtimes
  val clock: MyIO[Long] = MyIO[Long](() => { System.currentTimeMillis() })
  println(s"now:   ${System.currentTimeMillis()}")
  Thread.sleep(3 * 1000)
  println(s"later: ${clock.unsafeRun()}")

  //2 measure time taken by computation
  def measure[A](compute: MyIO[A]): MyIO[Long] = for {
    start  <- clock
    _      <- compute
    finish <- clock
  } yield (finish - start)

  val task: MyIO[String] = MyIO[String](() => { Thread.sleep(3); "completed" })

  val duration = measure(task)
  println(s"${duration.unsafeRun()}")

  //3 IO that prints to console
  def putStrLn(message: String): MyIO[Unit] = MyIO[Unit](() => println(message))

  //4 IO that reads from Console
  def readLn(): MyIO[String] = MyIO[String](() => StdIn.readLine())

  //5 get user name
  val program = for {
    _    <- putStrLn("What is your name? ")
    name <- readLn()
    _    <- putStrLn(s"Welcome $name")
  } yield name

  program.unsafeRun()

}
