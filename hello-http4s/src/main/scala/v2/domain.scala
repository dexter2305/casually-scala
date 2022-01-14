package v2

object domain {
  case class User(id: String = "", name: String)
  object User {

    def apply(name: String): User = new User(name = name)
  }
}
