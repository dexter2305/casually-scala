import cats._
import cats.implicits._
import cats.data._

// State: (S)=> (S, A)

val x         = State[Int, String](state => (state + 1, "sequencer"))
x.run(0).value
x.runS(0).value
x.runA(0).value

State.get[Int].run(5).value
State.set[String]("test").run("world").value
val sequencer = State.modify[Int](n => n + 1).run(0).value

//exercise
//gets the state
def get[S]: State[S, S]                   = State[S, S](s => (s, s))
//sets the given state
def set[S](s: S): State[S, Unit]          = State[S, Unit](_ => (s, ()))
//modifies the current state by function `f`
def modify[S](f: S => S): State[S, Unit]  = State(s => (f(s), ()))
//inspects the state but does not modify. Applies the function to state becomes the value.
def inspect[S, T](f: S => T): State[S, T] = State(s => (s, f(s)))
