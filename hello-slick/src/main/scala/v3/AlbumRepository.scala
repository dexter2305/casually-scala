package v3


trait AlbumRepository extends AlbumTable { this: DBComponent =>

  import profile.api._
  import domain._
  import scala.concurrent._

  def create(album: Album): Future[Int] = db.run { albums += album }

  //def update(album: Album): Future[Int] = db.run { albumTableQuery.filter(_.id === album.id.get).update(album) }

  //def getById(id: Int): Future[Option[Album]] = db.run { albums.filter(_.id === id).result.headOption }

  def getAll(): Future[List[Album]] = db.run { albums.to[List].result }

  //def delete(id: Int): Future[Int] = db.run { albumTableQuery.filter(_.id === id).delete }

}