package v4

object domain {
  final case class Album(id: Option[Long] = None, artist: String, title: String)
}

