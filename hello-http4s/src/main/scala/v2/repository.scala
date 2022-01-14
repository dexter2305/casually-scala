package v2

import java.util.UUID

object repository {
  object userRepository {
    import domain._
    import scala.collection.mutable.Map

    /*
      using mutaable map for prototyping
     */
    private val userDb = List("max", "alonso", "hamilton", "ricciardo")
      .map(s => {
        val id = UUID.randomUUID.toString()
        (id, User(id, s))
      })
      .foldLeft(Map[String, User]()) { case (db, user) => db += user }

    def add(user: User): Unit = {
      println(s"adding $user")
      userDb += (user.id -> user)
    }

    def findAll(): Seq[User] = {
      println(s"${userDb.values.map(_.name).mkString("-")}")
      userDb.values.toList
    }

    def findById(id: String): Option[User] = userDb.find(_._1 == id) match {
      case Some(tuple) => Some(tuple._2)
      case None        => None
    }

  }
}
