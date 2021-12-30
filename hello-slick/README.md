# hello-slick

## Get api

DatabaseConfig is a facade to hide the concrete implementation (*PostgresProfile*)

```scala
  val config = DatabaseConfig.forConfig[PostgresProfile]("profile_name")
  import config.profile.api._
```

## Create tables/schems

```scala
  case class User(id: Long = 0l, name: String)
  
  // use either db type specific import
  // check example one.Main
  import slick.jdbc.profile.PostgresProfile
  // or
  // this import comes from DatabaseConfig.forConfig from previous step
  // check example two.Main
  import config.profile.api._

  // without one of the two imports, this class will not compile
  // choose either db type specific profile or configuration provided profile (preferred, for isolation)
  class UserTable(tag: Tag) extends Table[User](tag, "users) { ??? }
```