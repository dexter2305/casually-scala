package v1

object domain {
  final case class User(id: Long = 0, name: String)
  object UserData {
    //for testing
    var all: List[User] = List(
      User(1, "Light Yagami"), 
      User(2, "Ryuk"), 
      User(3, "L"),
      User(4, "Kiera")
    )
  }
}
