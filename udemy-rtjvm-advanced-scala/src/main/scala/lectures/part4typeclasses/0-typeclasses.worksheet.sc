import scala.util.Try

trait Codec[T] {
  def encode(value: T): String
  def decode(string: String): Option[T]
}

object Codec {

  // direct interface apis
  // def encode[T](value: T)(implicit codec: Codec[T])       = codec.encode(value)
  // def decode[T](string: String)(implicit codec: Codec[T]) = codec.decode(string)

  // find implicit type class
  def apply[T](implicit codec: Codec[T]): Codec[T] = codec
}

implicit object IntCodec extends Codec[Int] {

  override def encode(value: Int): String          = s"value is ${value}"
  override def decode(string: String): Option[Int] = Try(string.toInt).toOption
}

// println(Codec.encode(30))
// println(Codec.decode("40"))

println(Codec[Int].encode(35))

implicit class EncodOps[T](value: T) {
  def encode(implicit codec: Codec[T]): String = codec.encode(value)
}

implicit class DecodeOps(string: String) {
  def decode[T: Codec] = {
    val codec = implicitly[Codec[T]]
    codec.decode(string)
  }
}
println(6.encode)


val l = List(1,2,3)
l.mkString("[",",","]")
l.mkString("|","-","|")