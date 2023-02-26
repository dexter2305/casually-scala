import io.getquill._

object QuillTutorial extends App {

  case class User(id: Int, name: String, isActive: Boolean)
  case class Device(id: Int, name: String, userId: Int)

  lazy val ctx = new PostgresJdbcContext(SnakeCase, "db.pg")
  import ctx._
  // println(ctx)

  object schema {
    val users = quote {
      querySchema[User]("Users")
    }
  }
  val getUsers = quote(schema.users)
  // single insert
  val maxVerstapppen  = User(1, "Max Verstappen", true)
  val insertMax = quote(
    schema.users
      .insert(
        // insert as value or lift an instance outside the quote
        // User(1, "Max Verstappen", true),
        lift(maxVerstapppen)
      )
      .returningGenerated(_.id),
  )
  val id         = ctx.run(insertMax)
  println(s"id inserted was $id")

  val names = List("Charles LeClerc", "Sergio Perez", "George Russel", "Carlos Sainz", "Lewis Hamilton", "Lando Norris", "Fernando Alonso")
  val users = names.map(User(1, _, true))
  // single inserts with a lift
  val qs    = users.map(user => quote(schema.users.insert(lift(user)).returningGenerated(_.id)))

  qs.foreach{ q => 
    val id = ctx.run(q)
    println(s"$q yielded $id")
  }

  case class Person(id: Long, name: String, age: Int)

  val a = quote {
  liftQuery(List(Person(0, "John", 31),Person(2, "name2", 32))).foreach(e => query[Person].insert(e))
}


  ctx.close()
}
