package one

object domain {
    case class Album(id: Long = 0, title: String, artist: String, year: Int)
}
