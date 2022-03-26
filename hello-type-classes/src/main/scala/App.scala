object sql {

  sealed trait ColumnType
  case class StringType(value: String)   extends ColumnType
  case class IntType(value: Int)         extends ColumnType
  case class NullType()                  extends ColumnType
  case class BooleanType(value: Boolean) extends ColumnType
  type Row = Seq[ColumnType]

  trait codec[A] {
    def encode(value: A): ColumnType
  }

  object codecs {

    implicit val stringCodec: codec[String] = StringType(_)
    implicit val intCodec: codec[Int]       = IntType(_)
    implicit val nullCodec: codec[Null]     = new codec[Null] {
      override def encode(value: Null): ColumnType = new NullType
    }
  }

  /**
   * curried parameters
   *
   * @param value
   * @param codec
   * @return
   */
  def encode_v1[A](value: A)(implicit codec: codec[A]): ColumnType = codec.encode(value)

  /**
   * context bound syntax
   *
   * @param value
   * @return
   */
  def encode_v2[A: codec](value: A) = {
    val con = implicitly[codec[A]]
    con.encode(value)
  }

  object ops {
    implicit class CodecOps[A](value: A) {
      def encode(implicit codec: codec[A]): ColumnType = codec.encode(value)
    }

  }
}

object App extends App {

  import sql._

  // (a)
  // create sql row without using type classes
  // create a single column of type string
  {
    val nameColumn           = StringType("Naveen")
    val idColumn             = IntType(1)
    val row: Seq[ColumnType] = List(idColumn, nameColumn)
    println(row.mkString(" | "))
  }
  // (b) type-classes using interface object approach
  // create using type-classes
  // use interface object approach or explicit approach
  {
    { // v1 - explicit encoding
      import sql.codecs.{stringCodec, intCodec, nullCodec}
      val row_excplicit_encode: Seq[ColumnType] = List(encode_v1("Naveen"), encode_v2(1), encode_v1(null))
      println(row_excplicit_encode.mkString(" | "))
    }

    { //v2 - implicit encoding
      import sql._
      import sql.codecs._
      implicit def toColType[A](e: A)(implicit codec: codec[A]) = {
        //println(s"encoding '$e'")
        codec.encode(value = e)
      }
      val row_implicit_encode: Seq[ColumnType] = List("Naveen", 1, null)
      println(row_implicit_encode.mkString(" "))
    }
  }
  // (c) type classes uing interface syntax approach
  // create row using type-classes
  // use interface syntax approach

  {
    import sql._
    import sql.codecs.stringCodec
    //import sql.ops._
    // this is still not working
    val x: ColumnType = "test"
    
  }

}
