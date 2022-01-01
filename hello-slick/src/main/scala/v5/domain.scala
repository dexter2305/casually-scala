package v5

object domain{
  final case class User(id: Option[Long] = None, name: String)
}
