package v3

object domain {

  case class Album(id: Option[Long] = None, artist: String, title: String, year: Int)

}
