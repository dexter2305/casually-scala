import cats.effect.unsafe.IORuntime
implicit val runtime: IORuntime = IORuntime.global

import cats.effect._
import io.circe._
import io.circe.literal._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._

def hello(name: String): Json = json"""{"name": $name}"""

val greeting: Json = hello("naveen")

import org.http4s.circe._
Ok(greeting).unsafeRunSync()

case class Message(text: String)
case class User(name: String)


import io.circe.syntax._

//implicit val userEncoder: Encoder[User] = Encoder.instance[User](user => json"${user.toString}")
import io.circe.generic.auto._
User("naveen").asJson
