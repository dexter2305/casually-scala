package lectures.part4typeclasses

import java.util.Date

object JsonSerialization extends App {


  case class User(name: String, age: Int, email: String)
  case class Post(content: String, createdAt: Date)
  case class Feed(user: User, posts: Seq[Post])

}